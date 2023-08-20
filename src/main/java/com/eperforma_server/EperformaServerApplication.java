package com.eperforma_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
@EntityScan("com.eperforma_server.entity")
public class EperformaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EperformaServerApplication.class, args);
	}

}
