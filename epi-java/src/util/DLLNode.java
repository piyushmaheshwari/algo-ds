package util;


import java.util.ArrayList;
import java.util.List;

public class DLLNode<T> {
    public DLLNode<T> next;
    public DLLNode<T> prev;
    public T val;

    public DLLNode(T val) {
        this.val = val;
        next = prev = null;
    }

    public static <T> DLLNode<T> make(T val) {
        return new DLLNode<>(val);
    }

    public static <T> List<T> printList(DLLNode<T> head) {
        List<T> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.val);
            head = head.next;
        }
        return ans;
    }
}
