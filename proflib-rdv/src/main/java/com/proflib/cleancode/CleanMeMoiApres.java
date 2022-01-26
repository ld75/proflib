package com.proflib.cleancode;

public class CleanMeMoiApres {

    public static String longestZSero(String str)
    {

        int maxCount = getMaxCountTrailingZeros(str);
        String ans = appendZeroUntill(maxCount);
        return ans;

    }

    private static int getMaxCountTrailingZeros(String str) {
        int count=0;
        int maxCount=0;
        for(char c: str.toCharArray()){
            if (c=='0'){
                count++;
            }
            else {
                count=0;
            }
            maxCount=(count>maxCount)?count:maxCount;
        }
        return maxCount;
    }

    private static String appendZeroUntill(int maxCount) {
        if (maxCount<0) return "";
        StringBuffer stringBuffer= new StringBuffer();
        for (int i = 0; i< maxCount; i++)stringBuffer.append("0");
        return stringBuffer.toString();
    }

}
