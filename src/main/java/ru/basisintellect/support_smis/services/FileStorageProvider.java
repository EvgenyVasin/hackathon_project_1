package ru.basisintellect.support_smis.services;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.basisintellect.support_smis.exceptions.FileStorageException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class FileStorageProvider {
    @Autowired
    private ConfigurationProvider configurationProvider;


    public File uploadFile(File file) {
        if (!Files.exists(getUploadFolder().toPath())) {
            createUploadDir();
        }
        String pathToSave = getUploadFolder() + File.separator + UUID.randomUUID().toString();
        File savedFile = new File(pathToSave);
        try {
            FileUtils.copyFile(file, savedFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("Can't save file.");
        }
        deleteFile(file);
        return savedFile;
    }
    public List<File> getFiles() {
        return Arrays.asList(getUploadFolder().listFiles());
    }
    public File getFile(String fileName) {
        String filePath = getUploadFolder() + File.separator + fileName;
        return new File(filePath);
    }
    public void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }
    private File getUploadFolder() {
        return new File(configurationProvider.getFileUploadDirectory());
    }
    private void createUploadDir() {
        File uploadDir = new File(configurationProvider.getFileUploadDirectory());
        uploadDir.mkdirs();
    }

}
