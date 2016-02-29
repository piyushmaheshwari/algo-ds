package linkedLists;

import util.LLNode;

public class ReverseKSubList {

    public <T> LLNode<T> reverseKSubList(LLNode<T> head, int k) {
        if (head == null)
            return head;

        int length = LLNode.countSize(head);
        if (length <= k)
            return new ReverseLinkedList().reverseIterative(head);

        int remain = length / k;

        LLNode<T> newHead = null;
        LLNode<T> prevTail = null;
        LLNode<T> start = head;

        while (remain > 0) {
            int num = k;
            LLNode<T> end = start;
            while (num > 1){
                end = end.next;
                num -= 1;
            }
            LLNode<T> temp = end.next;

            //detach the sub list and reverse it (start to end)
            end.next = null;
            LLNode<T> reversed = new ReverseLinkedList().reverseIterative(start);

            if (remain == length / k) //this is for the first sub list
                newHead = reversed;

            if (prevTail != null)
                prevTail.next = reversed;

            prevTail = start;
            start = temp;
            remain -= 1;
        }

        prevTail.next = start;

        return newHead;
    }


    public void test (){
        LLNode<Integer> head = LLNode.make(1);
        head.next = LLNode.make(2);
        head.next.next = LLNode.make (3);
        head.next.next.next = LLNode.make (4);
        head.next.next.next.next = LLNode.make(5);
        //head.next.next.next.next.next = LLNode.make(6);
        LLNode<Integer> newHead = reverseKSubList(head, 3);
        System.out.println(LLNode.printList(newHead));
    }
}
