package br.com.present.courses;

import br.com.present.commons.application.MicroserviceAccessDBApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PresentCoursesApplication extends MicroserviceAccessDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresentCoursesApplication.class, args);
	}

}