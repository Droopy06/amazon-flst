package com.amazon.services;

import com.amazon.dao.MembreMapper;
import com.amazon.models.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void saveMember(Membre membre) {
        membreMapper.saveMember(membre);
    }

    @Override
    public void updateInformation(Membre membre) {
        membreMapper.updateInformation(membre);
    }
}
