package divide_conqure;

public class LEET_Merge_k_Sorted_Lists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        ListNode[] lists;
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) return null;
            this.lists = lists;
            return divideMerge(0, lists.length - 1);
        }

        public ListNode divideMerge(int l, int r) {
            if (l < r) {
                if (l + 1 == r) {
                    return sort(lists[l], lists[r]);
                }
                return sort (
                        divideMerge(l, (l + r)/2),
                        divideMerge((l+r)/2 + 1, r)
                );
            }
            if (l == r) return lists[l];
            return null;
        }
        public ListNode sort(ListNode l1, ListNode l2) {
            ListNode head = new ListNode();
            ListNode cur = head;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    cur.next = l1;
                    cur = l1;
                    l1 = l1.next;
                } else {
                    cur.next = l2;
                    cur = l2;
                    l2 = l2.next;
                }
            }
            if (l1 != null) {
                while (l1 != null) {
                    cur.next = l1;
                    cur = l1;
                    l1 = l1.next;
                }
            }
            if (l2 != null) {
                while (l2 != null) {
                    cur.next = l2;
                    cur = l2;
                    l2 = l2.next;
                }
            }
            return head.next;
        }
    }
}
