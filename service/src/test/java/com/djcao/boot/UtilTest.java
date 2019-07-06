package com.djcao.boot;

import com.djcao.boot.util.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-04
 */

public class UtilTest {

    @Test
    public void test_isNumber(){
        String number = "123116";
        String notNumber = "123asd123";
        assert CommonUtil.isNumber(number);
        assert !CommonUtil.isNumber(notNumber);
    }
}
