package services;

import data.Reserva;
import data.ReservaPago;
import data.Reservas;
import data.Usuario;
import data.Pago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.naming.NamingContext;

@Path("/usuario")
public class UsuarioService {
    
    @Context
    private UriInfo uriInfo;

    private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

    private void openDB() {
		try {
			InitialContext ctx = new InitialContext();
			NamingContext envCtx = (NamingContext) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/mydb");
			connection = ds.getConnection();
		} catch (NamingException ne) {
			ne.getMessage();
			ne.printStackTrace();
		} catch (SQLException se) {
			se.getMessage();
			se.printStackTrace();
		}
	}

	private void closeDB() throws SQLException {
		try {
			if (resultSet != null) resultSet.close();
			if (preparedStatement != null) preparedStatement.close();
			if (connection != null) connection.close();
		} catch (SQLException se) {
			se.getMessage();
			se.printStackTrace();
		}
	}

	public UsuarioService() {
		openDB();
	}

	//>0 esta bien, coincide el titular y la tarjeta
	//0 esta bien, no se encuentra la tarjeta
	//-1 esta mal, se encuentra la tarjeta pero no coincide con el titular

	private int findPagoTarjeta(String titular, String numTarjeta) throws SQLException {

		int result = 0;
		String sql = "SELECT idPago FROM pago WHERE numTarjeta=?;";

		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, numTarjeta);
		resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			result = -1;
			sql = "SELECT idPago FROM pago WHERE idPago=? AND titular=?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, resultSet.getInt("idPago"));
			preparedStatement.setString(2, titular);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				result = resultSet.getInt("idPago");
			}
		}
		return result;
	}

	private boolean findPagoId (int idPago) throws SQLException {

		String sql = "SELECT idReserva FROM reserva WHERE idPagoReserva=?;";

		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, idPago);
		resultSet = preparedStatement.executeQuery();

		return resultSet.next() ? true : false;
	}

	private Pago pagoFromRS(ResultSet resultSet) throws SQLException {
        return new Pago(

            resultSet.getInt("idPago"),
			uriInfo.getBaseUri() + "usuario/" + resultSet.getString("idUsuarioPago") + "/pago/" + resultSet.getString("idPago"),
			resultSet.getString("titular"),
			resultSet.getString("numTarjeta").toString(),
			uriInfo.getBaseUri() + "usuario/" + resultSet.getString("idUsuarioPago")
		);
    }

	private Usuario usuarioFromRS(ResultSet resultSet) throws SQLException {
		return new Usuario (

			resultSet.getInt("idUsuario"),
			uriInfo.getBaseUri() + "usuario/" + resultSet.getString("idUsuario"),
			resultSet.getString("nombre"),
			resultSet.getString("apellidos"),
			resultSet.getString("correoElectronico"),
			resultSet.getString("dni"),
			resultSet.getString("direccion"),
			resultSet.getString("ciudad"),
			resultSet.getString("codigoPostal"),
			resultSet.getDate("fechaCarne").toString()
		);
	}

	private Reserva reservaFromRS(ResultSet resultSet, String idUsuario) throws SQLException {
        return new Reserva(

			uriInfo.getBaseUri() + "usuario/" + idUsuario + "/reserva/" + resultSet.getString("idReserva"),
			resultSet.getInt("idReserva"),
			resultSet.getDate("fechaReserva").toString(),
			resultSet.getDate("fechaRecogida").toString(),
			resultSet.getDate("fechaEntrega").toString(),
			resultSet.getFloat("precioFinal"),
			uriInfo.getBaseUri() + "filial/" + resultSet.getString("idCiudadRecogida"),
			uriInfo.getBaseUri() + "filial/" + resultSet.getString("idCiudadEntrega"),
			uriInfo.getBaseUri() + "oferta/" + resultSet.getInt("idCocheReservado"),
			uriInfo.getBaseUri() + "usuario/" + idUsuario + "/pago/" + resultSet.getString("idPagoReserva")
		);
    }

