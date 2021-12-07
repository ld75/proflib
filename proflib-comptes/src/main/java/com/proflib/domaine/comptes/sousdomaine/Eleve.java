package com.proflib.domaine.comptes.sousdomaine;

import javax.persistence.*;

@Entity
@NamedQuery(name="Eleve.findAll", query="Select e from Eleve e")
@NamedQuery(name="Eleve.findByNomPrenom", query="Select e from Eleve e where e.nom= :nom")
public class Eleve {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    Adresse adresse;
    @Embedded
    private Prenom prenom;
    @Embedded
    private Nom nom;

    public Eleve(String nom, String prenom) {
        this.nom = new Nom(nom);
        this.prenom=new Prenom(prenom);
        this.adresse=Adresse.VIDE;
    }

    public Eleve() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getNom() {
        return this.nom.nom;
    }

    public void setEmail(String email) {
    }

    @Override
    public String toString() {
        return this.nom.nom + " " +this.prenom.prenom;
    }
}
