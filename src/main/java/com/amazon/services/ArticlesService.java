package com.amazon.services;

import com.amazon.models.Articles;

/**
 * Created by LAMOOT Alexandre on 25/02/2016.
 */
public interface ArticlesService {
    void saveArticle(Articles articles);
    Articles getAllArticles();
    Articles getArticleByName(String name);
    Articles getArticlesById(long id);
    void updateArticle(Articles articles);
    void deleteArticle(Articles articles);
}
