package com.amazon.dao;

import com.amazon.models.Articles;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 25/02/2016.
 */
public interface ArticlesMapper {
    void saveArticle(Articles articles);
    List<Articles> getAllArticles();
    List<Articles> getArticlesByCategory(long id);
    List<Articles> getArticlesByYear(String date);
    Articles getArticleByName(String name);
    Articles getArticlesById(long id);
    void updateArticle(Articles articles);
    void deleteArticle(Articles articles);
}
