package com.api.gateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

		Function<PredicateSpec, Buildable<Route>> routeFunc = p -> p.path("/get")
				.filters(pp -> pp.addRequestHeader("MyHeader", "Token").addRequestParameter("MyParam", "Param"))
				.uri("http://httpbin.org:80");

		Function<PredicateSpec, Buildable<Route>> conversionRoute = p -> p.path("/currency-exchange/**")
				.uri("lb://currency-exchange"); // append path with uri

		Function<PredicateSpec, Buildable<Route>> exchageRoute = p -> p.path("/currency-conversion/**")
				.uri("lb://currency-conversion");

		Function<PredicateSpec, Buildable<Route>> exchageRouteFeign = p -> p.path("/currency-conversion-feign/**")
				.uri("lb://currency-conversion");

		return builder.routes().route(routeFunc).route(conversionRoute).route(exchageRoute).route(exchageRouteFeign).build();
	}

}
