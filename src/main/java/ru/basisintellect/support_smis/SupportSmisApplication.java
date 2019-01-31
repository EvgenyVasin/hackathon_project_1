package ru.basisintellect.support_smis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class SupportSmisApplication {
	public static void main(String[] args) {
		SpringApplication.run(SupportSmisApplication.class, args);

	}
}

