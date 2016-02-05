package util;

public class BTNode<T> {
    public T val;
    public BTNode<T> left;
    public BTNode<T> right;

    public BTNode(T val) {
        this.val = val;
    }
    public static <T> BTNode make (T val) {
        return new BTNode(val);
    }
}
