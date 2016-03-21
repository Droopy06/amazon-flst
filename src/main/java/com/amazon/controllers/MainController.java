package com.amazon.controllers;

import com.amazon.models.Articles;
import com.amazon.services.ArticlesService;
import com.amazon.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by LAMOOT Alexandre on 22/02/2016.
 */
@Controller
public class MainController {

    @Autowired
    CategorieService categorieService;

    @Autowired
    ArticlesService articlesService;

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public ModelAndView home(HttpSession httpSession) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("article",new Articles());
        model.put("support",articlesService.getAllSupport());
        model.put("allcategories",categorieService.getAllCategories());
        return new ModelAndView("amazon/home",model);
    }

}
