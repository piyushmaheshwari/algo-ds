package linkedLists;

import util.LLNode;

public class RemoveDuplicateSortedList {
    LLNode<Integer> solve (LLNode<Integer> head) {
        if (head == null || head.next == null)
            return head;

        LLNode<Integer> begin = head;
        while (begin != null){
            LLNode<Integer> end = begin;
            while (end.next != null && end.next.val == end.val)
                end = end.next;

            begin.next = end.next;
            begin = end.next;
        }
        return head;
    }


    public void test () {
        LLNode<Integer> head  = LLNode.make(2);
        head.next = LLNode.make (2);
        head.next.next = LLNode.make(3);
        head.next.next.next = LLNode.make(5);
        head.next.next.next.next = LLNode.make(7);
        head.next.next.next.next.next = LLNode.make(11);
        head.next.next.next.next.next.next = LLNode.make(11);
        System.out.println(LLNode.printList(solve(head)));
    }
}
