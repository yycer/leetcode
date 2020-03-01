package com.frankie.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Yao Frankie
 * @date: 2020/3/1 18:55
 */
@SpringBootTest
public class OfferTest {

    /**
     * 两位数组：
     * 礼物的最大价值。
     */
    @Test
    void problem47(){
        int[][] nums = {
                {1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11},
                {3, 7, 16, 5}
        };

        for (int i = 1; i < nums[0].length; i++)
            nums[0][i] = nums[0][i - 1] + nums[0][i];

        for (int j = 1; j < nums.length; j++)
            nums[j][0] = nums[j - 1][0] + nums[j][0];

        for (int i = 1; i < nums.length; i++)
            for (int j = 1; j < nums[i].length; j++)
                nums[i][j] = Math.max((nums[i][j] + nums[i - 1][j]), (nums[i][j] + nums[i][j - 1]));

        Assert.assertEquals(nums[nums.length - 1][nums[0].length - 1], 53);
    }
}
