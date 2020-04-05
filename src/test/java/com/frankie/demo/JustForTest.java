package com.frankie.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/2/14 20:30
 */
@SpringBootTest
public class JustForTest {

    @Test
    public void swapTest(){
        int[] arr = {1, 2, 3};
        int tmp = arr[0];
        arr[0]  = arr[2];
        arr[2]  = tmp;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void twoDimensionalTest(){
        int[][] arr = new int[2][3];
        arr[0] = new int[]{1, 2, 3};
        arr[1] = new int[]{10, 20, 30};
        System.out.println(Arrays.toString(arr));
    }
}
