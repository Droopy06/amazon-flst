package com.amazon.dao;


import com.amazon.models.Membre;

import java.util.List;

/**
 * Created by LAMOOT Alexandre on 27/01/2016.
 */
public interface MembreMapper {
    List<Membre> listAllMembres();
    Membre getMemberById(long id);
    Membre getMemberByName(String name,String password);
    void saveMember(Membre membre);
    void updateInformation(Membre membre);
}
