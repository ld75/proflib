package com.proflib.transport;

public class Vehicule {
    private final String nom;
    public double distanceParcourue;
    protected double distanceAttribuee;
    private double vitesse;

    public Vehicule(String vehicule) {
        this.nom = vehicule;
    }

    public double getVitesse() {
        return this.vitesse;
    }
    public void roule(){}
}
