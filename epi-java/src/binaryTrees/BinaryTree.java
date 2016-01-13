package binaryTrees;


import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    public int val;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public static BinaryTree makeN(int val) {
        return new BinaryTree(val);
    }

    private void inOrderInner(List<Integer> arg, BinaryTree root) {
        if (root == null)
            return;
        else {
            inOrderInner(arg, root.left);
            arg.add(root.val);
            inOrderInner(arg, root.right);
        }
    }

    public List<Integer> getInOrder() {
        List<Integer> arg = new ArrayList<>();
        inOrderInner(arg, this);
        return arg;
    }
}


