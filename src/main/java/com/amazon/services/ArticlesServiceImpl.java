package com.amazon.services;

import com.amazon.dao.ArticlesMapper;
import com.amazon.models.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 25/02/2016.
 */
@Service
public class ArticlesServiceImpl implements ArticlesService {

    @Autowired
    ArticlesMapper articlesMapper;

    @Override
    public void saveArticle(Articles articles) {
        articlesMapper.saveArticle(articles);
    }

    @Override
    public List<Articles> getAllArticles() {
        return articlesMapper.getAllArticles();
    }

    @Override
    public List<Articles> getAllSupport() {
        return articlesMapper.getAllSupport();
    }

    @Override
    public List<Articles> getArticlesByCategory(long id) {
        return articlesMapper.getArticlesByCategory(id);
    }

    @Override
    public List<Articles> getArticlesByYear(String date) {
        return articlesMapper.getArticlesByYear(date);
    }

    @Override
    public List<Articles> getArticlesByFormat(String format) {
        return articlesMapper.getArticlesByFormat(format);
    }

    @Override
    public Articles getArticleByName(String name) {
        return articlesMapper.getArticleByName(name);
    }

    @Override
    public Articles getArticlesById(long id) {
        return articlesMapper.getArticlesById(id);
    }

    @Override
    public void updateArticle(Articles articles) {
        articlesMapper.updateArticle(articles);
    }

    @Override
    public void deleteArticle(Articles articles) {
        articlesMapper.deleteArticle(articles);
    }
}
