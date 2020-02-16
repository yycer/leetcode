package com.frankie.demo;

import java.util.HashMap;
import java.util.HashSet;

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
}
