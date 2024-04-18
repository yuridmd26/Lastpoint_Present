package br.com.present.service.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PresentServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresentServiceRegistryApplication.class, args);
	}

}