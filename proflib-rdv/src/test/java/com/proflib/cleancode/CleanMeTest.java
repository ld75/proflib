package com.proflib.cleancode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CleanMeTest {

    @Test
    public void vide_longestZero_stringVide(){

        String res = CleanMeOriginal.longestZero("");
        System.out.println(res);
        Assertions.assertEquals("",res);
    }
    @Test
    public void espaces_longestZero_stringVide(){

        String res = CleanMeOriginal.longestZero("     ");
        Assertions.assertEquals("",res);
    }
    @Test
    public void chiffresordonnes_longestZero_unZero(){

        String res = CleanMeOriginal.longestZero("0123456789");
        Assertions.assertEquals("0",res);
    }
    @Test
    public void zerisConsecutifs_longestZero_leMaxDeZeroConsecutifs(){

        String res = CleanMeOriginal.longestZero("000123000045670089");
        Assertions.assertEquals("0000",res);
    }
}