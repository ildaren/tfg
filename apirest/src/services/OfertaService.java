package services;

import data.CocheOfertado;
import data.CochesOfertados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.naming.NamingContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("/oferta")
public class OfertaService {

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

	public OfertaService () {
		openDB();
	}


	private CocheOfertado cocheFromRS(ResultSet resultSet) throws SQLException {
        return new CocheOfertado(
			resultSet.getInt("idCocheOfertado"),
			uriInfo.getBaseUri() + "oferta/" + resultSet.getInt("idCocheOfertado"),
			resultSet.getString("matricula"),
			resultSet.getString("marca"),
			resultSet.getInt("numeroPuertas"),
			resultSet.getInt("capacidadMaletero"),
			resultSet.getString("cambioMarchas"),
			resultSet.getInt("plazas"),
			resultSet.getBoolean("aireAcondicionado"),
			resultSet.getDouble("precioPorDia"),
			resultSet.getInt("kilometraje"),
			resultSet.getString("politicaCombustible"),
			resultSet.getInt("politicaCancelacion"),
			resultSet.getString("modelo"),
			uriInfo.getBaseUri() + "filial/" + resultSet.getString("idFilialCoche")
		);
    }

	@GET
	@Path("{idCoche}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCocheOfertado(@PathParam("idCoche") String idCoche) throws SQLException {

		try {

			String sql = "SELECT *\n"
				+ "FROM cocheOfertado\n"
    			+ "WHERE idCocheOfertado = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(idCoche));
            resultSet = preparedStatement.executeQuery();

			String location = uriInfo.getBaseUri() + "oferta";
			String contentLocation = uriInfo.getAbsolutePath().toString();

			if (resultSet.next()) {
				CocheOfertado coche = cocheFromRS(resultSet);
                return Response.status(Response.Status.OK).entity(coche).header("Location", location).header("Content-Location", contentLocation).build();
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

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getCochesOfertados(@QueryParam("idFilial") String idFilial, @QueryParam("fechaInicio") String fechaInicio, @QueryParam("fechaFinal") String fechaFinal, @QueryParam("offset") @DefaultValue("0") String offset) throws SQLException {

		try {

			if (fechaInicio == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Error 400: fecha de inicio requerida").build();
			}
			if (fechaFinal == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Error 400: fecha de final requerida").build();
			}
			if (idFilial == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Error 400: id de una filial requerida").build();
			}

			int pageOffset = Integer.parseInt(offset);
			int pageLimit = pageOffset + 10;
			int pagePrev = pageOffset - 10;
			String sql = "SELECT DISTINCT co.*\n"
				+ "FROM cocheofertado co, disponibilidad d\n"
				+ "WHERE co.idFilialCoche = ? AND co.enMantenimiento = 0 AND co.idCocheOfertado NOT IN (\n"
					+ "SELECT idCocheDisponibilidad\n"
						+ "FROM disponibilidad d\n"
						+ "WHERE ((? > d.fechaNoDisp AND ? < d.fechaInicioDisp) OR (? > d.fechaNoDisp AND ? < d.fechaInicioDisp) OR (? < fechaNoDisp AND ? > fechaInicioDisp)))\n"
				+ "LIMIT ?,?;";

            preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(idFilial));
			preparedStatement.setString(2, fechaInicio);
			preparedStatement.setString(3, fechaInicio);
			preparedStatement.setString(4, fechaFinal);
			preparedStatement.setString(5, fechaFinal);
			preparedStatement.setString(6, fechaInicio);
			preparedStatement.setString(7, fechaFinal);
			preparedStatement.setInt(8, pageOffset);
			preparedStatement.setInt(9, pageLimit);
            resultSet = preparedStatement.executeQuery();
			CochesOfertados coches = new CochesOfertados();
			coches.setHref(uriInfo.getAbsolutePath() + "?idFilial=" + idFilial + "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal + "&offset=" + offset);

			int contador = 0;
			while (resultSet.next()) {
                CocheOfertado coche = cocheFromRS(resultSet);
                coches.addCoche(coche);
				contador++;
			}
			
			if (contador == 5) coches.setNext(uriInfo.getAbsolutePath() + "?idFilial=" + idFilial + "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal + "&offset=" + pageLimit);
			if (pageOffset >= pageLimit) coches.setPrev(uriInfo.getAbsolutePath() + "?idFilial=" + idFilial + "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal + "&offset=" + pagePrev);
			
			String location = uriInfo.getAbsolutePath().toString();
			String contentLocation = uriInfo.getAbsolutePath().toString();

            return Response.status(Response.Status.OK).entity(coches).header("Location", location).header("Content-Location", contentLocation).build();

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
}