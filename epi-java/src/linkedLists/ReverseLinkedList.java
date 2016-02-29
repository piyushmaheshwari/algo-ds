package linkedLists;

import util.LLNode;

public class ReverseLinkedList {
    <T> LLNode<T> reverseRecursive(LLNode<T> head) {
        if (head == null || head.next == null)
            return head;

        LLNode<T> newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public <T> LLNode<T> reverseIterative(LLNode<T> head) {
        if (head == null || head.next == null)
            return head;

        LLNode<T> prev = head;
        LLNode<T> curr = head.next;
        while (curr != null) {
            LLNode<T> temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        head.next = null;
        return prev;
    }

    public void test() {
        LLNode<Integer> head = LLNode.make(5);
        head.next = LLNode.make(6);
        head.next.next = LLNode.make(7);
        LLNode<Integer> newHead = reverseRecursive(head);
        System.out.println(LLNode.printList(newHead));
        LLNode<Integer> headAgain = reverseIterative(newHead);
        assert  (head == headAgain);
        System.out.println(LLNode.printList(headAgain));
        System.out.println(LLNode.printList(head));
    }
}
