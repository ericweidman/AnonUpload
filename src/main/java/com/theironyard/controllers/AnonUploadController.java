package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


/**
 * Created by ericweidman on 3/16/16.
 */
@RestController
public class AnonUploadController {
    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response) throws Exception {

        if (files.count() > 10) {
            files.delete(1);
            File fd = new File("public/files" + file.getName());
            fd.delete();
            long saveNext = files.count();


            File dir = new File("public/files");
            dir.mkdirs();
            File f = File.createTempFile("file" + saveNext, file.getOriginalFilename(), dir);
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(file.getBytes());
            AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename());
            files.save(anonFile);
            response.sendRedirect("/");
        }
    }
    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() {
        return (List<AnonFile>) files.findAll();

    }
}
