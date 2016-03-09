package com.amazon.controllers;

import com.amazon.models.Articles;
import com.amazon.services.ArticlesService;
import com.amazon.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LAMOOT Alexandre on 04/03/2016.
 */
@Controller
public class PanierController {

    @Autowired
    ArticlesService articlesService;

    @Autowired
    CategorieService categorieService;

    @RequestMapping(value = {"/panier/ajout/{article}"}, method = RequestMethod.GET)
    public String addPanier(@PathVariable(value = "article") long idProduit,
                                  HttpSession httpSession) {
        List<Articles> articles = new ArrayList<>();
        if(isPanier(httpSession)){
            articles = (List<Articles>) httpSession.getAttribute("panier");
        }
        Articles article = articlesService.getArticlesById(idProduit);
        articles.add(article);
        httpSession.setAttribute("panier",articles);
        return "redirect:/panier";
    }

    @RequestMapping(value = {"/panier"}, method = RequestMethod.GET)
    public ModelAndView myPanier(HttpSession httpSession){
        HashMap<String, Object> model = new HashMap<String, Object>();
        if(isPanier(httpSession)){
            List<Articles> articles = (List<Articles>) httpSession.getAttribute("panier");
            model.put("articles",articles);
        }
        model.put("support",articlesService.getAllArticles());
        model.put("allcategories",categorieService.getAllCategories());
        return new ModelAndView("amazon/checkout/panier",model);
    }

    @RequestMapping(value = {"/panier/delete"}, method = RequestMethod.GET)
    public String deletePanier(HttpSession httpSession) {
        if(httpSession.getAttribute("panier") != null) {
            if (!httpSession.getAttribute("panier").equals(""))
                httpSession.setAttribute("panier",null);
        }
        return "redirect:/panier";
    }

    private boolean isPanier(HttpSession session){
        boolean isConnect = false;
        if(session.getAttribute("panier") != null){
            if(!session.getAttribute("panier").equals(""))
                isConnect = true;
            else
                isConnect = false;
        }else
            isConnect = false;
        return isConnect;
    }
}