//	Se obtienen todos los datos del usuario en base a su id

	@GET
	@Path("{idUsuario}")
	public Response getUsuario (@PathParam("idUsuario") String idUsuario) throws SQLException {

		try {

			String sql = "SELECT * FROM usuario WHERE idUsuario=?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(idUsuario));
            resultSet = preparedStatement.executeQuery();

			String location = uriInfo.getBaseUri() + "usuario";
			String contentLocation = uriInfo.getAbsolutePath().toString();

			if (resultSet.next()) {
				Usuario usuario = usuarioFromRS(resultSet);
                return Response.status(Response.Status.OK).entity(usuario).header("Location", location).header("Content-Location", contentLocation).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Error: coche not found").build();
			}
		} catch (NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Unexpected Error: " + nfe.getMessage()).build();
        } catch (SQLException se) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
				"Unexpected Error: " + se.getMessage() +
				"\nError code: " + se.getErrorCode() +
				"\nSQL Satte: " + se.getSQLState()).build();
        } finally {
			closeDB();
		}
	}

//	Se comprueba si el dni y contraseña estan correctos al iniciar una sesion

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response comprobarUsuario (@QueryParam("dni") String dni, @QueryParam("contrasena") String contrasena) throws SQLException {

		try {

			String sql = "SELECT * FROM usuario WHERE dni=? AND contrasena=?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, dni);
			preparedStatement.setString(2, contrasena);
			resultSet = preparedStatement.executeQuery();

			

			if (resultSet.next()) {
				String location = uriInfo.getBaseUri() + "usuario";
				String contentLocation = location + "/" + resultSet.getInt("idUsuario");
				Usuario usuario = usuarioFromRS(resultSet);

				return Response.status(Response.Status.OK).entity(usuario).header("Location", location).header("Content-Location", contentLocation).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Usuario o contraseña invalido.").build();
			}
        } catch (NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Unexpected Error: " + nfe.getMessage()).build();
        } catch (SQLException se) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
				"Unexpected Error: " + se.getMessage() +
				"\nError code: " + se.getErrorCode() +
				"\nSQL Satte: " + se.getSQLState()).build();
        } finally {
			closeDB();
		}
	}

//	Añade un usuario nuevo a la base de datos

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Response postUsuario(Usuario usuario) throws SQLException {

		try {

			String sql = "INSERT INTO usuario (`nombre`, `apellidos`, `contrasena`, `correoElectronico`, `dni`, `direccion`, `ciudad`, `codigoPostal`, `fechaCarne`)" 
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getApellidos());
			preparedStatement.setString(3, usuario.getContrasena());
			preparedStatement.setString(4, usuario.getCorreoElectronico());
			preparedStatement.setString(5, usuario.getDni());
			preparedStatement.setString(6, usuario.getDireccion());
			preparedStatement.setString(7, usuario.getCiudad());
			preparedStatement.setString(8, usuario.getCodigoPostal());
			preparedStatement.setString(9, usuario.getFechaCarne());

			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();

			String location = uriInfo.getAbsolutePath().toString();
			String contentLocation = location + "/" + resultSet.getInt(1);
			
			return Response.status(Status.CREATED).header("Location", location).header("Content-Location", contentLocation).build();

		} catch (SQLException se) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
				"Unexpected Error: " + se.getMessage() +
				"\nError code: " + se.getErrorCode() +
				"\nSQL Satte: " + se.getSQLState()).build();
		} finally {
			closeDB();
		}
	}

