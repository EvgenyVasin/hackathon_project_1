package ru.basisintellect.support_smis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SupportSmisApplication {
	public static void main(String[] args) {
        try{
		SpringApplication.run(SupportSmisApplication.class, args);
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }
	}
}

