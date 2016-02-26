package com.amazon.dao;

import com.amazon.HibernateUtil;
import com.amazon.models.Articles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 25/02/2016.
 */
@Repository
public class ArticlesMapperImpl implements ArticlesMapper {

    SessionFactory sessionFactory;

    @Override
    public void saveArticle(Articles articles) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(articles);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Articles> getAllArticles() {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Articles> articles = session.createQuery("from Articles").list();
        session.close();
        return articles;
    }

    @Override
    public List<Articles> getArticlesByCategory(long id) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Articles> articles = session.createQuery("from Articles where categorie="+id).list();
        session.close();
        return articles;
    }

    @Override
    public List<Articles> getArticlesByYear(String date) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Articles> articles = session.createQuery("from Articles where date=DATE('"+date+"')").list();
        session.close();
        return articles;
    }

    @Override
    public Articles getArticleByName(String name) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Articles> articles = session.createQuery("from Articles where name="+name).list();
        session.close();
        return articles.get(0);
    }

    @Override
    public Articles getArticlesById(long id) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Articles> articles = session.createQuery("from Articles where id="+id).list();
        session.close();
        return articles.get(0);
    }

    @Override
    public void updateArticle(Articles articles) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(articles);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteArticle(Articles articles) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(articles);
        session.getTransaction().commit();
        session.close();
    }
}
