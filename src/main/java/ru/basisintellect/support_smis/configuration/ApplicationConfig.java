package ru.basisintellect.support_smis.configuration;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
/**
 * Created by safin.v on 01.11.2016.
 */
public class ApplicationConfig {
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("200KB");
        factory.setMaxRequestSize("200KB");
        return factory.createMultipartConfig();
    }
}
