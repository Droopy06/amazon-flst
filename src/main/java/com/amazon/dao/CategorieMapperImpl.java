package com.amazon.dao;

import com.amazon.HibernateUtil;
import com.amazon.models.Articles;
import com.amazon.models.Categorie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 25/02/2016.
 */
@Repository
public class CategorieMapperImpl implements CategorieMapper {

    SessionFactory sessionFactory;

    @Override
    public void saveCategorie(Categorie categorie) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(categorie);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Categorie getCategorieByName(long id) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Categorie> categories = session.createQuery("from Categorie where id="+id).list();
        session.close();
        return categories.get(0);
    }

    @Override
    public void updateCategorie(Categorie categorie) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(categorie);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteCategorie(Categorie categorie) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(categorie);
        session.getTransaction().commit();
        session.close();
    }
}
