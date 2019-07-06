package com.djcao.boot.util;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-03
 */
public class SwitchHelper {
    private static volatile boolean simple = true;

    public static boolean isSimple() {
        return simple;
    }

    public static void setSimple(boolean simple) {
        SwitchHelper.simple = simple;
    }
}
