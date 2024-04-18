package br.com.present.face.id;

import br.com.present.commons.application.MicroserviceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PresentFaceIdApplication extends MicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresentFaceIdApplication.class, args);
	}

}