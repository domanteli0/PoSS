package com.spaghettininjas.yaposs;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@org.springframework.context.annotation.Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RestClient.Builder getRestClientBuilder() {
        return RestClient.builder();
    }
}
