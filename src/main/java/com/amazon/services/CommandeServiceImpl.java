package com.amazon.services;

import com.amazon.dao.CommandeMapper;
import com.amazon.models.Commandes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 21/03/2016.
 */
@Service
public class CommandeServiceImpl implements CommandeService{

    @Autowired
    CommandeMapper commandeMapper;

    @Override
    public List<Commandes> findAllCommandeByUser(long id) {
        return commandeMapper.findAllCommandeByUser(id);
    }

    @Override
    public void saveCommande(Commandes commande) {
        commandeMapper.saveCommande(commande);
    }

    @Override
    public void updateCommande(Commandes commande) {
        commandeMapper.updateCommande(commande);
    }
}
