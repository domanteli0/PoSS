package com.spaghettininjas.yaposs;

import com.google.protobuf.util.JsonFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;

@SpringBootApplication
public class StaffService {

	public static void main(String[] args) {
		SpringApplication.run(StaffService.class, args);
	}

	@Bean
	ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufJsonFormatHttpMessageConverter(
				JsonFormat.parser().ignoringUnknownFields(),
				JsonFormat.printer().omittingInsignificantWhitespace()
		);
	}

}
