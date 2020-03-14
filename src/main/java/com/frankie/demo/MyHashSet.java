package com.frankie.demo;


/**
 * @author: Yao Frankie
 * @date: 2020/3/14 14:05
 */
public class MyHashSet {

    private int[] arr = new int[1000000];

    /** Initialize your data structure here. */
    public MyHashSet() {
    }

    public void add(int key) {
        if (arr[key] != 1)
            arr[key] = 1;
    }

    public void remove(int key) {
        arr[key] = 0;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return arr[key] == 1;
    }
}