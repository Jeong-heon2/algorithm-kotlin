package linked_list;

public class LEET_ADD_TWO_NUMBERS {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode l1_1 = new ListNode(2);
        ListNode l1_2 = new ListNode(4);
        ListNode l1_3 = new ListNode(3);
        l1_1.next = l1_2;
        l1_2.next = l1_3;

        ListNode l2_1 = new ListNode(5);
        ListNode l2_2 = new ListNode(6);
        ListNode l2_3 = new ListNode(4);
        l2_1.next = l2_2;
        l2_2.next = l2_3;
        s.addTwoNumbers(l1_1, l2_1);
    }

      static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

     // log(N)
    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode cur = new ListNode();
            ListNode root = cur;
            int qot = 0;
            while (l1 != null || l2 != null) {
                if (l1 == null) {
                    cur.val = (l2.val + qot) % 10;
                    qot = (l2.val + qot) / 10;
                    l2 = l2.next;
                } else if (l2 == null) {
                    cur.val = (l1.val + qot) % 10;
                    qot = (l1.val + qot) / 10;
                    l1 = l1.next;
                } else {
                    int remain = (l1.val + l2.val + qot) % 10;
                    qot = (l1.val + l2.val + qot) / 10;
                    cur.val = remain;
                    l2 = l2.next;
                    l1 = l1.next;
                }
                if (l1 == null && l2 == null) break;
                cur.next = new ListNode();
                cur = cur.next;
            }
            if (qot != 0) {
                cur.next = new ListNode(1);
            }
            return root;
        }
    }
}
