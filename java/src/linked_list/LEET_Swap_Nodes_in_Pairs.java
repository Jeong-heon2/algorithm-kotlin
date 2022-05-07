package linked_list;

public class LEET_Swap_Nodes_in_Pairs {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
//    class Solution {
//        public ListNode swapPairs(ListNode head) {
//            if (head == null) return head;
//            if (head.next == null) return head;
//            ListNode curNode = head;
//            ListNode preNode = null;
//            head = head.next;
//            while(curNode != null && curNode.next != null) {
//                ListNode nextNode = curNode.next.next;
//                curNode.next.next = curNode;
//                if (preNode != null) preNode.next = curNode.next;
//                preNode = curNode;
//                curNode.next = nextNode;
//                curNode = nextNode;
//            }
//            return head;
//        }
//    }
}
