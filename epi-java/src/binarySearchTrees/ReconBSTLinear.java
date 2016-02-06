package binarySearchTrees;

import util.BTNode;

import java.util.ArrayList;
import java.util.List;

public class ReconBSTLinear {

    private int nextIndex;

    private BTNode<Integer> solveInner(int[] A, int leftRange, int rightRange) {
        if (this.nextIndex == A.length)
            return null;

        int value = A[this.nextIndex];
        if (value >= leftRange && value <= rightRange) {
            BTNode<Integer> root = BTNode.make(value);
            this.nextIndex += 1;
            root.left = solveInner(A, leftRange, value);
            root.right = solveInner(A, value, rightRange);
            return root;
        } else
            return null;
    }

    BTNode<Integer> solve(int[] A) {
        return solveInner(A, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    List<Integer> preOrder(BTNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        result.add(root.val);
        result.addAll(preOrder(root.left));
        result.addAll(preOrder(root.right));
        return result;
    }

    List<Integer> inOrder(BTNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        result.addAll(inOrder(root.left));
        result.add (root.val);
        result.addAll(inOrder(root.right));
        return result;
    }

    public void test() {
        int[] A = {5, 3, 2, 4, 7, 6, 8};
        BTNode<Integer> tree = solve(A);
        System.out.println(preOrder(tree));
        System.out.println(inOrder(tree));
    }
}
