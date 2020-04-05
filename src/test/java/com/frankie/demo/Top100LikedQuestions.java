package com.frankie.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Yao Frankie
 * @date: 2020/4/5 09:50
 */
@SpringBootTest
public class Top100LikedQuestions {

    /**
     * 70. Climbing Stairs
     */
    @Test
    void p70(){
        int n = 5;
        int ret = Utils.climbStairs(n);
        Assert.assertEquals(ret, 8);
    }
}
