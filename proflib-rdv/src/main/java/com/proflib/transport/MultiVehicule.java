package com.proflib.transport;


import com.proflib.transport.deplacement.VehiculeAction;

import java.util.ArrayList;

public class MultiVehicule {
    private final ArrayList<VehiculeAction> deplacements;
    private int litreEssence;

    public MultiVehicule(ArrayList<VehiculeAction> deplacement) {
        this.deplacements = deplacement;
    }

    public void faitLaCourse() {
        deplacements.forEach(
                deplacement->{deplacement.execute(this);}
        );
    }

    public void ajouteEssense(int litreEssenceAjoute) {
        this.litreEssence +=litreEssenceAjoute;
    }

    public int getEssence() {
        return this.litreEssence;
    }
}
