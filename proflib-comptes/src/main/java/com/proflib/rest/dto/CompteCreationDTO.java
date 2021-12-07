package com.proflib.rest.dto;

import com.proflib.dict.TypeCompte;

import java.lang.reflect.Type;

public class CompteCreationDTO {
    String nom;
    String prenom;
    String email;
    String mdp;
    String comfirmmdp;
    String typeCompte;

    public CompteCreationDTO() {
    }

    public CompteCreationDTO(String nom, String prenom, String email, String mdp, String comfirmmdp, String typeCompte) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.comfirmmdp = comfirmmdp;
        this.typeCompte = typeCompte;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getComfirmmdp() {
        return comfirmmdp;
    }

    public void setComfirmmdp(String comfirmmdp) {
        this.comfirmmdp = comfirmmdp;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    @Override
    public String toString() {
        return "CompteCreationDTO{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", comfirmmdp='" + comfirmmdp + '\'' +
                ", typeCompte='" + typeCompte + '\'' +
                '}';
    }
}
