package com.gullerya.webapp;

import jakarta.servlet.*;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;

@ApplicationPath("/rest")
public class App extends Application implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	private final Map<String, Object> instances;

	public App() {
		logger.info("constructing the web app");
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.gullerya.webapp");
		Map<String, AppResource> resources = applicationContext.getBeansOfType(AppResource.class);
		Map<String, ContainerRequestFilter> requestFilters = applicationContext
				.getBeansOfType(ContainerRequestFilter.class);
		Map<String, ContainerResponseFilter> responseFilters = applicationContext
				.getBeansOfType(ContainerResponseFilter.class);
		Map<String, ExceptionMapper> exceptionMappers = applicationContext.getBeansOfType(ExceptionMapper.class);

		Map<String, Object> tmp = new HashMap<>(resources);
		tmp.putAll(requestFilters);
		tmp.putAll(responseFilters);
		tmp.putAll(exceptionMappers);
		instances = Collections.unmodifiableMap(tmp);
	}

	@Override
	public Set<Object> getSingletons() {
		return new HashSet<>(instances.values());
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("context initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("context destroyed");
	}
}
