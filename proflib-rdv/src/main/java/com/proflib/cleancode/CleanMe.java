package com.proflib.cleancode;

public class CleanMe {
    public static String longestZero(String str){
    int count=0;
    int maxCount=0;
    for (char c : str.toCharArray()) {
        if (c == '0') {
            count++;
        } else {
            count = 0;
        }
        maxCount = (count > maxCount) ? count : maxCount;
    }
    String ans=" " ;
    if (maxCount>0){
        for (int i = 0; i<maxCount; i++) ans+="0";
    }
    return ans;
}
}
