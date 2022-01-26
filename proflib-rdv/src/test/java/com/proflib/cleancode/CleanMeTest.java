package com.proflib.cleancode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CleanMeTest {

    @Test
    public void vide_longestZSero(){

        String res = CleanMeMoiApres.longestZSero("");
        System.out.println(res);
        Assertions.assertEquals("",res);
    }
    @Test
    public void espaces_longestZSero(){

        String res = CleanMeMoiApres.longestZSero("     ");
        Assertions.assertEquals("",res);
    }
    @Test
    public void chiffres_longestZSero(){

        String res = CleanMeMoiApres.longestZSero("0  000 120030450670890");
        Assertions.assertEquals("000",res);
    }
}