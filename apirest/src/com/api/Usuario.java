package com.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/usuario")
public class Usuario {

	@GET
	@Produces("application/xml")
	public String sayHello() {
		
		String resource = "<?xml version='1.0'?>" +
				"<hello> Hi, this is hello from XML </hello>";
		return resource;
	}
	
	@GET
	@Produces("application/json")
	public String sayHelloJSON() {
		
		String resource = null;
		return resource;
	}
	
	@GET
	@Produces("application/html")
	public String sayHelloHTML() {
		
		String resource = "<h1> Hi, this is hello from HTML </h1>";
		return resource;
	}
}
