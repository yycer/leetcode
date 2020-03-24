package com.frankie.demo;

/**
 * @author: Yao Frankie
 * @date: 2020/3/24 22:49
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
