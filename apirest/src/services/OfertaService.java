package services;

import data.Oferta;
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

@Path("/oferta")
public class OfertaService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser() {
        
        Oferta primeraOferta = new Oferta(1, 1, "localhost", "333", "2436ggb", "citroen", 5, 3,
            "manual", 5, true, "baja", 24.56, "lleno", 0);
            
        return Response.status(Response.Status.OK).entity(primeraOferta).build();
    }
}