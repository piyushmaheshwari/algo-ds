package util;

import java.util.ArrayList;
import java.util.List;

public class BTNode<T> {
    public T val;
    public BTNode<T> left;
    public BTNode<T> right;
    public BTNode<T> parent;

    public BTNode(T val) {
        this.val = val;
        this.left = this.right = this.parent = null;
    }

    public BTNode(T val, BTNode<T> parent) {
        this.val = val;
        this.parent = parent;
    }

    public static <T> BTNode make(T val) {
        return new BTNode(val);
    }

    public static <T> BTNode make(T val, BTNode<T> parent) {
        return new BTNode(val, parent);
    }

    public static <T> List<T> inOrder(BTNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root != null) {
            result.addAll(inOrder(root.left));
            result.add(root.val);
            result.addAll(inOrder(root.right));
        }
        return result;
    }
}
