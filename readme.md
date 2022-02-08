# JAX-RS template for Docker

This project is a copy-pasteable template of a pure JAX-RS application, including the setup to build it into a servlet container based Docker.

Main aspects:
- application uses ONLY JAX-RS annotations
- bean management by Spring
- logging by SLF4J + LOG4J2
- JAX-RS by Jersey (runtime dependency, no Jersey imports / awareness in the code)
- Docker based deployment, several servlet containers example

JAX-RS features implemented:
- main application initializer
- Resource
- Filter
- ExceptionMapper

Servlet containers:
- Jetty
- Tomcat
- Wildfly (WIP, not yet working)