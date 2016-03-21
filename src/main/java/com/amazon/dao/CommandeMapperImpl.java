package com.amazon.dao;

import com.amazon.HibernateUtil;
import com.amazon.models.Commandes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 21/03/2016.
 */
@Repository
public class CommandeMapperImpl implements CommandeMapper {

    SessionFactory sessionFactory;

    @Override
    public List<Commandes> findAllCommandeByUser(long id) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Commandes> commandes = session.createQuery("from Commandes where idMembre="+id).list();
        session.close();
        return commandes;
    }

    @Override
    public void saveCommande(Commandes commande) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(commande);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateCommande(Commandes commande) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(commande);
        session.getTransaction().commit();
        session.close();
    }
}
