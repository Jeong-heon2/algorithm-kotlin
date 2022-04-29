package linked_list;

import java.util.Stack;

public class LEET_Remove_Nth_Node_From_End_of_List {


     public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
     // stack 을 굳이 쓰지 않고도 해결 가능할듯. 전체 사이즈를 구하고  head 부터 size - n 번째 제거하면 되니까.
     class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            Stack<ListNode> stack = new Stack<>();
            ListNode curNode = head;
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.next;
            }
            while(n-- > 0) {
                curNode = stack.pop();
            }
            if (stack.size() == 0) {
                return curNode.next;
            } else {
                ListNode pre = stack.pop();
                pre.next = curNode.next;
                while(stack.size() > 0) {
                    pre = stack.pop();
                }
                return pre;
            }
        }
    }
}
