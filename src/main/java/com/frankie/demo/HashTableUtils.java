package com.frankie.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: Yao Frankie
 * @date: 2020/3/10 20:04
 */
public class HashTableUtils {

    public static int getLongestStringLength(String s){
        int count = 0, max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: s.toCharArray()){
            if (map.get(c) != null)
                count = 1;
            else{
                map.put(c, 1);
                count++;
            }
            max = Math.max(count, max);
        }
        return max;
    }

    /**
     * lengthOfLongestStringUsingSlidingWindow()
     */
    public static int lolsUsingSlidingWindow(String s){
        Map<Character, Integer> map = new HashMap<>();
        int j = 0, max = 0;
        for (int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            if (map.get(cur) == null){
                map.put(cur, i);
            } else{
                // Be careful: abba
                j = Math.max((map.get(cur) + 1), j);
                map.put(cur, i);
            }
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    /**
     * Java concise HashMap solution.
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/discuss/2146/Java-concise-HashMap-solution.
     */
    public static int lolsUsingSlidingWindowConcisely(String s){
        Map<Character, Integer> map = new HashMap<>();
        int ret = 0;
        for (int i = 0, start = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            if (map.containsKey(cur))
                start = Math.max(start, map.get(cur) + 1);
            map.put(cur, i);
            ret = Math.max(ret, i - start + 1);
        }
        return ret;
    }

    public static int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums){
            if (map.containsKey(n)){
                map.put(n, map.get(n) + 1);
                if (map.get(n) > (nums.length / 2)) return n;
            }
            else
                map.put(n, 1);
        }
        return 0;
    }

    public static int majorityElement2(int[] nums) {
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums){
            if (map.containsKey(n))
                map.put(n, map.get(n) + 1);
            else
                map.put(n, 1);
            if (map.get(n) > nums.length / 2){
                ret = n;
                break;
            }
        }
        return ret;
    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ret = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            int tmp = 0;
            for (int j = 0; j < nums.length; j++){
                if (nums[j] < nums[i]) tmp++;
            }
            ret[i] = tmp;
        }
        return ret;
    }

    /**
     * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/discuss/524823/Simple-Java-solution
     */
    public static int[] smallerNumbersThanCurrentImprove(int[] nums) {
        int[] tmpArr = new int[101];
        int[] retArr = new int[nums.length];
        int cur = 0;
        for (int n: nums)
            tmpArr[n]++;

        for (int i = 0; i < 101; i++){
            cur += tmpArr[i];
            tmpArr[i] = cur;
        }

        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0)
                retArr[i] = 0;
            else
                retArr[i] = tmpArr[nums[i] - 1];
        }
        return retArr;
    }

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int n: arr){
            if (map.containsKey(n))
                map.put(n, map.get(n) + 1);
            else
                map.put(n, 1);
        }

        return map.values().size() == map.values().stream().collect(Collectors.toSet()).size();
    }

    /**
     * https://leetcode.com/problems/unique-number-of-occurrences/discuss/392858/JavaPython-3-4-liner-and-2-liner-Using-Map-and-Set-w-brief-explanation-and-analysis.
     * 1. HashSet(Collection<? extends E> c)
     * 2. getOrDefault(Object key, V defaultValue)
     */
    public static boolean uniqueOccurrencesImprove1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int n: arr)
            map.put(n, map.getOrDefault(n, 0) + 1);

        return map.values().size() == new HashSet<>(map.values()).size();
    }

    /**
     * https://leetcode.com/problems/unique-number-of-occurrences/discuss/392918/Java-array-%2B-set-beat-100
     */
    public static boolean uniqueOccurrencesImprove2(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int[] tmpArr = new int[2001];
        for (int n: arr)
            tmpArr[n + 1000]++;

        for (int n: tmpArr){
            if (n != 0){
                if (set.contains(n)) return false;
                set.add(n);
            }
        }
        return true;
    }

}