//	Obtiene una lista de los datos de pago realizados por el usuario

	@GET
	@Path("{idUsuario}/pago")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getPagos(@PathParam("idUsuario") String idUsuario) throws SQLException {

		try {
			ArrayList <Pago> pagos = new ArrayList<>();

			String sql = "SELECT *\n"
				+ "FROM pago \n"
				+ "WHERE idUsuarioPago = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(idUsuario));
            resultSet = preparedStatement.executeQuery();

			String location = uriInfo.getBaseUri() + "usuario/" + idUsuario + "/pago";
			String contentLocation = uriInfo.getAbsolutePath().toString();

			while (resultSet.next()) {

				Pago pago = pagoFromRS(resultSet);
				pagos.add(pago);
			}

			return Response.status(Response.Status.OK).entity(pagos).header("Location", location).header("Content-Location", contentLocation).build();

		} catch (NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Unexpected Error: " + nfe.getMessage()).build();
        } catch (SQLException se) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
				"Unexpected Error: " + se.getMessage() +
				"\nError code: " + se.getErrorCode() +
				"\nSQL Satte: " + se.getSQLState()).build();
        } finally {
			closeDB();
		}
	}

//	Obtener los datos de un unico pago de un usuario

	@GET
	@Path("{idUsuario}/pago/{idPago}")
	@Produces({MediaType.APPLICATION_JSON})
    public Response getPago(@PathParam("idUsuario") String idUsuario, @PathParam("idPago") String idPago) throws SQLException {

		try {

			String sql = "SELECT *\n"
				+ "FROM pago \n"
				+ "WHERE idPago = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(idPago));
            resultSet = preparedStatement.executeQuery();

			String location = uriInfo.getBaseUri() + "usuario/" + idUsuario + "/pago";
			String contentLocation = uriInfo.getAbsolutePath().toString();

			if (resultSet.next()) {
				Pago pago = pagoFromRS(resultSet);
                return Response.status(Response.Status.OK).entity(pago).header("Location", location).header("Content-Location", contentLocation).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Error: coche not found").build();
			}
		} catch (NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Unexpected Error: " + nfe.getMessage()).build();
        } catch (SQLException se) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
				"Unexpected Error: " + se.getMessage() +
				"\nError code: " + se.getErrorCode() +
				"\nSQL Satte: " + se.getSQLState()).build();
        } finally {
			closeDB();
		}
    }

//	Obtener los datos de una reserva de un usuario

	@GET
	@Path("{idUsuario}/reserva/{idReserva}")
	@Produces({MediaType.APPLICATION_JSON})
    public Response getReserva(@PathParam("idUsuario") String idUsuario, @PathParam("idReserva") String idReserva) throws SQLException {

		try {

			String sql = "SELECT *\n"
				+ "FROM reserva \n"
				+ "WHERE idReserva = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(idReserva));
            resultSet = preparedStatement.executeQuery();

			String location = uriInfo.getBaseUri() + "usuario/" + idUsuario + "/reserva";
			String contentLocation = uriInfo.getAbsolutePath().toString();

			if (resultSet.next()) {
				Reserva reserva = reservaFromRS(resultSet, idUsuario);
				
                return Response.status(Response.Status.OK).entity(reserva).header("Location", location).header("Content-Location", contentLocation).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Error: coche not found").build();
			}
		} catch (NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Unexpected Error: " + nfe.getMessage()).build();
        } catch (SQLException se) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
				"Unexpected Error: " + se.getMessage() +
				"\nError code: " + se.getErrorCode() +
				"\nSQL Satte: " + se.getSQLState()).build();
        } finally {
			closeDB();
		}
    }

