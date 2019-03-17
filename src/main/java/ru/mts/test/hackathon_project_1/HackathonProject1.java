package ru.mts.test.hackathon_project_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class HackathonProject1 {
	public static void main(String[] args) {
        try{
		SpringApplication.run(HackathonProject1.class, args);
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }
	}
}

