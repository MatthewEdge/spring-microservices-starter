# Spring Boot Microservices

Spring Boot, Netflix Zuul for the gateway, JWTs for API security, Prometheus + Grafana for metrics, Zipkin for tracing,
and Docker. 

## Running Locally

Stacks are managed locally with Docker Compose. 

* To run the application stack:
        * `docker-compose build -f app.yml && docker-compose up -f app.yml`
* To run the metrics stack:
        * `docker-compose build -f metrics.yml && docker-compose up -f metrics.yml`

## Kubernetes

Sample Kubernetes YAMLs are included in the `kube` folder.

## Design Concepts

### API Access

Routes to be consumed by clients are defined in the `zuul.routes` section of the `application.yml` in `gateway`. These
routes follow the Versioned RESTful naming convention of `/v#/noun/identifier/action`.

### JWT Security

Most APIs, sans Login, Registration, and Password Reset, are protected routes. The consumer must pass a Header of `Authorization: Bearer JWT_TOKEN`
where `JWT_TOKEN` is the issued JWT string on successful login/registration. This JWT is only valid for 4 hours. An active client may refresh
this token by calling the `/token/refresh` route with an existing, valid JWT.

Spring Security enforces this with the custom `JwtValidationFilter` class. It checks for the presence of the Authorization header, extracts the
JWT, and then verifies it is an active, properly signed JWT. We use the `Auth0` library for this. If the JWT is invalid, or if the Authorization
header is not present, the request is handed off to the Spring Security filter chain which will return a HTTP 401 (using the `accessDeniedHandler()`
configuration in SecurityConfig).

### Users

Users are validated by the `users` service. This is where the user database is accessed. No other microservice accesses this database directly.

### TODO - Tracing

Tracing is achieved through the JWT. A UUID is generated and attached to the JWT when issued. This UUID is attached to every log through the MDC 
aspect.


