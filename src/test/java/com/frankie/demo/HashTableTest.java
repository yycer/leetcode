package com.frankie.demo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Yao Frankie
 * @date: 2020/4/19 14:51
 */
@SpringBootTest
public class HashTableTest {

    /**
     * 128. Longest Consecutive Sequence
     */
    @Test
    public void p128(){
        int[] nums = {100, 4, 200, 1, 3, 2};
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int n: nums){
            if (!map.containsKey(n)){
                int left  = map.containsKey(n - 1) ? map.get(n - 1) : 0;
                int right = map.containsKey(n + 1) ? map.get(n + 1) : 0;
                int sum = left + right + 1;
                map.put(n, sum);
                max = Math.max(max, sum);

                map.put(n - left,  sum);
                map.put(n - right, sum);
            } else {
                continue;
            }
        }
        System.out.println(max);
    }
}
