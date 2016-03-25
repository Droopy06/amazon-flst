package com.amazon.controllers;

import com.amazon.dao.CommandeMapper;
import com.amazon.models.Articles;
import com.amazon.models.Membre;
import com.amazon.services.ArticlesService;
import com.amazon.services.CategorieService;
import com.amazon.services.CommandeService;
import com.amazon.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    CommandeService commandeService;

    @RequestMapping(value = {"/connexion"}, method = RequestMethod.GET)
    public ModelAndView connexion(@ModelAttribute @Valid Membre membre,HttpSession httpSession) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        if(this.isConnect(httpSession)){
            return new ModelAndView("redirect:/account",model);
        }else{
            model.put("membre",new Membre());
            model.put("article",new Articles());
            model.put("support",articlesService.getAllSupport());
            model.put("allcategories",categorieService.getAllCategories());
            return new ModelAndView("amazon/membre/connexion",model);
        }
    }

    @RequestMapping(value = {"/connexion/erreur"}, method = RequestMethod.GET)
    public ModelAndView connexionErreur(@ModelAttribute @Valid Membre membre,HttpSession httpSession) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        if(this.isConnect(httpSession)){
            return new ModelAndView("redirect:/account",model);
        }else{
            model.put("membre",new Membre());
            model.put("article",new Articles());
            model.put("support",articlesService.getAllSupport());
            model.put("allcategories",categorieService.getAllCategories());
            model.put("erreur","Erreur lors de la saisie du compte ou il n'est pas activé");
            return new ModelAndView("amazon/membre/connexion",model);
        }
    }

    @RequestMapping(value = {"/connect"}, method = RequestMethod.POST)
    public String connect(@ModelAttribute @Valid Membre membre,HttpSession httpSession) {
        Membre mMembre = membreService.getMemberByName(membre.getNom(),membre.getPassword());
        if(mMembre.getNom() != null){
            httpSession.setAttribute("membre",mMembre);
            return "redirect:/account";
        }else{
            return "redirect:/connexion/erreur";
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
    public ModelAndView register(@ModelAttribute @Valid Membre membre,
                           BindingResult bindingResult) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        if(bindingResult.hasErrors()){
            model.put("erreur",bindingResult.getAllErrors());
            model.put("membre",new Membre());
            model.put("article",new Articles());
            model.put("support",articlesService.getAllArticles());
            model.put("allcategories",categorieService.getAllCategories());
            return new ModelAndView("amazon/membre/register",model);
        }
        membre.setActif(0);
        membre.setPrenium(0);
        membre.setCompte(250.50);
        membreService.saveMember(membre);
        return new ModelAndView("redirect:/connexion",model);
    }

    @RequestMapping(value = {"/account"}, method = RequestMethod.GET)
    public ModelAndView account(HttpSession httpSession) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        if(httpSession.getAttribute("membre") != null) {
            Membre membre = (Membre) httpSession.getAttribute("membre");
            model.put("compte", membre.getCompte());
            model.put("commandes",commandeService.findAllCommandeByUser(membre.getId()));
            model.put("article",new Articles());
            model.put("support",articlesService.getAllSupport());
            model.put("allcategories",categorieService.getAllCategories());
            return new ModelAndView("amazon/membre/account", model);
        }else
            return new ModelAndView("redirect:/connexion",model);
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public String update(@ModelAttribute @Valid Membre membre,HttpSession httpSession) {
        if(httpSession.getAttribute("membre") != null){
            membreService.updateInformation(membre);
        }
        return "modifyaccount";
    }

    @RequestMapping(value = {"/deconnexion"}, method = RequestMethod.GET)
    public String deconnexion(HttpSession httpSession) {
        if(httpSession.getAttribute("membre") != null){
            httpSession.setAttribute("membre",null);
        }
        return "redirect:/connexion";
    }

    private boolean isConnect(HttpSession session){
        boolean isConnect = false;
        if(session.getAttribute("membre") != null){
            if(!session.getAttribute("membre").equals(""))
                isConnect = true;
            else
                isConnect = false;
        }else
            isConnect = false;
        return isConnect;
    }
}
