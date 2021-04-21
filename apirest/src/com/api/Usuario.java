package com.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

@Path("/usuario")
public class Usuario {

//	@GET
//	@Produces(MediaType.TEXT_XML)
//	public String sayHello() {
//		
//		String resource = "<?xml version='1.0'?>" +
//				"<hello> Hi, this is hello from XML </hello>";
//		return resource;
//	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response sayHelloJSON() {
//		
//		return Response.ok("Hello World desde el API REST",MediaType.APPLICATION_JSON).build();
//	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHTML() {
		
		String respuesta = null;
		String resultado = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root", "");
			
		    stmt = conn.createStatement();
//		    rs = stmt.executeQuery("SELECT foo FROM bar");

		    // or alternatively, if you don't know ahead of time that
		    // the query will be a SELECT...

		    if (stmt.execute("SELECT * FROM `trabajadores`")) {
		    	
		        rs = stmt.getResultSet();
		        
		        while (rs.next()) {
		        	
		            resultado = "ID: " + rs.getInt(1) + ". ";
		            resultado = resultado + "Correo electronico: " + rs.getString(2) + ". ";
		            resultado = resultado + "Contraseña: " + rs.getString(3) + ". ";
		        }
		        
		        respuesta = "Database is connected !";
		    }

		    // Now do something with the ResultSet ....
			
			conn.close();
			
		} catch (Exception e) {
			
			respuesta = "Do not connect to DB - Error:" + e;
		} finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		    
		    if (conn != null) {
		    	try {
		            conn.close();
		        } catch (SQLException sqlEx) { }
		    	
		    	conn = null;
		    }
		}
		
		
		String resource = "<!DOCTYPE html>"
				+ "<html lang=\"es\">"
				+ "<head>"
				+ "		<meta charset=\"utf-8\">"
				+ "</head> \n"
				+ respuesta
				+ resultado;
		return resource;
	}
}
