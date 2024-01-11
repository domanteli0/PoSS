package com.spaghettininjas.yaposs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableWebFlux
public class ApiGatewayApplication {

    @Value("${customerService.hostname:localhost}")
    private String customerServiceHostname;

    @Value("${customerService.port:8081}")
    private String customerServicePort;

    @Value("${ORDER_MANAGEMENT_SERVICE_HOSTNAME:localhost}")
    private String orderManagementServiceHostname;

    @Value("${ORDER_MANAGEMENT_SERVICE_PORT:8088}")
    private String orderManagementServicePort;
  
    @Value("${STAFF_SERVICE_HOSTNAME:localhost}")
    private String staffServiceHostname;

    @Value("${STAFF_SERVICE_PORT:8085}")
    private String staffServicePort;

    @Value("${INVENTORY_SERVICE_HOSTNAME:localhost}")
    private String inventoryServiceHostname;

    @Value("${INVENTORY_SERVICE_PORT:8087}")
    private String inventoryServicePort;
  

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
                    "order-management-service",
                p -> p
                        .path("/api/Orders/**")
                        .filters(f -> f.rewritePath("/api/Orders/(?<segment>.*)", "/api/Orders/${segment}"))
                        .uri("http://" + orderManagementServiceHostname + ":" + orderManagementServicePort)
            )
            .route(
                    "order-management-service",
                    p -> p
                            .path("/api/OrderItems/**")
                            .filters(f -> f.rewritePath("/api/OrderItems/(?<segment>.*)", "/api/OrderItems/${segment}"))
                            .uri("http://" + orderManagementServiceHostname + ":" + orderManagementServicePort)
            )
            .route(
                    "order-management-service",
                    p -> p
                            .path("/api/Appointments/**")
                            .filters(f -> f.rewritePath("/api/Appointments/(?<segment>.*)", "/api/Appointments/${segment}"))
                            .uri("http://" + orderManagementServiceHostname + ":" + orderManagementServicePort)
            )
            .route(
                    "order-management-service",
                    p -> p
                            .path("/api/AppointmentTimes/**")
                            .filters(f -> f.rewritePath("/api/AppointmentTimes/(?<segment>.*)", "/api/AppointmentTimes/${segment}"))
                            .uri("http://" + orderManagementServiceHostname + ":" + orderManagementServicePort)
                "customer-service-openapi",
                p -> p
                    .path("/spec/Customers")
                    .uri("http://" + customerServiceHostname + ":" + customerServicePort)
            )
            .route(
                "staff-service",
                p -> p
                    .path("/api/Staff/**")
                    .filters(f -> f.rewritePath("/api/Staff/(?<segment>.*)", "/api/Staff/${segment}"))
                    .uri("http://" + staffServiceHostname + ":" + staffServicePort)
            )
            .route(
                "staff-service-openapi",
                p -> p
                    .path("/spec/Staff")
                    .uri("http://" + staffServiceHostname + ":" + staffServicePort)
            )
            .route(
                "inventory-service",
                p -> p
                    .path("/api/Inventory/**")
                    .filters(f -> f.rewritePath("/api/Inventory/(?<segment>.*)", "/api/Inventory/${segment}"))
                    .uri("http://" + inventoryServiceHostname + ":" + inventoryServicePort)
            )
            .route(
                "products-service",
                p -> p
                    .path("/api/Products/**")
                    .filters(f -> f.rewritePath("/api/Products/(?<segment>.*)", "/api/Products/${segment}"))
                    .uri("http://" + inventoryServiceHostname + ":" + inventoryServicePort)
            )
            .route(
                "inventory-service-openapi",
                p -> p
                    .path("/spec/Inventory")
                    .uri("http://" + inventoryServiceHostname + ":" + inventoryServicePort)
            )
            .build();
    }
}
