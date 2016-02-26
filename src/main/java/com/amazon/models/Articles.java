package com.amazon.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by LAMOOT Alexandre on 26/02/2016.
 */
@Entity
@Table(name = "Articles")
public class Articles {

    @Id @GeneratedValue
    private @Getter @Setter long id;
    private @Getter @Setter String nom;
    private @Getter @Setter float prix;
    private @Getter @Setter String description;
    private @Getter @Setter String images;
    private @Getter @Setter long categorie;
    private @Getter @Setter String format;
    private @Getter @Setter boolean dispo;
    private @Getter @Setter int qte;
    private @Getter @Setter int note;
    private @Getter @Setter String date;
}
