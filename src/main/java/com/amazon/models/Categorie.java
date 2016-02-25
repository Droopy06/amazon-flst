package com.amazon.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by LAMOOT Alexandre on 24/02/2016.
 */
@Entity
@Table(name = "Categorie")
public class Categorie {

    @Id @GeneratedValue
    private @Getter @Setter long id;
    private @Getter @Setter String nom;
    private @Getter @Setter String famille;
}
