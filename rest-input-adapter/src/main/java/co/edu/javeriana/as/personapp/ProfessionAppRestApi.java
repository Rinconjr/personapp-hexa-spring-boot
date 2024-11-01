package co.edu.javeriana.as.personapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ProfessionAppRestApi {

	public static void main(String[] args) {
		log.info("Starting ProfessionAppRestApi ...");
		SpringApplication.run(PersonAppRestApi.class, args);
		log.info("Started ProfessionAppRestApi OK");
	}

}
