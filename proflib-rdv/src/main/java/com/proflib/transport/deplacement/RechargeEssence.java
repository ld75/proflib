package com.proflib.transport.deplacement;

import com.proflib.transport.MultiVehicule;

public class RechargeEssence implements VehiculeAction {
    private final int quantiteEssenceLitre;

    public RechargeEssence(int quantiteEssenceLitre) {
        this.quantiteEssenceLitre = quantiteEssenceLitre;
    }

    @Override
    public void execute(MultiVehicule multiVehicule) {
        multiVehicule.ajouteEssense(quantiteEssenceLitre);
    }
}
