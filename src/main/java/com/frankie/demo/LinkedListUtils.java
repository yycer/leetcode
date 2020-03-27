package com.frankie.demo;

/**
 * @author: Yao Frankie
 * @date: 2020/3/24 22:50
 */
public class LinkedListUtils {

    public static ListNode head = null;

    public static void addNode(int val){
        ListNode cur = head;
        if (cur == null){
            head = new ListNode(val);
            return;
        }
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = new ListNode(val);
    }

    public static ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode curNode   = dummyNode;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                curNode.next = l1;
                l1 = l1.next;
            } else {
                curNode.next = l2;
                l2 = l2.next;
            }
            curNode = curNode.next;
        }

        if (l1 != null){
            curNode.next = l1;
        }

        if (l2 != null){
            curNode.next = l2;
        }
        return dummyNode.next;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode dummyNode = new ListNode(-1);
        ListNode curNode   = dummyNode;

        while (head != null){
            if (curNode.val != head.val){
                curNode.next = head;
                curNode = curNode.next;
            }
            head = head.next;
        }
        return dummyNode.next;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int lenA = getListNodeLength(headA);
        int lenB = getListNodeLength(headB);
        int diff = Math.abs(lenA - lenB);
        if (lenA < lenB){
            walkDiffStep(headB, diff);
        } else {
            walkDiffStep(headA, diff);
        }

        while (headA != headB){
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private static int getListNodeLength(ListNode node){
        int ret = 0;
        while (node != null){
            node = node.next;
            ret++;
        }
        return ret;
    }

    private static void walkDiffStep(ListNode node, int diff){
        while (diff > 0){
            node = node.next;
            diff--;
        }
    }
}
