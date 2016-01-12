package binaryTrees;


public class BinaryTree {
    public int val;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class Tuple2<L, R> {
    public L first;
    public R second;

    public Tuple2(L first, R second) {
        this.first = first;
        this.second = second;
    }
}

