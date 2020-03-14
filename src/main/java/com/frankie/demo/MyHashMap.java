package com.frankie.demo;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/3/14 13:58
 */
public class MyHashMap {

    private int[] nums = new int[1000000];

    /** Initialize your data structure here. */
    public MyHashMap() {
        Arrays.fill(nums, -1);
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        nums[key] = value;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return nums[key];
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        nums[key] = -1;
    }
}
