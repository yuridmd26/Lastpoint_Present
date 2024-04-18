package br.com.present.api.gateway;

import br.com.present.commons.application.MicroserviceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PresentApiGatewayApplication extends MicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresentApiGatewayApplication.class, args);
	}

}