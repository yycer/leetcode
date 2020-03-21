package com.frankie.demo;

import java.util.*;

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

    public static int[] sortByBits(int[] arr) {
        List<Integer> retList = new ArrayList<>(arr.length);
        Map<Integer, List<Integer>> map = new TreeMap<>();
        
        for (int n: arr){
            int bitOne = countBitOne(n);
            if (map.containsKey(bitOne)){
                map.get(bitOne).add(n);
                map.put(bitOne, map.get(bitOne));
            } else {
                List<Integer> tmpList = new ArrayList<>();
                tmpList.add(n);
                map.put(bitOne, tmpList);
            }
        }

        for (int n: map.keySet()){
            List<Integer> childList = map.get(n);
            Collections.sort(childList);
            retList.addAll(childList);
        }
        return retList.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int countBitOne(int n) {
        int count = 0;
        while (n != 0){
            // count += n % 2 == 1 ? 1 : 0;
            count += (n & 1);
            n >>= 1;
        }
        return count;
    }

    public static int[] sortByBitsImprove(int[] arr) {
        return Arrays.stream(arr).boxed()
                .sorted((a, b) -> Integer.bitCount(a) == Integer.bitCount(b) ?
                        a - b : Integer.bitCount(a) - Integer.bitCount(b))
                .mapToInt(Integer::intValue).toArray();
    }

    /**
     * https://leetcode.com/problems/number-complement/discuss/96018/Java-very-simple-code-and-self-evident-explanation
     */
    public static int findComplement(int num) {
        int n = 0;
        while (n < num)
            n = (n << 1) | 1;
        return n - num;
    }

    /**
     * https://leetcode.com/problems/number-complement/discuss/95992/Java-1-line-bit-manipulation-solution
     * https://leetcode.com/problems/number-complement/discuss/281148/Java-one-line
     */
    public static int findComplementUsingHOB(int num) {
        return ((Integer.highestOneBit(num) << 1) - 1) ^ num;
    }

    public static boolean hasAlternatingBits(int n) {
        int pre = n & 1;
        n >>= 1;
        while (n > 0){
            int cur = n & 1;
            if ((pre ^ cur) != 1) return false;
            pre = cur;
            n >>= 1;
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/binary-number-with-alternating-bits/discuss/113933/Java-super-simple-explanation-with-inline-example
     */
    public static boolean hasAlternatingBitsAmazing(int n) {
        n ^= (n >> 1);
        return (n & (n + 1)) == 0;
    }

    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        int sum = 0;
        while (n > 0){
            sum += (n & 1);
            if (sum > 1) return false;
            n >>= 1;
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/power-of-two/discuss/63972/One-line-java-solution-using-bitCount
     */
    public static boolean isPowerOfTwoUsingMethod(int n) {
        return Integer.bitCount(n) == 1 && n > 0;
    }

    /**
     * https://leetcode.com/problems/power-of-two/discuss/64127/One-line-Java-solution
     */
    public static boolean isPowerOfTwoAmazing(int n) {
        return (n & n - 1) == 0 && (n > 0);
    }

    public static boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) != 0;
    }

    /**
     * https://leetcode.com/problems/power-of-four/discuss/80495/One-line-Java-No-complex-coding-Using-only-Integer-class-methods
     */
    public static boolean isPowerOfFourAmazing(int num) {
//        return Integer.bitCount(num) == 1 && (Integer.toBinaryString(num).length() & 1) == 1;
        return Integer.bitCount(num) == 1 && Integer.numberOfTrailingZeros(num) % 2 == 0;
    }

    public static int[] countBits(int num) {
        int[] ret = new int[num + 1];
        int n = 0;
        while (n <= num){
            ret[n] = countBitOne(n++);
        }
        return ret;
    }
}
