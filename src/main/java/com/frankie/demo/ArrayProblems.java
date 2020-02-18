package com.frankie.demo;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author: Yao Frankie
 * @date: 2020/2/15 15:04
 */
public class ArrayProblems {


    public static boolean p217(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(10);
        for (int num: nums){
            if (map.containsKey(num)){
                return true;
            } else {
                map.put(num, 1);
            }
        }
        return false;
    }

    public static boolean p217UsingSet(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num: nums){
//            if (set.contains(num)){
//                return true;
//            } else {
//                set.add(num);
//            }
            if (!set.add(num)){
                return true;
            }
        }
        return false;
    }

    public static int p122(int[] prices) {
        int amount = 0;
        for (int i = 1; i < prices.length; i++){
            if (prices[i] > prices[i - 1]){
                amount += (prices[i] - prices[i - 1]);
            }
        }
        return amount;
    }

    /**
     * 优化点:
     * 1. 变量名：profit
     * 2. Math.max(0, prices[i] - prices[i - 1]);
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/39564/Another-accepted-Java-solution
     */
    public static int p122UsingMax(int[] prices){
        int profit = 0;

        for (int i = 1; i < prices.length; i++){
            profit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return profit;
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/118105/Java-Solution-With-Explanation
     */
    public static int p122UsingResetPoint(int[] prices){
        if(prices.length == 0) return 0;
        int profit = 0, low = prices[0], max = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] < prices[i - 1]){
                //reset point
                profit += max;
                max = 0;
                low = prices[i];
            }else{
                max = Math.max(max, prices[i] - low);
            }
        }
        profit += max;
        return profit;
    }

    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;

        for (int i = 0; i <= length; i++){
            if (i != nums[i]) return i;
        }
        return length;
    }

    /**
     * https://leetcode.com/problems/missing-number/discuss/69832/1%2B-lines-Ruby-Python-Java-C%2B%2B
     */
    public static int missingNumberUsingMath(int[] nums){
        int length = nums.length;
        return (length * (length + 1) / 2) - IntStream.of(nums).sum();
    }

    /**
     * https://leetcode.com/problems/missing-number/discuss/69832/1%2B-lines-Ruby-Python-Java-C%2B%2B
     */
    public static int missingNumberUsingSum(int[] nums){
        int miss = 0, i = 0;
        for (int num: nums){
            miss += ++i - num;
        }
        return miss;
    }

    /**
     * https://leetcode.com/problems/missing-number/discuss/70030/Using-BitSet-Java
     */
    public static int missingNumberUsingBitSet(int[] nums){
        int n = nums.length, i;
        BitSet bit = new BitSet();
        for (i = 0; i < n; i++)
            bit.set(nums[i]);
        for (i = 0; i < n; i++)
            if (!bit.get(i)) break;
        return i;
    }

    public static List<List<Integer>> pascalTriangle(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0){
            return result;
        } else if (numRows == 1){
            result.add(Collections.singletonList(1));
            return result;
        } else if (numRows == 2){
            result.add(Collections.singletonList(1));
            result.add(Arrays.asList(1, 1));
            return result;
        } else {
            result.add(Collections.singletonList(1));
            result.add(Arrays.asList(1, 1));
            for (int i = 2; i < numRows; i++){
                List<Integer> tmp = new ArrayList<>();
                tmp.add(1);
                List<Integer> top = result.get(i - 1);
                for (int j = 0; j < top.size() - 1; j++){
                    tmp.add(top.get(j) + top.get(j + 1));
                }
                tmp.add(1);
                result.add(tmp);
            }
        }
        return result;
    }

    /**
     * num[i][j] = num[i - 1][j - 1] + num[i - 1][j]
     * https://leetcode.com/problems/pascals-triangle/discuss/38343/Another-accepted-Java-solution
     */
    public static List<List<Integer>> pascalTriangleUsingFormula(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();

        for (int i = 0; i < numRows; i++){
            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1));
            for (int j = 1; j < i; j++){
                list.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
            }
            if (i > 0) list.add(1);
            ret.add(list);
        }
        return ret;
    }
}