//	Obtener todas las reservas de un usuario

	@GET
	@Path("{idUsuario}/reserva")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getReservas(@PathParam("idUsuario") String idUsuario, @QueryParam("offset") @DefaultValue("0") String offset) throws SQLException {

		try {

			int pageOffset = Integer.parseInt(offset);
			int pageLimit = pageOffset + 10;
			int pagePrev = pageOffset - 10;
			String sql = "SELECT re.*\n"
				+ "FROM reserva re, pago p\n"
				+ "WHERE re.idPagoReserva = p.idPago AND p.idUsuarioPago = ?\n"
				+ "LIMIT ?,?;";

            preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(idUsuario));
			preparedStatement.setInt(2, pageOffset);
			preparedStatement.setInt(3, pageLimit);
            resultSet = preparedStatement.executeQuery();

			Reservas reservas = new Reservas();
			reservas.setHref(uriInfo.getAbsolutePath() + "?idUsuario=" + idUsuario + "&offset=" + offset);
			int contador = 0;
			while (resultSet.next()) {
				Reserva reserva = reservaFromRS(resultSet, idUsuario);
                reservas.addReserva(reserva);
				contador++;
			}
			
			if (contador == 10) reservas.setNext(uriInfo.getAbsolutePath() + "?idUsuario=" + idUsuario + "&offset=" + pageLimit);
			if (pageOffset >= 10) reservas.setPrev(uriInfo.getAbsolutePath() + "?idUsuario=" + idUsuario + "&offset=" + pagePrev);
			
			String location = uriInfo.getBaseUri() + "reserva";
			String contentLocation = uriInfo.getAbsolutePath().toString();

            return Response.status(Response.Status.OK).entity(reservas).header("Location", location).header("Content-Location", contentLocation).build();

        } catch (NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Unexpected Error: " + nfe.getMessage()).build();
        } catch (SQLException se) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
				"Unexpected Error: " + se.getMessage() +
				"\nError code: " + se.getErrorCode() +
				"\nSQL Satte: " + se.getSQLState()).build();
        } finally {
			closeDB();
		}
	}

//	Borrar una reserva de un usuario

	@DELETE
	@Path("{idUsuario}/reserva/{idReserva}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteReserva(@PathParam("idUsuario") String idUsuario, @PathParam("idReserva") String idReserva) throws SQLException {

		try {

			String sql = "SELECT fechaRecogida, fechaEntrega, idPagoReserva, idCocheReservado\n"
				+ "FROM reserva, pago\n"
				+ "WHERE idReserva = ? AND idUsuarioPago = ? AND idPago=idPagoReserva";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(idReserva));
			preparedStatement.setInt(2, Integer.parseInt(idUsuario));
			resultSet = preparedStatement.executeQuery();
			
			String fechaRecogida;
			String fechaEntrega;
			int idPago;
			int idCocheReservado;
			if (resultSet.next()) {
				fechaRecogida = resultSet.getDate("fechaRecogida").toString();
				fechaEntrega = resultSet.getDate("fechaENtrega").toString();
				idPago = resultSet.getInt("idPagoReserva");
				idCocheReservado = resultSet.getInt("idCocheReservado");

				sql = "DELETE FROM disponibilidad\n"
				+ "WHERE ? = fechaNoDisp AND ? = fechaInicioDisp AND idCocheDisponibilidad = ?;\n";

				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, fechaRecogida);
				preparedStatement.setString(2, fechaEntrega);
				preparedStatement.setInt(3, idCocheReservado);
				if (preparedStatement.executeUpdate() > 0) {

					sql = "DELETE FROM reserva WHERE idReserva=?;";

					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, Integer.parseInt(idReserva));

					if (preparedStatement.executeUpdate() > 0) {

						if (!findPagoId(idPago)) {

							sql = "DELETE FROM pago WHERE idPago=?\n";
							preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setInt(1, idPago);
							if (preparedStatement.executeUpdate() > 0) {
								return Response.status(Response.Status.OK).build();
							}
							else {
								return Response.status(Response.Status.NOT_FOUND).entity("Error: No encontrado en pago").build();
							}
						}
						else return Response.status(Response.Status.OK).build();
					} else {
						return Response.status(Response.Status.NOT_FOUND).entity("Error: No encontrado en reservas al borrar").build();
					}
				} else {
					return Response.status(Response.Status.NOT_FOUND).entity("Error: No encontrado en disponibilidad").build();
				}
			}
			else {
				return Response.status(Response.Status.NOT_FOUND).entity("Error: No se encuentra la reserva correspondiente al usuario registrado.").build();
			}
		} catch (NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Unexpected Error: " + nfe.getMessage()).build();
        } catch (SQLException se) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
				"Unexpected Error: " + se.getMessage() +
				"\nError code: " + se.getErrorCode() +
				"\nSQL Satte: " + se.getSQLState()).build();
		} finally {
			closeDB();
		}
	}

