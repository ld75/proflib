package com.proflib.dict;

public enum TypeCompte {
    ELEVE("ELEVE");
    String label;

    TypeCompte(String label)
    {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
