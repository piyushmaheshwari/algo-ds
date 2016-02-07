package binarySearchTrees;

import util.BTNode;

public class IsBinaryTreeABST {

    private boolean solveInner (BTNode<Integer> root, int leftRange, int rightRange) {
        if (root == null)
            return true;

        if (root.val < leftRange || root.val > rightRange)
            return false;

        return solveInner(root.right, Math.max(root.val, leftRange), rightRange) &&
                solveInner(root.left, leftRange, Math.min(root.val, rightRange));
    }

    public boolean solve (BTNode<Integer> root){
        return solveInner(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public void test (){
        BTNode<Integer> tree = new BTNode<>(3);
        tree.left = new BTNode<>(2);
        tree.left.left = new BTNode<>(1);
        tree.right = new BTNode<>(5);
        tree.right.left = new BTNode<>(-1);
        tree.right.right = new BTNode<>(6);

        System.out.println(solve(tree));
    }
}
