package com.frankie.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

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


    /**
     * 118. Pascal's Triangle
     * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
     *
     * Input: 5
     * Output:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     */
    @Test
    public void question118(){
        int numRows = 5;
//        List<List<Integer>> result = ArrayProblems.pascalTriangle(numRows);
        List<List<Integer>> result = ArrayProblems.pascalTriangleUsingFormula(numRows);
//        System.out.println(result);
    }

    /**
     * 53. Maximum Subarray
     */
    @Test
    public void question53(){
//        int[] nums = {-2, 1, 3, -7, 3, 6};
        int[] nums = {-2, -1};
//        ArrayProblems.maximumSubarray(nums);
        ArrayProblems.maximumSubarray2(nums);
    }


    /**
     * 26. Remove Duplicates from Sorted Array
     */
    @Test
    public void question26(){
        int[] nums = {1, 1, 2};
        int result = ArrayProblems.p26(nums);
        System.out.println(result);
    }

    /**
     * 78. Subsets
     *
     * Input: nums = [1,2,3]
     * Output:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     *
     * https://leetcode.com/problems/subsets/discuss/299755/3-Java-solutions%3A-bit-iterative-and-recursive
     * https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
     */
    @Test
    public void problem78(){
        int[] nums = {1, 2, 3};
        List<List<Integer>> lists = ArrayProblems.p78(nums);
        Assert.assertEquals(lists.size(), 8);
    }

    /**
     * 189. Rotate Array
     */
    @Test
    public void problem189(){
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        ArrayProblems.p189(nums, k);
        Assert.assertArrayEquals(nums, new int[]{5, 6, 7, 1, 2, 3, 4});
    }

    /**
     * 62. Unique Paths
     * https://leetcode.com/problems/unique-paths/discuss/241181/Java-Solutions
     * https://leetcode.com/problems/unique-paths/discuss/23159/Java-different-solutions-(math-dp(O(m*n)-O(n)-space)).
     */
    @Test
    void problem62(){
        int m = 6, n = 4;
//        int m = 9, n = 8;
//        int ret = ArrayProblems.uniquePathsUsingMath(m, n);
//        int ret = ArrayProblems.uniquePathsUsingTwoDimenArray(m, n);
        int ret = ArrayProblems.uniquePathsUsingTwoOneArray(m, n);
        Assert.assertEquals(ret, 56);
//        Assert.assertEquals(ret, 6435);
    }


    /**
     * 287. Find the Duplicate Number
     *
     * Example 1:
     * Input: [1,3,4,2,2]
     * Output: 2
     *
     * Example 2:
     * Input: [3,1,3,4,2]
     * Output: 3
     */
    @Test
    void problem287(){
//        int[] nums = {1, 3, 4, 2, 2};
        int[] nums = {1, 5, 6, 2, 8, 3, 4, 2, 7, 9};
//        ArrayProblems.findTheDupNum(nums);
        int ret = ArrayProblems.findTheDupNum2(nums);
        Assert.assertEquals(ret, 2);
    }

    /**
     * 48. Rotate Image
     *
     * Example 1:
     * Given input matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * rotate the input matrix in-place such that it becomes:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     *
     * https://leetcode.com/problems/rotate-image/discuss/226001/Java-Solutions
     * https://leetcode.com/problems/rotate-image/discuss/19057/Java-simple-code
     */
    @Test
    void problem48(){
        int[][] matrix = {
            {1,  2,  3,  4,  5},
            {6,  7,  8,  9,  10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
        };

//        ArrayProblems.rotateImage(matrix);
        ArrayProblems.rotateImageAntiClockwise(matrix);
//        ArrayProblems.rotateImgUsingTDT(matrix);
        for (int[] m: matrix)
            System.out.println(Arrays.toString(m));
    }

    /**
     * 11. Container With Most Water
     *
     * Input: [1,8,6,2,5,4,8,3,7]
     * Output: 49
     */
    @Test
    void problem11(){
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int ret = ArrayProblems.containMostWater(height);
        int ret = ArrayProblems.containMostWaterConcisely(height);
        Assert.assertEquals(ret, 49);
    }

    /**
     * 3. Longest Substring Without Repeating Characters
     */
    @Test
    void problem3(){
        String s = "abcabcbb";
//        int ret = HashTableUtils.getLongestStringLength(s);
        int ret = HashTableUtils.lolsUsingSlidingWindow(s);
        Assert.assertEquals(ret, 3);
    }


    /**
     * 169. Majority Element
     */
    @Test
    void problem169(){
        int[] nums = {3, 2, 3};
        HashTableUtils.majorityElement(nums);
    }

    /**
     * 1365. How Many Numbers Are Smaller Than the Current Number
     */
    @Test
    void problem1365() {
        int[] nums = {5, 1, 2, 2, 3};
//        int[] ret = HashTableUtils.smallerNumbersThanCurrent(nums);
        int[] ret = HashTableUtils.smallerNumbersThanCurrentImprove(nums);
        System.out.println(Arrays.toString(ret));
    }

    /**
     * 1207. Unique Number of Occurrences
     */
    @Test
    void problem1207(){
        int[] arr = {1, 2, 2, 1, 1, 3};
//        HashTableUtils.uniqueOccurrences(arr);
        HashTableUtils.uniqueOccurrencesImprove2(arr);
    }

    @Test
    void playListAndMap(){
        List<Map<String, Object>> list = new ArrayList<>();

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("intro", "xxx");
        map1.put("time", "123");

        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("intro", "desc");
        map2.put("time", "8996748312");

        list.add(map1);
        list.add(map2);
        System.out.println(list);

        List<HashMap<String, Object>> ret = list.stream().map(x -> {
            HashMap<String, Object> tmpMap = new HashMap<>();
            tmpMap.put("intro", x.get("intro"));
            return tmpMap;
        }).collect(Collectors.toList());

        List<String> intro = list.stream().map(x -> (String) x.get("intro"))
                .collect(Collectors.toList());

        System.out.println(intro);

//        list.stream().map(x -> x.get("intro"))

        System.out.println(ret);
    }

    @Test
    void getDiffSet(){
        ArrayList<String> dbNames = new ArrayList<>();
        dbNames.add("yyc");
        dbNames.add("asan");

        ArrayList<String> inputNames = new ArrayList<>();
        inputNames.add("yyc");
        inputNames.add("pangzi");

        dbNames.removeAll(inputNames);
        System.out.println(dbNames);
    }

    /**
     * 1160. Find Words That Can Be Formed by Characters
     */
    @Test
    void problem1160(){
        String[] words = {"cat", "bt", "hat", "tree"};
        String   chars = "atach";
        int ret = HashTableUtils.countCharacters(words, chars);
        Assert.assertEquals(ret, 6);
//        HashTableUtils.countCharactersImprove(words, chars);
    }

    /**
     * 1002. Find Common Characters
     */
    @Test
    void problem1002() {
//        String[] A = {"bella", "label", "roller"};
        String[] A = {"abcd", "abd", "acd"};
        HashTableUtils.findCommonCharacters(A);
    }
}
