package com.bill.util;

public class RandomUtil {

    private static char[] numbers = "0123456789".toCharArray();

    public static String genOtpCode(int len) {
        if(len <= 0) return "000000";

        StringBuilder result = new StringBuilder();
        for(int i=0; i<len; i++) {
            result.append(numbers[(int) (Math.random() * numbers.length)]);
        }
        return result.toString();
    }
}
