package ar.com.sicos.service;

import ar.com.sicos.model.OperationInput;
import ar.com.sicos.security.AuthBasic;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api")
@Produces(MediaType.APPLICATION_JSON)
public interface OperacionesMatematicas {
	@GET
	@Path("suma/{s1}/{s2}")
	Response suma(@PathParam("s1") int a,
				  @PathParam("s2") int b);

	@GET
	@Path("resta/{s1}/{s2}")
	Response resta(@PathParam("s1") int a,
				  @PathParam("s2") int b);

	@GET
	@Path("producto/{s1}/{s2}")
	Response producto(@PathParam("s1") int a,
				   @PathParam("s2") int b);

	@AuthBasic
	@GET
	@Path("division/{s1}/{s2}")
	Response division(@PathParam("s1") int a,
				   @PathParam("s2") int b);

	@POST
	@Path("operacion")
	@Consumes(MediaType.APPLICATION_JSON)
	Response operation(OperationInput input);
}

