package binarySearchTrees;

import util.BinaryTreeNode;

public class IsBinaryTreeABST {

    private boolean solveInner (BinaryTreeNode<Integer> root, int leftRange, int rightRange) {
        if (root == null)
            return true;

        if (root.val < leftRange || root.val > rightRange)
            return false;

        return solveInner(root.right, Math.max(root.val, leftRange), rightRange) &&
                solveInner(root.left, leftRange, Math.min(root.val, rightRange));
    }

    public boolean solve (BinaryTreeNode<Integer> root){
        return solveInner(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public void test (){
        BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
        tree.left = new BinaryTreeNode<>(2);
        tree.left.left = new BinaryTreeNode<>(1);
        tree.right = new BinaryTreeNode<>(5);
        tree.right.left = new BinaryTreeNode<>(-1);
        tree.right.right = new BinaryTreeNode<>(6);

        System.out.println(solve(tree));
    }
}
