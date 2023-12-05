package com.example.library.utils;

import cn.hutool.crypto.SecureUtil;

public class Secure {
    private static final String PASS_SALT = "wanyuechuan";

    public static String secureSalt(String s) {
        return SecureUtil.md5(s + PASS_SALT);
    }
}
