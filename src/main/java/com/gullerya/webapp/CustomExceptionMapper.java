package com.gullerya.webapp;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionMapper implements ExceptionMapper<Throwable> {
	private static final Logger logger = LoggerFactory.getLogger(CustomExceptionMapper.class);

	@Override
	public Response toResponse(Throwable t) {
		logger.error("{} - {}", t.getClass().getCanonicalName(), t.getMessage());
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), null)
				.entity(t.getMessage())
				.build();
	}
}
