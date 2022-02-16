package com.proflib.rdv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = DureeDeplacementRDV.class)
public class DureeDeplacementRDVTest {

    @Autowired
    DureeDeplacementRDV dureeDeplacementRDV;
    @Test
    public void vitesseZeroDistanceZero_calculDureeTrajet_InfiniteException()
    {
        Assertions.assertThrows(InfiniteException.class,()->{dureeDeplacementRDV.calculDureeTrajet(0,0);});
    }
    @Test
    public void vitesseNegDistanceNeg_calculDureeTrajet_IllegalArgumentException()
    {
        Assertions.assertThrows(IllegalArgumentException.class,()->{dureeDeplacementRDV.calculDureeTrajet(-1,-1);});
    }
    @Test
    public void vitesseUnDistanceUn_calculDureeTrajet_soixanteMinutes() throws InfiniteException {
        int minutes = dureeDeplacementRDV.calculDureeTrajet(1, 1);
        Assertions.assertEquals(60,minutes);
    }
    @Test
    public void vitesseDeuxDistanceUn_calculDureeTrajet_trentMinutes() throws InfiniteException {
        int minutes = dureeDeplacementRDV.calculDureeTrajet(2, 1);
        Assertions.assertEquals(30,minutes);
    }

}
