package com.amazon.controllers;

import com.amazon.dao.ArticlesMapper;
import com.amazon.dao.CategorieMapper;
import com.amazon.models.Articles;
import com.amazon.services.ArticlesService;
import com.amazon.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LAMOOT Alexandre on 22/02/2016.
 */
@Controller
public class MainController {

    @Autowired
    ArticlesService articlesService;

    @Autowired
    CategorieService categorieService;


    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String home() {
        HashMap<String, Object> model = new HashMap<String, Object>();
        List<Articles> articles = articlesService.getAllArticles();
        List<String> categories = new ArrayList<>();
        for(Articles article : articles){
            categories.add(categorieService.getCategorieByName(article.getCategorie()).getNom());
        }
        model.put("articles",articles);
        model.put("categories",categories);
        return "amazon/home";
    }

}
