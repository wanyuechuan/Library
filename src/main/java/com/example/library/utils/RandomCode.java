package com.example.library.utils;

import java.util.Random;

public class RandomCode {
    private static String code;

    private static Random random ;


    public static String RecreateCode(){
        if (random == null){
            random = new Random();
        }
        code = (random.nextInt(899999) + 100000) + "";
        return code;
    }


}
