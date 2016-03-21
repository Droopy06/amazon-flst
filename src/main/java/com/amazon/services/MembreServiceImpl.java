package com.amazon.services;

import com.amazon.dao.MembreMapper;
import com.amazon.models.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
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
        password = sha256(password);
        return membreMapper.getMemberByName(name,password);
    }

    @Override
    public void saveMember(Membre membre) {
        membre.setToken(this.getTokenByUser());
        membre.setPassword(sha256(membre.getPassword()));
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

    /**
     * For encoding the password in sha256
     *
     * @param base
     * @return
     */
    public String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Get ip address of user
     *
     * @param request
     * @return
     */
    public String getIpAdresse(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
