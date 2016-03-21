package com.amazon.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by LAMOOT Alexandre on 21/03/2016.
 */
@Entity
@Table(name = "Commandes")
public class Commandes {

    @Id @GeneratedValue
    private @Getter @Setter long id;
    private @Getter @Setter long idMembre;
    private @Getter @Setter String date;
    private @Getter @Setter double prix;
    private @Getter @Setter String modePaiement;
    private @Getter @Setter long idArticles;
}
