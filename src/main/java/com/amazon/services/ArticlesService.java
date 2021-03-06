package com.amazon.services;

import com.amazon.models.Articles;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 25/02/2016.
 */
public interface ArticlesService {
    void saveArticle(Articles articles);
    List<Articles> getAllArticles();
    List<Articles> getAllSupport();
    List<Articles> getArticlesByCategory(long id);
    List<Articles> getArticlesByYear(String date);
    List<Articles> getArticlesByFormat(String format);
    Articles getArticleByName(String name);
    Articles getArticlesById(long id);
    void updateArticle(Articles articles);
    void deleteArticle(Articles articles);
}
