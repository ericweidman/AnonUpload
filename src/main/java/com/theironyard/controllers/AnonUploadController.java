package com.theironyard.controllers;

import com.theironyard.services.AnonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ericweidman on 3/16/16.
 */
@RestController
public class AnonUploadController {
    @Autowired
    AnonFileRepository files;



}
