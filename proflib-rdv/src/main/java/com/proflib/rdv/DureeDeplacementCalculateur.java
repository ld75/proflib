package com.proflib.rdv;

import com.proflib.rdv.vehicule.Train;
import org.springframework.stereotype.Component;

@Component
public class DureeDeplacementCalculateur {

    public int distanceEtVitesseToMinutes(int distanceKm, int vitesseKM_H) {
        if (distanceKm==0) return 0;
        return distanceKm*60/vitesseKM_H;
    }
}
