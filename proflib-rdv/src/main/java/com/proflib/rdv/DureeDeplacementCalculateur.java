package com.proflib.rdv;

import com.proflib.transport.Train;
import org.springframework.stereotype.Component;

@Component
public class DureeDeplacementCalculateur {

    public int distanceEtVitesseToMinutes(int distanceKm, int vitesseKM_H) {
        if (distanceKm==0) return 0;
        return distanceKm*60/vitesseKM_H;
    }
    public int calculeDuree(int distance, Train train) {

        return distance;
    }
}
