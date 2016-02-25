package com.amazon.dao;

import com.amazon.models.Categorie;

/**
 * Created by LAMOOT Alexandre on 25/02/2016.
 */
public interface CategorieMapper {
    void saveCategorie(Categorie categorie);
    Categorie getCategorieByName(long id);
    void updateCategorie(Categorie categorie);
    void deleteCategorie(Categorie categorie);
}
