package com.amazon.services;

import com.amazon.models.Categorie;

/**
 * Created by LAMOOT Alexandre on 25/02/2016.
 */
public interface CategorieService {
    void saveCategorie(Categorie categorie);
    Categorie getCategorieByName(long id);
    void updateCategorie(Categorie categorie);
    void deleteCategorie(Categorie categorie);
}
