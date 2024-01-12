package com.spaghettininjas.yaposs;

import com.google.protobuf.util.JsonFormat;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;

@SpringBootApplication
@OpenAPIDefinition()
@EnableWebMvc
public class InventoryService {

	public static void main(String[] args) {
		SpringApplication.run(InventoryService.class, args);
	}

	@Bean
	ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufJsonFormatHttpMessageConverter(
				JsonFormat.parser().ignoringUnknownFields(),
				JsonFormat.printer().omittingInsignificantWhitespace()
		);
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
			.info(new Info().title("Inventory service"))
			.addServersItem(new Server().url("http://localhost:8080"));
	}

}
