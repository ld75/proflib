package com.proflib.rdv;

import org.springframework.stereotype.Component;

@Component
public class DureeDeplacementRDV {
    public double calculDureeTrajetMinute(double vitesseKmH, double distanceKm) throws IllegalArgumentException, InfiniException {
        if (vitesseKmH<0 || distanceKm<0) throw new IllegalArgumentException();
        if (vitesseKmH==0 || distanceKm==0) throw new InfiniException();
        return distanceKm*60/vitesseKmH;
    }

}
