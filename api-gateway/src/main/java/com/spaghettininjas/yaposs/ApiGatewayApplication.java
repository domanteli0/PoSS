package com.spaghettininjas.yaposs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    @Value("${customerService.hostname:localhost}")
    private String customerServiceHostname;

    @Value("${customerService.port:8081}")
    private String customerServicePort;

    @Value("${PAYMENT_SERVICE_HOSTNAME:localhost}")
    private String paymentServiceHostname;

    @Value("${PAYMENT_SERVICE_PORT:8085}")
    private String paymentServicePort;

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(
                "customer-service",
                p -> p
                    .path("/api/Customers/**")
                    .filters(f -> f.rewritePath("/api/Customers/(?<segment>.*)", "/api/Customers/${segment}"))
                    .uri("http://" + customerServiceHostname + ":" + customerServicePort)
            )
            .route(
                "payment-service",
                p -> p
                    .path("/api/Payments/**")
                    .filters(f -> f.rewritePath("/api/Payments/(?<segment>.*)", "/api/Payments/${segment}"))
                    .uri("http://" + paymentServiceHostname + ":" + paymentServicePort)
            )
            .build();

    }

}
