package br.com.present.classes;

import br.com.present.commons.application.MicroserviceAccessDBApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PresentClassesApplication extends MicroserviceAccessDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresentClassesApplication.class, args);
	}

}