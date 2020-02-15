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
}
