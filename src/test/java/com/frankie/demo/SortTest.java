package com.frankie.demo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author: Yao Frankie
 * @date: 2020/4/23 19:52
 */
@SpringBootTest
public class SortTest {

    @Test
    public void sortTest(){
        int[] arr = {3, 1, 2};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int n: arr){
            queue.offer(n);
        }
        System.out.println(queue);
    }

    @Test
    public void quickSortTest(){
        int[] arr = {3, 7, 2, 4, 1, 6, 5};
        int left = 0;
        int right = arr.length - 1;
        quickSort(arr, left, right);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left > right) return;
        int pivot = arr[left];
        int i = left;
        int j = right;

        while (i <= j){
            if (arr[i] < pivot){
                i++;
            }
            if (arr[j] > pivot){
                j--;
            }


            Utils.swap(arr, i, j);
        }
        quickSort(arr, left, i);
        quickSort(arr, i + 1, right);
    }

}
