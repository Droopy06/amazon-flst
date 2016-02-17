package com.amazon.controllers;

import com.amazon.models.Membre;
import com.amazon.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by prog on 16/02/2016.
 */
@Controller
public class MembreController {

    @Autowired
    MembreService membreService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = {"/connect"}, method = RequestMethod.POST)
    public java.lang.String connect(@ModelAttribute @Valid Membre membre) {
        Membre mMembre = membreService.getMemberByName(membre.getNom(),membre.getPassword());
        if(mMembre.getId() != 1L){
            this.httpSession.setAttribute("membre",mMembre);
            return "account";
        }else{
            return "connect";
        }
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public java.lang.String update(@ModelAttribute @Valid Membre membre) {
        if(this.httpSession.getAttribute("membre") != null){
            membreService.updateInformation(membre);
        }
        return "modifyaccount";
    }

    @RequestMapping(value = {"/deconnexion"}, method = RequestMethod.POST)
    public java.lang.String deconnexion() {
        if(this.httpSession.getAttribute("membre") != null){
            this.httpSession.invalidate();
        }
        return "redirect:/connect";
    }
}
