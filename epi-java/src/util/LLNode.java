package util;

import java.util.ArrayList;
import java.util.List;

public class LLNode<T> {
    public LLNode<T> next;
    public T val;

    public LLNode(T val) {
        this.next = null;
        this.val = val;
    }

    public static <M> LLNode<M> make(M val) {
        return new LLNode<>(val);
    }

    public static <T> List<T> printList(LLNode<T> head) {
        List<T> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.val);
            head = head.next;
        }
        return ans;
    }

    public static <T> int countSize(LLNode<T> head) {
        int count = 0;
        while (head != null) {
            count += 1;
            head = head.next;
        }
        return count;
    }
}
