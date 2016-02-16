package com.amazon.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by prog on 27/01/2016.
 */
@Entity
@Table(name = "Membre")
public class Membre {
    @Id
    @GeneratedValue
    private
    @Getter
    @Setter
    long id;
    private
    @Getter
    @Setter
    String nom;
    private
    @Getter
    @Setter
    String prenom;
    private
    @Getter
    @Setter
    String email;
    private
    @Getter
    @Setter
    String password;
    private
    @Getter
    @Setter
    String ip;
    private
    @Getter
    @Setter
    String adresse;
    private
    @Getter
    @Setter
    int age;
    private
    @Getter
    @Setter
    boolean isHaveCar;
}
