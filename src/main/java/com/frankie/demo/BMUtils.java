package com.frankie.demo;

/**
 * @author: Yao Frankie
 * @date: 2020/3/17 22:03
 * Bit Manipulation Utils.
 */
public class BMUtils {

    /**
     * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/discuss/502788/Simple-Java-Solution
     * @param num
     * @return
     */
    public static int numbersOfSteps(int num) {
        int step = 0;
        while (num > 0){
            if (num % 2 == 0) num >>= 1;
                // if (num % 2 == 0) num /= 2;
            else num--;
            // else num -= 1;
            step++;
        }
        return step;
    }

    /**
     * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/discuss/502710/javaPython-3-3-Straight-forward-codes-w-analysis.
     */
    public static int numbersOfStepsImprove(int num) {
        int step = 0;
        while (num > 0){
            step += num % 2 == 0 || num == 1 ? 1 : 2;
            num >>= 1;
        }
        return step;
    }
}
