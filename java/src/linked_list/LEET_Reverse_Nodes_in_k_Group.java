package linked_list;

public class LEET_Reverse_Nodes_in_k_Group {


    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // O(2N)
    // memory O(1)
    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || head.next == null || k == 1) return head;
            int size = 0;
            ListNode curNode = head;
            while(curNode != null) {
                size++;
                curNode = curNode.next;
            }

            int n = size/k;
            ListNode preNode = new ListNode();
            preNode.next = head;
            ListNode leftNode = preNode;
            ListNode rightNode = leftNode.next;
            for (int i = 0 ; i < n ; i++) {
                preNode = leftNode;
                leftNode = rightNode;
                rightNode = rightNode.next;
                for (int j = 1 ; j < k ; j++) {
                    leftNode.next = rightNode.next;
                    rightNode.next = preNode.next;
                    preNode.next = rightNode;
                    rightNode = leftNode.next;
                }
                if (i == 0) head = preNode.next;
            }
            return head;
        }
    }
}
