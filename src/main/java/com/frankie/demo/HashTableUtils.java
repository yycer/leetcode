package com.frankie.demo;

import java.util.HashMap;
import java.util.Map;

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

}
