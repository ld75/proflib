package com.proflib.cleancode;

public class CleanMeApres {

    public static String longestZSero(String str) {

        int number = 0;
        int max = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') number++;
            else number = 0;
            max = Math.max(number, max);
        }
        return (max > 0) ? "0".repeat(max) : " ";
    }

