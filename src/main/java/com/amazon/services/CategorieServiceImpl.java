package com.amazon.services;

import com.amazon.dao.CategorieMapperImpl;
import com.amazon.models.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 25/02/2016.
 */
@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    CategorieMapperImpl categorieMapper;

    @Override
    public void saveCategorie(Categorie categorie) {
        categorieMapper.saveCategorie(categorie);
    }

    @Override
    public Categorie getCategorieByName(long id) {
        return categorieMapper.getCategorieByName(id);
    }

    @Override
    public Categorie getCategorieByName(String name) {
        return categorieMapper.getCategorieByName(name);
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieMapper.getAllCategories();
    }

    @Override
    public List<Categorie> getCategorieByFamilly(String familly) {
        return categorieMapper.getCategorieByFamilly(familly);
    }

    @Override
    public void updateCategorie(Categorie categorie) {
        categorieMapper.updateCategorie(categorie);
    }

    @Override
    public void deleteCategorie(Categorie categorie) {
        categorieMapper.deleteCategorie(categorie);
    }
}
