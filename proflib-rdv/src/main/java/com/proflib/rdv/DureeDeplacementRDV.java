package com.proflib.rdv;

import org.springframework.stereotype.Component;

@Component
public class DureeDeplacementRDV {
    public int calculDureeTrajet(int vitesseKmh, int distanceKm) throws InfiniteException {
        if (vitesseKmh==0) throw new InfiniteException();
        if (vitesseKmh==-1 || distanceKm==-1) throw new IllegalArgumentException();
        return distanceKm*60/vitesseKmh;
    }
}
