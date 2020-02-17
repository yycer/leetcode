package com.frankie.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Yao Frankie
 * @date: 2020/2/14 20:13
 */
@SpringBootTest
public class TopInterviewQuestionsTest {

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

    /**
     * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
     * Input : [3,2,3]
     * Output: 3
     */
    @Test
    public void question169(){
        int[] nums = {3, 2, 3};

        // Step1: Build HashMap.
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num: nums){
            if (map.get(num) != null){
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // Step2: Find the majority element.
        int result = 0;
        int count  = 0;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()){
            if (entry.getValue() > count){
                count  = entry.getValue();
                result = entry.getKey();
            }
        }
    }

    /**
     * AC: Array and Constant.
     * https://leetcode.com/problems/majority-element/discuss/51898/AC-clean-Java-solution
     */
    @Test
    public void improve169UsingAC(){
        int[] nums = {3, 2, 1, 3, 3, 1, 3, 8, 3};

        int n = nums.length, m = nums[0], c = 1;
        for (int i = 1; i < n; i++){
            if (nums[i] == m){
                c++;
            } else if (c > 0){
                c--;
            } else {
                m = nums[i];
                c = 1;
            }
        }

        System.out.println("m = " + m);
    }

    /**
     * https://leetcode.com/problems/majority-element/discuss/51611/Java-solutions-(sorting-hashmap-moore-voting-bit-manipulation).
     */
    @Test
    public void improve169UsingHashMap(){

        int ret = 0;
        int[] nums = {3, 2, 3, 2, 3, 1, 3, 8, 3};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num: nums){
            if (!map.containsKey(num)){
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
            // Must be >, eg: [2, 2, 1, 1, 1, 2, 2]
            if (map.get(num) > nums.length / 2){
                ret = num;
                break;
            }
        }
        System.out.println(ret);
    }


    /**
     * Given an array of integers, find if the array contains any duplicates.
     *
     * Example 1:
     * Input: [1,2,3,1]
     * Output: true
     *
     * Example 2:
     * Input: [1,2,3,4]
     * Output: false
     *
     * Solution2: Using set
     * https://leetcode.com/problems/contains-duplicate/discuss/61030/Java-HashSet-and-sorting-solutions.
     */
    @Test
    public void question217(){
        int[] nums = {1, 2, 3, 1};
//        boolean isDuplicate = ArrayProblems.p217(nums);
        boolean isDuplicate = ArrayProblems.p217UsingSet(nums);
        Assert.assertTrue(isDuplicate);
    }

    
    /**
     * 122. Best Time to Buy and Sell Stock II
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit.
     * You may complete as many transactions as you like
     * (i.e., buy one and sell one share of the stock multiple times).
     *
     * Example 1:
     * Input: [7,1,5,3,6,4]
     * Output: 7
     *
     * Example 2:
     * Input: [1,2,3,4,5]
     * Output: 4
     */
    @Test
    public void question122(){
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        int[] prices = {1, 2, 3, 4, 5};
        int[] prices = {3, 1, 8, 4, 7};
//        int[] prices = {7, 1, 5, 3};
//        int[] prices = {7, 1, 5, 6};
//        int[] prices = {7, 1, 5, 3, 6};
//        int ret = ArrayProblems.p122(prices);
//        int ret = ArrayProblems.p122UsingMax(prices);
        int ret = ArrayProblems.p122UsingResetPoint(prices);
        Assert.assertEquals(ret, 7);
    }


    /**
     * 268. Missing Number
     * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
     *
     * Example 1:
     * Input: [3,0,1]
     * Output: 2
     *
     * Example 2:
     * Input: [9,6,4,2,3,5,7,0,1]
     * Output: 8
     */
    @Test
    public void question268(){
        int[] nums = {3, 0, 1};
//        int missingNumber = ArrayProblems.missingNumber(nums);
        int missingNumber = ArrayProblems.missingNumberUsingMath(nums);
//        int missingNumber = ArrayProblems.missingNumberUsingSum(nums);
//        int missingNumber = ArrayProblems.missingNumberUsingBitSet(nums);
        Assert.assertEquals(missingNumber, 2);
    }
}
