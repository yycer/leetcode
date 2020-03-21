package com.frankie.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

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

    /**
     * 1290. Convert Binary Number in a Linked List to Integer
     */
    @Test
    void p1290(){
        int ret = 0 << 1;
        System.out.println(ret);
    }

    /**
     * 1356. Sort Integers by The Number of 1 Bits
     */
    @Test
    void p1356() {
//        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] arr = {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        int[] ret = BMUtils.sortByBits(arr);
        System.out.println(Arrays.toString(ret));

    }

    /**
     * 476. Number Complement
     */
    @Test
    void p476(){
        int num = 20;
        int ret = BMUtils.findComplement(num);
        Assert.assertEquals(ret, 11);
    }

    /**
     * 693. Binary Number with Alternating Bits
     */
    @Test
    void p693(){
        int n = 11;
        boolean ret = BMUtils.hasAlternatingBits(n);
        Assert.assertFalse(ret);
    }

    /**
     * 231. Power of Two
     */
    @Test
    void p231(){
        int n = 8;
        boolean ret = BMUtils.isPowerOfTwo(n);
        Assert.assertTrue(ret);
    }

    /**
     * 342. Power of Four
     */
    @Test
    void p342(){
        int num = 64;
        boolean ret = BMUtils.isPowerOfFour(num);
    }

    /**
     * 338. Counting Bits
     */
    @Test
    void p338(){
        int num = 5;
        int[] ret = BMUtils.countBits(num);
        System.out.println(Arrays.toString(ret));
    }
}
