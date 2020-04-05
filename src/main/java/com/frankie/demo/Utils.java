package com.frankie.demo;

/**
 * @author: Yao Frankie
 * @date: 2020/2/14 20:29
 */
public class Utils {

    public static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i]  = a[j];
        a[j]  = t;
    }

    public static void swap(int[][] a, int i, int j){
        int t   = a[i][j];
        a[i][j] = a[j][i];
        a[j][i] = t;
    }

    public static int climbStairs(int n) {
        if (n <= 2) return n;
        return climbStairs(n - 2) + climbStairs(n - 1);
    }
}
