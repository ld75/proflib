package com.proflib.domaine.comptes.sousdomaine;

import javax.persistence.Embeddable;

@Embeddable
public class Prenom {
    String prenom;

    public Prenom(String valeur) {
        this.prenom =valeur;
    }

    public Prenom() {

    }
}
