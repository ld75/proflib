package com.proflib.rdv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ContextConfiguration(classes={DureeDeplacementRdv.class})
public class DureeDeplacementRDVTest {
    @Autowired
    DureeDeplacementRdv dureeDeplacementRdv;
    @Test
    public void distanceNegativeVitesseNegative_calculDureeDeplacement_ExceptionIllegal()
    {
        Assertions.assertThrows(IllegalArgumentException.class,()->{dureeDeplacementRdv.calculDureeTrajet(-5,-5);});
    }
    @Test
    public void distanceZeroVitesseZero_calculDureeDeplacement_ExceptionInfini()
    {
        Assertions.assertThrows(InfiniteDurationExcption.class,()->{dureeDeplacementRdv.calculDureeTrajet(0,0);});
    }
    @Test
    public void distance1Vitesse1_calculDureeDeplacement_60minutes() throws InfiniteDurationExcption {
        int minutes = dureeDeplacementRdv.calculDureeTrajet(1, 1);
           Assertions.assertEquals(60,minutes);
    };

    @Test
    public void distance1Vitesse2_calculDureeDeplacement_30minutes() throws InfiniteDurationExcption {
        int minutes = dureeDeplacementRdv.calculDureeTrajet(1, 2);
        Assertions.assertEquals(30,minutes);
    };

    }
