package ru.basisintellect.support_smis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.basisintellect.support_smis.model.entities.ImageEntity;
import ru.basisintellect.support_smis.model.entities.UserEntity;
import ru.basisintellect.support_smis.repositories.ImageRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by safin.v on 22.11.2016.
 */
@Component
public class ImgWorker {
    @Autowired
    ImageRepository images;

    public  void setImg(UserEntity entity, MultipartFile img){
        if (!img.isEmpty()) {
            try {
                File folder = new File("pictures");

                if (!folder.exists()) {
                    folder.mkdir();
                }

                byte[] fileBytes = img.getBytes();

                String rootPath = "pictures/";
                System.out.println("File content type: " + img.getContentType());
                File newFile = new File(rootPath + img.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
                stream.write(fileBytes);
                stream.close();
                String imgLink = rootPath + img.getOriginalFilename();

                ImageEntity imageEntity = images.findByImgLink(imgLink);

                if(imageEntity == null) {
                    imageEntity = new ImageEntity();
                    imageEntity.setImgLink(rootPath + img.getOriginalFilename());
                    images.save(imageEntity);
                }
                entity.setImage(imageEntity);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("File upload is failed: " + e.getMessage());
            }
        }
    }
}
