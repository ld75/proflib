package com.proflib.domaine.comptes.sousdomaine;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Professeur {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    Adresse adresse;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
