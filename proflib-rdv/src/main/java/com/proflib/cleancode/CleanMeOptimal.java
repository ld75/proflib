package com.proflib.cleancode;

public class CleanMeOptimal {

    public static String getPlusgrandZerosDafiles(String str) {

        int max = getMaxZerosQuiSenchainent(str);
        return repeteZeroFois(max);
    }

    private static String repeteZeroFois(int max) {
        return (max > 0) ? "0".repeat(max) : " ";
    }

    private static int getMaxZerosQuiSenchainent(String str) {
        int number = 0;
        int max = 0;
        for (int charidx = 0; charidx < str.length(); charidx++) {
            if (str.charAt(charidx) == '0') number++;
            else number = 0;
            max = Math.max(number, max);
        }
        return max;
    }
}

