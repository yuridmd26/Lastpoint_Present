package br.com.present.groups;

import br.com.present.commons.application.MicroserviceAccessDBApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PresentGroupsApplication extends MicroserviceAccessDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresentGroupsApplication.class, args);
	}

}