package co.edu.javeriana.as.personapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.javeriana.as.personapp.terminal.menu.MenuPrincipal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class StudyAppCli implements CommandLineRunner {
	
	@Autowired
	private MenuPrincipal menuPrincipal;

	public static void main(String[] args) {
		log.info("Starting StudyAppCli ...");
		SpringApplication.run(PersonAppCli.class, args);
		log.info("Started StudyAppCli OK");
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("EXECUTING : command line runner");
		menuPrincipal.inicio();
		log.info("FINISHED : command line runner");
	}

}
