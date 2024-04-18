package br.com.present.events;

import br.com.present.commons.application.MicroserviceAccessDBApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PresentEventsApplication extends MicroserviceAccessDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresentEventsApplication.class, args);
	}

}