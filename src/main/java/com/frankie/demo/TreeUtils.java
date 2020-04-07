package com.frankie.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: Yao Frankie
 * @date: 2020/3/31 20:11
 */
public class TreeUtils {

    public static List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> list    = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;

        while(cur!=null || !stack.empty()){
            while(cur!=null){
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }

        return list;
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> retList = new ArrayList<>();
        doPathSum(root, sum, new ArrayList<>(), retList);
        return retList;
    }

    private static void doPathSum(TreeNode root, int sum, ArrayList<Integer> tmpList, List<List<Integer>> retList) {
        if (root == null) return;
        tmpList.add(root.val);
        if (root.left == null && root.right == null && sum == root.val){
            retList.add(new ArrayList<>(tmpList));
//            retList.add(tmpList);
        } else {
            doPathSum(root.left,  sum - root.val, tmpList, retList);
            doPathSum(root.right, sum - root.val, tmpList, retList);
        }
        tmpList.remove(tmpList.size() - 1);
    }
}
