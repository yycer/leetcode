package com.frankie.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Yao Frankie
 * @date: 2020/3/31 20:10
 */
@SpringBootTest
public class TreeTest {

    /**
     * 94. Binary Tree Inorder Traversal
     */
    @Test
    void p94(){
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        t5.left  = t8;
        t5.right = t9;
        t2.left  = t4;
        t2.right = t5;
        t3.left  = t6;
        t3.right = t7;
        t1.left  = t2;
        t1.right = t3;

        TreeUtils.inorderTraversal(t1);
    }

    /**
     * 113. Path Sum II
     */
    @Test
    void p113(){
        TreeNode t5 = new TreeNode(5);
        TreeNode t4 = new TreeNode(4);
        TreeNode t8 = new TreeNode(8);
        TreeNode t11 = new TreeNode(11);
        TreeNode t13 = new TreeNode(13);
        TreeNode t42 = new TreeNode(42);
        TreeNode t7 = new TreeNode(7);
        TreeNode t2 = new TreeNode(2);
        TreeNode t52 = new TreeNode(52);
        TreeNode t1 = new TreeNode(1);

        t5.left   = t4;
        t5.right  = t8;
        t4.left   = t11;
        t11.left  = t7;
        t11.right = t2;
        t8.left   = t13;
        t8.right  = t42;
        t42.left  = t52;
        t42.right = t1;

        int sum = 22;
        TreeUtils.pathSum(t5, sum);
    }

    /**
     * 572. Subtree of Another Tree
     */
    @Test
    void p572(){
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;


        TreeNode t22 = new TreeNode(2);
        TreeNode t42 = new TreeNode(4);
        TreeNode t52 = new TreeNode(5);
        t22.left = t42;
        t22.right = t52;

        boolean ret = TreeUtils.isSubtree(t1, t22);
        System.out.println(ret);
    }
}
