package com.amazon.services;

import com.amazon.dao.MembreMapper;
import com.amazon.models.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created by LAMOOT Alexandre on 27/01/2016.
 */
@Service
public class MembreServiceImpl implements MembreService {

    @Autowired
    MembreMapper membreMapper;

    @Override
    public List<Membre> listAllMembres() {
        return membreMapper.listAllMembres();
    }

    @Override
    public Membre getMemberById(long id) {
        return membreMapper.getMemberById(id);
    }

    @Override
    public Membre getMemberByName(String name, String password) {
        return membreMapper.getMemberByName(name,password);
    }

    @Override
    public void saveMember(Membre membre) {
        membre.setToken(this.getTokenByUser());
        membreMapper.saveMember(membre);
    }

    @Override
    public void updateInformation(Membre membre) {
        membreMapper.updateInformation(membre);
    }

    @Override
    public String getTokenByUser() {
        String characts = "abcdefghijklmnopqrstuvwxyz";
        characts += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        characts += "1234567890";
        Random rand = new Random();
        String token = "";
        for(int i=0;i < 18;i++){
            int random = (int)(Math.random()*( characts.length() + 1 ));
            token += characts.substring(random,random+1);
        }
        return token;
    }
}
