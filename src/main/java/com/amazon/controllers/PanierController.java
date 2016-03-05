package com.amazon.controllers;

import com.amazon.models.Articles;
import com.amazon.models.Categorie;
import com.amazon.services.ArticlesService;
import com.amazon.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
                                  HttpServletResponse response) {
        response.addCookie(new Cookie("panier",String.valueOf(idProduit)));
        return "redirect:/panier";
    }

    @RequestMapping(value = {"/panier"}, method = RequestMethod.GET)
    public ModelAndView myPanier(HttpServletRequest request,
                                 HttpServletResponse response){
        HashMap<String, Object> model = new HashMap<String, Object>();
        List<Articles> articles = new ArrayList<>();
        Cookie[] panierCookie = request.getCookies();
        if(!Objects.equals(panierCookie[0].getValue(), ""))
            articles.add(articlesService.getArticlesById(Long.valueOf(panierCookie[0].getValue())));
        model.put("articles",articles);
        model.put("support",articlesService.getAllArticles());
        model.put("allcategories",categorieService.getAllCategories());
        return new ModelAndView("amazon/checkout/panier",model);
    }

    @RequestMapping(value = {"/panier/delete"}, method = RequestMethod.GET)
    public String deletePanier(@CookieValue(value = "panier", defaultValue = "0") String panierCookie,
                            HttpServletResponse response) {
        Cookie cookie = new Cookie("panier","");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        return "redirect:/panier";
    }
}
