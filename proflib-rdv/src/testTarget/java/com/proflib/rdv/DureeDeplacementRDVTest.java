package com.proflib.rdv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.testDureeDeplacementRDVTest.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes={DatasourceMockConfig.class,DureeDeplacementRDV.class})
public class DureeDeplacementRDVTest {
    @Autowired
    DureeDeplacementRDV dureeDlacementRDV;
    @Test
    public void vitesseNegDistanceNeg_calculDureeTrajetMinute_Exception()    {
        Assertions.assertThrows(IllegalArgumentException.class,()->{dureeDlacementRDV.calculDureeTrajetMinute(-1.0, -1.0);});
    }
    @Test
    public void vitesse0Distance0_calculDureeTrajetMinute_InfiniException(){
        Assertions.assertThrows(InfiniException.class,()->{dureeDlacementRDV.calculDureeTrajetMinute(0, 0);});
    }
    @Test
    public void vitesse1KmhDistance1Km_calculDureeTrajetMinute_60minutes() throws InfiniException {
        double minutes = dureeDlacementRDV.calculDureeTrajetMinute(1, 1);
        Assertions.assertEquals(60,minutes);
    }
    @Test
    public void vitesse2KmhDistance1Km_calculDureeTrajetMinute_30minutes() throws InfiniException {
        double minutes = dureeDlacementRDV.calculDureeTrajetMinute(2, 1);
        Assertions.assertEquals(30,minutes);
    }
    @Test
    public void vitesse50KmhDistance20Km_calculDureeTrajetMinute_30minutes() throws InfiniException {
        double minutes = dureeDlacementRDV.calculDureeTrajetMinute(50, 20.100);
        Assertions.assertEquals(24,minutes);
    }
}
