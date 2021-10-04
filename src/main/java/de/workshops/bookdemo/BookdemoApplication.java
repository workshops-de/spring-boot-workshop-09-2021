package de.workshops.bookdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BookdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookdemoApplication.class, args);
	}

	@Bean
	public ObjectMapper mapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper = mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return mapper;
	}

}
