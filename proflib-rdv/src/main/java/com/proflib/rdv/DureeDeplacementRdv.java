package com.proflib.rdv;

import org.springframework.stereotype.Component;

@Component
public class DureeDeplacementRdv {
    public int calculDureeTrajet(int distanceKm, int vitesseKmH) throws InfiniteDurationExcption {
        if (distanceKm<0  || vitesseKmH< 0) throw new IllegalArgumentException("negative");
        if (distanceKm==0 || vitesseKmH==0) throw new InfiniteDurationExcption();
        return distanceKm*60/vitesseKmH;
    }
}