//	Actualizar una reserva de un usuario

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("{idUsuario}/reserva/{idReserva}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateReserva(@PathParam("idUsuario") String idUsuario, @PathParam("idReserva") String idReserva, Reserva reservaNueva) throws SQLException {
		
		try {

			String sql = "SELECT fechaRecogida, fechaEntrega, idCocheReservado FROM reserva, pago WHERE idReserva=? AND idUsuarioPago=? AND idPago=idPagoReserva;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(idReserva));
			preparedStatement.setInt(2, Integer.parseInt(idUsuario));
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				int idCoche = resultSet.getInt("idCocheReservado");
				String fechaRecogidaAntigua = resultSet.getDate("fechaRecogida").toString();
				String fechaEntregaAntigua = resultSet.getDate("fechaEntrega").toString();

				sql = "SELECT idDisponibilidad FROM disponibilidad WHERE fechaNoDisp = ? AND fechaInicioDisp = ? AND idCocheDisponibilidad = ?";
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, fechaRecogidaAntigua);
				preparedStatement.setString(2, fechaEntregaAntigua);
				preparedStatement.setInt(3, idCoche);
				resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {

					sql = "SELECT *\n"
						+ "FROM disponibilidad\n"
						+ "WHERE idCocheDisponibilidad = ? AND idDisponibilidad != ? AND ((? > fechaNoDisp AND ? < fechaInicioDisp) OR (? > fechaNoDisp AND ? < fechaInicioDisp) OR (? < fechaNoDisp AND ? > fechaInicioDisp));";

					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, idCoche);
					preparedStatement.setInt(2, resultSet.getInt("idDisponibilidad"));
					preparedStatement.setString(3, reservaNueva.getFechaRecogida());
					preparedStatement.setString(4, reservaNueva.getFechaRecogida());
					preparedStatement.setString(5, reservaNueva.getFechaEntrega());
					preparedStatement.setString(6, reservaNueva.getFechaEntrega());
					preparedStatement.setString(7, reservaNueva.getFechaRecogida());
					preparedStatement.setString(8, reservaNueva.getFechaEntrega());
					resultSet = preparedStatement.executeQuery();

					if (!resultSet.next()) {

						sql = "UPDATE disponibilidad SET fechaNoDisp=?, fechaInicioDisp=? WHERE fechaNoDisp=? AND fechaInicioDisp=? AND idCocheDisponibilidad=?;";

						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString(1, reservaNueva.getFechaRecogida());
						preparedStatement.setString(2, reservaNueva.getFechaEntrega());
						preparedStatement.setString(3, fechaRecogidaAntigua);
						preparedStatement.setString(4, fechaEntregaAntigua);
						preparedStatement.setInt(5, idCoche);

						if (preparedStatement.executeUpdate() > 0) {
							
							sql = "UPDATE reserva SET fechaRecogida=?, fechaEntrega=?, precioFinal=?, idCiudadEntrega=? WHERE idReserva=?;";

							preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setString(1, reservaNueva.getFechaRecogida());
							preparedStatement.setString(2, reservaNueva.getFechaEntrega());
							preparedStatement.setFloat(3, reservaNueva.getPrecioFinal());
							preparedStatement.setInt(4, Integer.parseInt(reservaNueva.getHrefFilialEntrega()));
							preparedStatement.setInt(5, Integer.parseInt(idReserva));

							String location = uriInfo.getBaseUri() + "reserva";
							String contentLocation = uriInfo.getAbsolutePath().toString();

							if (preparedStatement.executeUpdate() > 0) {
								return Response.status(Response.Status.OK).header("Location", location).header("Content-Location",contentLocation).build();
							} else {
								return Response.status(Response.Status.BAD_REQUEST).entity("Error: reserva no actualizada\n").build();
							}
						} else {
							return Response.status(Response.Status.BAD_REQUEST).entity("Error: disponibilidad no actualizada\n").build();
						}
					} else {
						return Response.status(Response.Status.BAD_REQUEST).entity("Error: el nuevo periodo de reserva entra en conflicto con otro existente").build();
					}
				} else {
					return Response.status(Response.Status.BAD_REQUEST).entity("Error: no se ha encontrado la disponibilidad asociada a esta reserva").build();
				}
			} else return Response.status(Response.Status.NOT_FOUND).entity("Error: no se ha encontrado la reserva correpondiente a este usuario").build();
		} catch (NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Unexpected Error: " + nfe.getMessage()).build();
        } catch (SQLException se) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
				"Unexpected Error: " + se.getMessage() +
				"\nError code: " + se.getErrorCode() +
				"\nSQL Satte: " + se.getSQLState()).build();
		} finally {
			closeDB();
		}
	}

