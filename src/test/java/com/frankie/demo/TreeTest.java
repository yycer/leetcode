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
}
