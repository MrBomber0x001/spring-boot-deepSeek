package com.meska.aidemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AidemoApplication {

	private static final Logger log = LogManager.getLogger(AidemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AidemoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ChatClient.Builder builder){
//		return args -> {
//            var client = builder.build();
//
//            String response = client.prompt().user("Tell me an interesting fact about DeepSeek!").call().content();
//            System.out.println(response);
//
//            log.info("DeepSeek Response: {}", response);
//        };
//	}
}
