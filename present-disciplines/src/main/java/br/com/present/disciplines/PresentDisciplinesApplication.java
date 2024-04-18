package br.com.present.disciplines;

import br.com.present.commons.application.MicroserviceAccessDBApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PresentDisciplinesApplication extends MicroserviceAccessDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresentDisciplinesApplication.class, args);
	}

}
