package com.gullerya.webapp;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Path("/api")
public class Resource implements AppResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getSomething() {
		return Response
				.status(Response.Status.OK.getStatusCode(), null)
				.entity(new Date())
				.build();
	}

	@GET
	@Path("error")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getError() {
		throw new RuntimeException("something bad");
	}
}
