package ru.basisintellect.support_smis.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationProvider {

    @Value("${spring.config.fileUploadDirectory}")
    String fileUploadDirectory;

    public String getFileUploadDirectory() {
        return fileUploadDirectory;
    }
}
