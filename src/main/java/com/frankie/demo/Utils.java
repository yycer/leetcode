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
}
