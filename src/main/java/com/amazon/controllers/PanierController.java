package com.amazon.controllers;

import com.amazon.models.Articles;
import com.amazon.models.Categorie;
import com.amazon.services.ArticlesService;
import com.amazon.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    public ModelAndView addPanier(@PathVariable(value = "article") long idProduit,
                                  HttpServletResponse response) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        /*List<Articles> articlesList = new ArrayList<>();
        articlesList.add(articlesService.getArticlesById(idProduit));*/
        Cookie cookie = new Cookie("panier",String.valueOf(articlesService.getArticlesById(idProduit).getId()));
        response.addCookie(cookie);
        model.put("support",articlesService.getAllArticles());
        model.put("allcategories",categorieService.getAllCategories());
        return new ModelAndView("amazon/checkout/panier",model);
    }
}
