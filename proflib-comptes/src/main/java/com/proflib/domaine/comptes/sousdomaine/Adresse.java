package com.proflib.domaine.comptes.sousdomaine;


import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Adresse {

    public static final Adresse VIDE = new Adresse();
    @Embedded
    Rue rue;
    @Embedded
    CodePostal codePostal;
}