//	Añade una reserva, el pago asociado y añade la disponibilidad necesaria al coche reservado

	@POST
	@Path("{idUsuario}/reserva")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response postReserva(@PathParam("idUsuario") String idUsuario, ReservaPago reservaPago) throws SQLException {

		Reserva reserva = reservaPago.getReserva();
		Pago pago = reservaPago.getPago();

		try {
			String sql;
			int result = findPagoTarjeta(pago.getTitular(), pago.getNumTarjeta());
			int idPago;
			if (result == 0) {
				
				sql = "INSERT INTO pago (`Titular`, `numTarjeta`, `idUsuarioPago`) VALUES (?, ?, ?);";

				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, pago.getTitular());
				preparedStatement.setString(2, pago.getNumTarjeta());
				preparedStatement.setInt(3, Integer.parseInt(idUsuario));

				preparedStatement.executeUpdate();
				resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				idPago = resultSet.getInt(1);
			}
			else if (result > 0) {
				idPago = result;
			}
			else {
				return Response.status(Response.Status.BAD_REQUEST).entity("Error: No coincide el titular con el numero de tarjeta").build();
			}

			sql = "INSERT INTO reserva (`fechaReserva`, `fechaRecogida`, `fechaEntrega`, `precioFinal`, `idCiudadRecogida`, `idCiudadEntrega`, `idCocheReservado`, `idPagoReserva`)\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, reserva.getFechaReserva());
			preparedStatement.setString(2, reserva.getFechaRecogida());
			preparedStatement.setString(3, reserva.getFechaEntrega());
			preparedStatement.setFloat(4, reserva.getPrecioFinal());
			preparedStatement.setInt(5, Integer.parseInt(reserva.getHrefFilialRecogida()));
			preparedStatement.setInt(6, Integer.parseInt(reserva.getHrefFilialEntrega()));
			preparedStatement.setInt(7, Integer.parseInt(reserva.getHrefCoche()));
			preparedStatement.setInt(8, idPago);

			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();

			String location = uriInfo.getAbsolutePath().toString();
			String contentLocation = location + "/" + resultSet.getInt(1);

			sql = "INSERT INTO disponibilidad (`idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (?, ?, ?);";
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, Integer.parseInt(reserva.getHrefCoche()));
			preparedStatement.setString(2, reserva.getFechaRecogida());
			preparedStatement.setString(3, reserva.getFechaEntrega());

			preparedStatement.executeUpdate();
			
			return Response.status(Status.CREATED).header("Location", location).header("Content-Location", contentLocation).build();

		} catch (SQLException se) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
				"Unexpected Error: " + se.getMessage() +
				"\nError code: " + se.getErrorCode() +
				"\nSQL Satte: " + se.getSQLState()).build();
		} finally {
			closeDB();
		}
	}
}
