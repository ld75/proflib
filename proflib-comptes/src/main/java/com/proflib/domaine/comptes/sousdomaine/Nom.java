package com.proflib.domaine.comptes.sousdomaine;

import javax.persistence.Embeddable;

@Embeddable
public class Nom {
    String nom;

    public Nom(String valeur) {
        this.nom =valeur;
    }

    public Nom() {

    }
}
