package com.amazon.controllers;

import com.amazon.models.Articles;
import com.amazon.models.Membre;
import com.amazon.services.ArticlesService;
import com.amazon.services.CategorieService;
import com.amazon.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * Created by prog on 16/02/2016.
 */
@Controller
public class MembreController {

    @Autowired
    MembreService membreService;

    @Autowired
    CategorieService categorieService;

    @Autowired
    ArticlesService articlesService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = {"/connexion"}, method = RequestMethod.GET)
    public ModelAndView connexion(@ModelAttribute @Valid Membre membre) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("membre",new Membre());
        model.put("article",new Articles());
        model.put("support",articlesService.getAllArticles());
        model.put("allcategories",categorieService.getAllCategories());
        return new ModelAndView("amazon/membre/connexion",model);
    }

    @RequestMapping(value = {"/connect"}, method = RequestMethod.POST)
    public String connect(@ModelAttribute @Valid Membre membre) {
        Membre mMembre = membreService.getMemberByName(membre.getNom(),membre.getPassword());
        if(mMembre.getNom() != null){
            this.httpSession.setAttribute("membre",mMembre);
            return "redirect:/account";
        }else{
            return "redirect:/connexion";
        }
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView registerUser() {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("membre",new Membre());
        model.put("article",new Articles());
        model.put("support",articlesService.getAllArticles());
        model.put("allcategories",categorieService.getAllCategories());
        return new ModelAndView("amazon/membre/register",model);
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid Membre membre) {
        membre.setActif(0);
        membre.setPrenium(0);
        membre.setCompte(250.50);
        membreService.saveMember(membre);
        return "redirect:/connexion";
    }

    @RequestMapping(value = {"/account"}, method = RequestMethod.GET)
    public String account() {
        if(this.httpSession.getAttribute("membre") != null)
            return "amazon/membre/account";
        else
            return "redirect:/connexion";
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public String update(@ModelAttribute @Valid Membre membre) {
        if(this.httpSession.getAttribute("membre") != null){
            membreService.updateInformation(membre);
        }
        return "modifyaccount";
    }

    @RequestMapping(value = {"/deconnexion"}, method = RequestMethod.GET)
    public String deconnexion() {
        if(this.httpSession.getAttribute("membre") != null){
            this.httpSession.invalidate();
        }
        return "redirect:/connexion";
    }
}
