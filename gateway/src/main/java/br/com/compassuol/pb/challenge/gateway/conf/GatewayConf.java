package br.com.compassuol.pb.challenge.gateway.conf;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConf {
    @Bean
    public RouteLocator categoryRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("ms-products", r -> r
                        .path("/categories/")
                        .uri("http://localhost:8081/"))
                .route("ms-products", r -> r
                        .path("/categories/{id}")
                        .uri("http://localhost:8081/"))
                .build();
    }

    @Bean
    public RouteLocator notificationRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("ms-products", r -> r
                        .path("/notifications/")
                        .uri("http://localhost:8081/"))
                .build();
    }

    @Bean
    public RouteLocator productRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("ms-products", r -> r
                        .path("/products/")
                        .uri("http://localhost:8081/"))
                .route("ms-products", r -> r
                        .path("/products/{id}")
                        .uri("http://localhost:8081/"))
                .build();
    }

    @Bean
    public RouteLocator roleRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("ms-products", r -> r
                        .path("/roles/")
                        .uri("http://localhost:8081/"))
                .route("ms-products", r -> r
                        .path("/roles/{id}")
                        .uri("http://localhost:8081/"))
                .build();
    }

    @Bean
    public RouteLocator userRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("ms-products", r -> r
                        .path("/users/")
                        .uri("http://localhost:8081/"))
                .route("ms-products", r -> r
                        .path("/users/{id}")
                        .uri("http://localhost:8081/"))
                .build();
    }
}