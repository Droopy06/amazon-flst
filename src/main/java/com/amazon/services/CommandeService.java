package com.amazon.services;

import com.amazon.models.Commandes;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 21/03/2016.
 */
public interface CommandeService {
    List<Commandes> findAllCommandeByUser(long id);
    void saveCommande(Commandes commande);
    void updateCommande(Commandes commande);
}
