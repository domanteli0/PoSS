package com.spaghettininjas.yaposs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@OpenAPIDefinition()
@EnableWebMvc
public class OrderManagementService {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementService.class, args);
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Order management service"))
            .addServersItem(new Server().url("http://localhost:8080"));
    }
}