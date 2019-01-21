package ru.basisintellect.support_smis.controllers;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.basisintellect.support_smis.model.entities.CustomImgEntity;
import ru.basisintellect.support_smis.model.entities.ImageEntity;
import ru.basisintellect.support_smis.model.entities.UserEntity;
import ru.basisintellect.support_smis.repositories.UsersRepository;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by safin.v on 01.11.2016.
 */
@Controller
@ResponseBody
public class ImageController {
    @Autowired
    UsersRepository users;

    //@Autowired
    //ImageRepository images;

    @RequestMapping(value = "/imgUser={userId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getUserImg(@PathVariable(value = "userId") Long  userId) throws IOException {
        UserEntity userEntity = users.findById(userId).get();
        return getImg(userEntity, getClass().getResource("/static/images/noavatar.png").getFile());
    }

//    @RequestMapping(value = "/imgUser={username}", method = RequestMethod.GET)
//    public ResponseEntity<byte[]> getUserImg(@PathVariable(value = "username") String  username) throws IOException {
//        UserEntity user = users.findByUsername(username);
//        return getImg(user, getClass().getResource("/static/images/noavatar.png").getFile());
//    }


    private ResponseEntity<byte[]> getImg(CustomImgEntity detal) throws IOException{
        return getImg(detal, "");
    }

    /**
     * @param detal объект базы данных содержащий ссылку на картинку
     * @param altImg альтернативная ссылка на картинку если в объекте нет ссылки
     */
    private ResponseEntity<byte[]> getImg(CustomImgEntity detal, String altImg) throws IOException{
        ByteArrayOutputStream out = null;
        InputStream input = null;

        ImageEntity imageEntity = detal.getImageEntity();

        String imgLink;

        if(imageEntity != null)
            imgLink = imageEntity.getImgLink();
        else
            imgLink = altImg;

        try {
            out = new ByteArrayOutputStream();
            input = new BufferedInputStream(new FileInputStream(imgLink));
            int data = 0;
            while ((data = input.read()) != -1) {
                out.write(data);
            }
        } finally {
            if (null != input) {
                input.close();
            }
            if (null != out) {
                out.close();
            }
        }
        byte[] bytes = out.toByteArray();

        final HttpHeaders headers = new HttpHeaders();

        //headers.setContentType(MediaType.parseMediaType(detal.getImgContentType()));

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
    }
}
