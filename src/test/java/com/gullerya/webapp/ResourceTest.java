package com.gullerya.webapp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ResourceTest {

	@BeforeAll
	public static void initSuite() throws Exception {
		Server server = new Server(8080);
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		server.setHandler(contexts);
		ResourceConfig resourceConfig = ResourceConfig.forApplicationClass(App.class);
		ServletContainer servletContainer = new ServletContainer(resourceConfig);
		ServletHolder jaxrs = new ServletHolder(servletContainer);
		ServletContextHandler mainHandler = new ServletContextHandler(contexts, "/", true, false);
		mainHandler.addServlet(jaxrs, "/*");
		jaxrs.start();
		server.start();
	}

	@Test
	void testSomething() throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("http://localhost:8080/api"))
				.GET()
				.build();
		HttpClient client = HttpClient.newBuilder().build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		Assertions.assertEquals(200, response.statusCode());
		System.out.println(response.body());
	}
}
