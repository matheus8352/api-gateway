package com.matthew.microservices.apigateway.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(route -> route.path("/get")
						.filters(filter -> filter
								.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(route -> route.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))
				.route(route -> route.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				.build();
	}
}