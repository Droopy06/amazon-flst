package com.amazon.controllers;

import com.amazon.models.Articles;
import com.amazon.models.Categorie;
import com.amazon.services.ArticlesService;
import com.amazon.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LAMOOT Alexandre on 26/02/2016.
 */
@Controller
public class ProductsController {

    @Autowired
    ArticlesService articlesService;

    @Autowired
    CategorieService categorieService;


    @RequestMapping(value = {"/products"}, method = RequestMethod.GET)
    public ModelAndView home() {
        HashMap<String, Object> model = new HashMap<String, Object>();
        List<Articles> articles = articlesService.getAllArticles();
        List<List<String>> categories = new ArrayList<>();
        for(Articles article : articles){
            Categorie maCategorie = categorieService.getCategorieByName(article.getCategorie());
            List<String> objectCategorie = new ArrayList<>();
            objectCategorie.add(maCategorie.getNom());
            objectCategorie.add(maCategorie.getFamille());
            categories.add(objectCategorie);
        }
        model.put("articles",articles);
        model.put("article",new Articles());
        model.put("categories",categories);
        model.put("support",articlesService.getAllArticles());
        model.put("allcategories",categorieService.getAllCategories());
        return new ModelAndView("amazon/products/products",model);
    }

    @RequestMapping(value = {"/product/{produit}"}, method = RequestMethod.GET)
    public ModelAndView detailProduct(@PathVariable(value = "produit") long idProduit) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        Articles article = articlesService.getArticlesById(idProduit);
        Categorie categorie = categorieService.getCategorieByName(article.getCategorie());
        model.put("article",article);
        model.put("categorie",categorie.getNom());
        model.put("support",articlesService.getAllArticles());
        model.put("allcategories",categorieService.getAllCategories());
        return new ModelAndView("amazon/products/detailproduct",model);
    }

    @RequestMapping(value = {"/product/search/{cat}"}, method = RequestMethod.GET)
    public ModelAndView searchProduct(@PathVariable(value = "cat") String nom) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        Categorie categorie = categorieService.getCategorieByName(nom);
        List<Articles> articles = articlesService.getArticlesByCategory(categorie.getId());
        model.put("articles",articles);
        model.put("article",new Articles());
        model.put("categorie",categorie.getNom());
        model.put("support",articlesService.getAllArticles());
        model.put("allcategories",categorieService.getAllCategories());
        return new ModelAndView("amazon/products/productsearch",model);
    }

    @RequestMapping(value = {"/product/search/date"}, method = RequestMethod.POST)
    public ModelAndView searchProductByDate(@ModelAttribute @Valid Articles product) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        List<Articles> articles = articlesService.getArticlesByYear(product.getDate());
        List<List<String>> categories = new ArrayList<>();
        for(Articles article : articles){
            Categorie maCategorie = categorieService.getCategorieByName(article.getCategorie());
            List<String> objectCategorie = new ArrayList<>();
            objectCategorie.add(maCategorie.getNom());
            objectCategorie.add(maCategorie.getFamille());
            categories.add(objectCategorie);
        }
        model.put("articles",articles);
        model.put("article",new Articles());
        model.put("year",product.getDate());
        model.put("support",articlesService.getAllArticles());
        model.put("allcategories",categorieService.getAllCategories());
        return new ModelAndView("amazon/products/productsearchyear",model);
    }

    @RequestMapping(value = {"/support/{cat}"}, method = RequestMethod.GET)
    public ModelAndView searchProductFormat(@PathVariable(value = "cat") String format) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        List<Articles> articles = articlesService.getArticlesByFormat(format);
        model.put("articles",articles);
        model.put("article",new Articles());
        model.put("format",format);
        model.put("support",articlesService.getAllArticles());
        model.put("categorie",categorieService.getCategorieByName(articles.get(0).getCategorie()).getNom());
        model.put("allcategories",categorieService.getAllCategories());
        return new ModelAndView("amazon/products/productsearchformat",model);
    }
}