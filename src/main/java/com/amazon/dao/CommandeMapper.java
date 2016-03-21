package com.amazon.dao;

import com.amazon.models.Commandes;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 21/03/2016.
 */
public interface CommandeMapper {
    List<Commandes> findAllCommandeByUser(long id);
    void saveCommande(Commandes commande);
    void updateCommande(Commandes commande);
}
