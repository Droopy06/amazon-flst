package com.amazon.controllers;

import com.amazon.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by prog on 16/02/2016.
 */
@Controller
public class MembreController {

    @Autowired
    MembreService membreService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home() {
        return "adherent/index";
    }
}
