package com.amazon.controllers;

import com.amazon.models.Membre;
import com.amazon.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by LAMOOT Alexandre on 25/02/2016.
 */
@Controller
public class membreTestController {
    @Autowired
    private MembreService membreService;

    @RequestMapping(value = {"/test/membre/ajout"}, method = RequestMethod.GET)
    public String testMember() {
        Membre membre = new Membre();
        membre.setNom("LAMOOT");
        membre.setPrenom("Alexandre");
        membre.setActif(0);
        membre.setAdresse("41 rue du port");
        membre.setPassword("test");
        membre.setCp("59000");
        membre.setPays("France");
        membre.setPrenium(1);
        membre.setCompte("250");
        membreService.saveMember(membre);
        return "";
    }
}
