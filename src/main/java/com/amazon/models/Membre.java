package com.amazon.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by prog on 27/01/2016.
 */
@Entity
@Table(name = "Membre")
public class Membre {

    @Id @GeneratedValue
    private @Getter @Setter long id;
    //@NotNull
    private @Getter @Setter String nom;
    private @Getter @Setter String prenom;
    @NotNull
    private @Getter @Setter String email;
    private @Getter @Setter String adresse;
    private @Getter @Setter String cp;
    private @Getter @Setter String pays;
    private @Getter @Setter double compte;
    private @Getter @Setter String token;
    @NotNull @Size(min = 4,max=200)
    private @Getter @Setter String password;
    private @Getter @Setter int actif;
    private @Getter @Setter int prenium;
}
