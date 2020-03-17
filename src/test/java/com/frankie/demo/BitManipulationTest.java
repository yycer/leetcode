package com.frankie.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Yao Frankie
 * @date: 2020/3/17 22:03
 */
@SpringBootApplication
public class BitManipulationTest {

    /**
     * 1342. Number of Steps to Reduce a Number to Zero
     */
    @Test
    void p1342(){
        int num = 14;
        int ret = BMUtils.numbersOfSteps(num);
        Assert.assertEquals(ret, 6);
    }
}
