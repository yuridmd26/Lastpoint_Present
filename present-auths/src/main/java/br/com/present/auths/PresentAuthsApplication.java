package br.com.present.auths;

import br.com.present.commons.application.MicroserviceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PresentAuthsApplication extends MicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresentAuthsApplication.class, args);
	}

}