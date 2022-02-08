package com.gullerya.webapp;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JaxRSFilter implements ContainerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(JaxRSFilter.class);

	@Override
	public void filter(ContainerRequestContext ctx) {
		logger.info("filtering stuff");
		if (ctx.getLanguage() != null && "EN".equals(ctx.getLanguage().getLanguage())) {
			ctx.abortWith(
					Response.status(Response.Status.FORBIDDEN)
							.entity("Cannot access")
							.build()
			);
		}
	}
}
