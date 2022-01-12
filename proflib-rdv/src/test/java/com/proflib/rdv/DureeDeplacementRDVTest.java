package com.proflib.rdv;

import com.proflib.rdv.datasource.DatasourceMockConfig;
import com.proflib.rdv.vehicule.Train;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes={DatasourceMockConfig.class,DureeDeplacementCalculateur.class})
public class DureeDeplacementRDVTest {

    @Autowired
    DureeDeplacementCalculateur dureeDeplacementCalculateur;

    @Test
    public void distanceEtVitesseNulles_DistanceEtVitesseToMinutes()
    {
        int minutes = dureeDeplacementCalculateur.distanceEtVitesseToMinutes(0,0);
        Assertions.assertEquals(0,minutes);
    }
    @Test
    public void distanceEtVitesseNonNullesEgales1_DistanceEtVitesseToMinutes()
    {
        int minutes = dureeDeplacementCalculateur.distanceEtVitesseToMinutes(1,1);
        Assertions.assertEquals(60,minutes);
    }

    @Test
    public void distanceEtVitesseNonNullesEgales2_DistanceEtVitesseToMinutes()
    {
        int minutes = dureeDeplacementCalculateur.distanceEtVitesseToMinutes(2,2);
        Assertions.assertEquals(60,minutes);
    }
    @Test
    public void distanceEtVitesseNonNullesDifferents_DistanceEtVitesseToMinutes()
    {
        int minutes = dureeDeplacementCalculateur.distanceEtVitesseToMinutes(1,2);
        Assertions.assertEquals(30,minutes);
    }

    @Test
    public void distanceEtVitesseNonNullesRealistes_DistanceEtVitesseToMinutes()
    {
        int minutes = dureeDeplacementCalculateur.distanceEtVitesseToMinutes(20,50);
        Assertions.assertEquals(24,minutes);
    }


}
