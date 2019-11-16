package com.books.biblio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.books.biblio.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"ccom.books.biblio"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class SpringMVCapp {

	public static void main(String[] args) {
		SpringApplication.run(SpringMVCapp.class, args);
	}
}
