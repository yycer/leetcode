package com.frankie.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Yao Frankie
 * @date: 2020/3/24 22:49
 */
@SpringBootTest
public class LinkedListTest {

    /**
     * 21. Merge Two Sorted Lists
     * Input:  1->2->4, 1->3->4
     * Output: 1->1->2->3->4->4
     */
    @Test
    void p21(){
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l14 = new ListNode(4);
        l12.next = l14;
        l11.next = l12;

        ListNode l21 = new ListNode(1);
        ListNode l23 = new ListNode(3);
        ListNode l24 = new ListNode(4);
        l23.next = l24;
        l21.next = l23;
        ListNode ret = LinkedListUtils.mergeTwoSortedLists(l11, l21);
    }

    /**
     * 83. Remove Duplicates from Sorted List
     */
    @Test
    void p83(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(3);

        l4.next = l5;
        l3.next = l4;
        l2.next = l3;
        l1.next = l2;
        ListNode ret = LinkedListUtils.deleteDuplicates(l1);
        System.out.println(ret);
    }

    /**
     * 160. Intersection of Two Linked Lists
     */
    @Test
    void p160(){

        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(8);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l4.next = l5;
        l3.next = l4;
        l2.next = l3;
        l1.next = l2;

        ListNode l8 = new ListNode(8);

        ListNode ret = LinkedListUtils.getIntersectionNode(l8, l1);
        System.out.println(ret);
    }

    @Test
    void addNodeTest(){
        LinkedListUtils.addNode(1);
        LinkedListUtils.addNode(2);
        LinkedListUtils.addNode(3);
        System.out.println(LinkedListUtils.head);
    }


    @Test
    void p141(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l6.next = l2;
        l5.next = l6;
        l4.next = l5;
        l3.next = l4;
        l2.next = l3;
        l1.next = l2;

        hasCycle(l1);
    }

    public boolean hasCycle(ListNode head) {
        ListNode fastNode = head;
        ListNode slowNode = head;

        while(fastNode != null && fastNode.next != null){
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;

            if (fastNode == slowNode){
                return true;
            }
        }
        return false;
    }

    @Test
    public void dummyNodeChange(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode dummyNode = new ListNode(0);
        ListNode curNode   = dummyNode;

        dummyNode.next = l1;

        System.out.println(dummyNode);
        System.out.println(curNode);
    }

    @Test
    public void curNodeChange(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode dummyNode = new ListNode(0);
        ListNode curNode   = dummyNode;

        curNode.next = l1;

        System.out.println(dummyNode);
        System.out.println(curNode);
    }
}
