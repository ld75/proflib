package com.proflib.transport;

public class VehiculeDistance {
    public Vehicule vehicule;
    public double distance;

    public VehiculeDistance(String vehiculeNom, double distanceKm) {
        distance = distanceKm;
        vehicule= new Vehicule(vehiculeNom);
    }

    public double getVitesse() {
        return vehicule.getVitesse();
    }
}
