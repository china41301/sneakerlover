package com.djcao.boot.util;

import java.util.regex.Pattern;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-04
 */
public class CommonUtil {
    private static final Pattern pattern = Pattern.compile("[0-9]+$");

    public static boolean isNumber(String number){
        return pattern.matcher(number).matches();
    }
}
