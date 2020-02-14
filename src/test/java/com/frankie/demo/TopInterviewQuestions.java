package com.frankie.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/2/14 20:13
 */
@SpringBootTest
public class TopInterviewQuestions {

    /**
     * Input : [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     */
    @Test
    public void question283(){
        int[] input = {0, 1, 0, 3, 12};
        int length = input.length;
        int[] output = new int[length];

        int idx = 0;
        for (int i : input) {
            if (i != 0) {
                output[idx++] = i;
            }
        }
        while (idx < length){
            output[idx++] = 0;
        }

        System.out.println(Arrays.toString(output));
    }

    /**
     * Input : [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     */
    @Test
    public void improve283(){
        int[] nums = {0, 1, 0, 3, 12};
        int zeroIdx = 0;

        for (int i = 0; i < nums.length; i++){
            if (nums[i] != 0){
                Utils.swap(nums, i, zeroIdx++);
            }
        }
        System.out.println(Arrays.toString(nums));
    }

}
