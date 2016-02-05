package binarySearchTrees;

import util.BTNode;


public class FirstOccurKBST {

    BTNode<Integer> solveRecursive(BTNode<Integer> root, int k) {
        if (root == null)
            return null;

        if (root.val == k) {
            BTNode left = solveRecursive(root.left, k);
            if (left != null)
                return left;
            else
                return root;
        } else if (root.val > k) {
            return solveRecursive(root.left, k);
        } else {
            return solveRecursive(root.right, k);
        }
    }


    BTNode<Integer> solveIterative(BTNode<Integer> root, int k) {
        BTNode<Integer> ans = null;
        BTNode<Integer> current = root;
        while (current != null) {
            if (current.val == k) {
                ans = current;
                current = current.left;
            } else if (current.val > k) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return ans;
    }

    public void test() {
        BTNode<Integer> tree = BTNode.make(108);
        tree.left = BTNode.make(108);
        tree.left.right = BTNode.make(108);
        tree.left.left = BTNode.make(-10);
        tree.left.left.left = BTNode.make(-14);
        tree.left.left.right = BTNode.make(2);
        tree.right = BTNode.make(285);
        tree.right.left = BTNode.make(243);
        tree.right.right = BTNode.make(285);
        tree.right.right.right = BTNode.make(401);
        assert (solveIterative(tree, 108) == tree.left);
        assert (solveRecursive(tree, 108) == tree.left);
        assert (solveRecursive(tree, 285) == tree.right);
        assert (solveIterative(tree, 285) == tree.right);
        assert (solveIterative(tree, 143) == null);
        assert (solveRecursive(tree, 143) == null);

    }
}
