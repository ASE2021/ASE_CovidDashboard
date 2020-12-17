package com.ase.ase.rest.controller;

import com.ase.ase.entities.BasicInformation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicInformationController {

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/BasicInformation",
            produces = MediaType.APPLICATION_JSON_VALUE

    )

    public BasicInformation getBasicInformation(){

        BasicInformation b = new BasicInformation(3,50,30);

        return b;
    }

}
