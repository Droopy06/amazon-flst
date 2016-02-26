package com.amazon.services;

import com.amazon.models.Categorie;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 25/02/2016.
 */
public interface CategorieService {
    void saveCategorie(Categorie categorie);
    Categorie getCategorieByName(long id);
    Categorie getCategorieByName(String name);
    List<Categorie> getCategorieByFamilly(String familly);
    void updateCategorie(Categorie categorie);
    void deleteCategorie(Categorie categorie);
}
