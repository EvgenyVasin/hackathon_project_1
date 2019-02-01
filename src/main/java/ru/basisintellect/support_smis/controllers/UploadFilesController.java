package ru.basisintellect.support_smis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadFilesController {
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("files") MultipartFile[] files) {

//        if (files.length ==0) {

        try {
            for (MultipartFile file : files) {


                byte[] fileBytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");
                System.out.println("Server rootPath: " + rootPath);
                System.out.println("File original name: " + file.getOriginalFilename());
                System.out.println("File content type: " + file.getContentType());
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "File upload is failed: " + e.getMessage();
        }
//        } else {
//            return "File upload is failed: File is empty";
//        }
    }
}
