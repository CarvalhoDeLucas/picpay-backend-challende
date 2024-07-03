package com.carvalhodelucas.picpay_backend_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

//Habilida a auditoria JDBC que permite realizar algumas coisas, como settar o createdAtde forma automatica
@EnableJdbcAuditing
@SpringBootApplication
public class PicpayBackendChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpayBackendChallengeApplication.class, args);
	}

}
