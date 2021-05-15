package services;

import data.Filial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.naming.NamingContext;

@Path("/filial")
public class FilialService {
    
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

	public FilialService () {
		openDB();
	}

    private Filial filialFromRS(ResultSet resultSet) throws SQLException {
        return new Filial(
			resultSet.getInt("idFilial"),
			uriInfo.getBaseUri() + "filial/" + resultSet.getInt("idFilial"),
			resultSet.getString("comunidad"),
			resultSet.getString("ciudad"),
			resultSet.getString("calle"),
			resultSet.getInt("numero")
		);
    }

	@GET
	@Produces({MediaType.APPLICATION_JSON})
    public Response getFiliales() throws SQLException {

        try {

			ArrayList<Filial> filiales = new ArrayList<>();

			String sql = "SELECT *\n"
				+ "FROM filial;\n";

			preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

			String location = uriInfo.getBaseUri() + "filial";
			String contentLocation = uriInfo.getAbsolutePath().toString();
		
			while (resultSet.next()) {
				Filial filial = filialFromRS(resultSet);
                filiales.add(filial);
			}
			return Response.status(Response.Status.OK).entity(filiales).header("Location", location).header("Content-Location", contentLocation).build();
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
    @Path("{idFilial}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFilial(@PathParam("idFilial") String idFilial) throws SQLException {

        try {

			String sql = "SELECT *\n"
				+ "FROM filial\n"
    			+ "WHERE idFilial = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(idFilial));
            resultSet = preparedStatement.executeQuery();

			String location = uriInfo.getBaseUri() + "filial";
			String contentLocation = uriInfo.getAbsolutePath().toString();

			if (resultSet.next()) {
				Filial filial = filialFromRS(resultSet);
                return Response.status(Response.Status.OK).entity(filial).header("Location", location).header("Content-Location", contentLocation).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Error: filial not found").build();
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
}
