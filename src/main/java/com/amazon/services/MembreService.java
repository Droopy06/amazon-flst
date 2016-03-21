package com.amazon.services;


import com.amazon.models.Membre;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by LAMOOT Alexandre on 27/01/2016.
 */
public interface MembreService {
    List<Membre> listAllMembres();
    Membre getMemberById(long id);
    Membre getMemberByName(String name,String password);
    void saveMember(Membre membre);
    void updateInformation(Membre membre);
    String getTokenByUser();
    String sha256(String base);
    String getIpAdresse(HttpServletRequest request);
}
