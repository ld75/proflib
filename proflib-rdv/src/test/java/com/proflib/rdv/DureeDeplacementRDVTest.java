package com.proflib.rdv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DureeDeplacementRDVTest {

    @Test
    public void vitesseNegDistanceNeg_calculDureeDeplacement_ExceptionIllegal()
    {
        DureeDeplacementRDV dureeDeplacementRDV =new DureeDeplacementRDV();
        Assertions.assertThrows(IllegalArgumentException.class,()->{dureeDeplacementRDV.calculDureeDeplacement(-5,-5);});
    }
    @Test
    public void vitesseZeroDistanceZero_calculDureeDeplacement_InfiniException()
    {
        DureeDeplacementRDV dureeDeplacementRDV =new DureeDeplacementRDV();
        Assertions.assertThrows(InfiniException.class,()->{dureeDeplacementRDV.calculDureeDeplacement(0,0);});
    }
}
