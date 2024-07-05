package com.carvalhodelucas.picpay_backend_challenge;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.kafka.config.TopicBuilder;

//Habilida a auditoria JDBC que permite realizar algumas coisas, como settar o createdAtde forma automatica
@EnableJdbcAuditing
@SpringBootApplication
public class PicpayBackendChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpayBackendChallengeApplication.class, args);
	}

	@Bean
	NewTopic notificationTopic() {
		return TopicBuilder.name("transaction-notification").build();
	}

}
