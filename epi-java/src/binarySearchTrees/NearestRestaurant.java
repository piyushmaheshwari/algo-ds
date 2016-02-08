package binarySearchTrees;

import util.BTNode;

import java.util.ArrayList;
import java.util.List;

public class NearestRestaurant {

    void insertBST(BTNode<Integer> root, BTNode<Integer> key) {
        if (root.val < key.val) {
            if (root.right == null) {
                key.parent = root;
                root.right = key;
            } else {
                insertBST(root.right, key);
            }
        } else {
            if (root.left == null) {
                key.parent = root;
                root.left = key;
            } else {
                insertBST(root.left, key);
            }
        }
    }

    BTNode<Integer> findSuccessor(BTNode<Integer> root) {
        if (root == null)
            return null;

        if (root.right != null) {
            BTNode<Integer> ans = root.right;
            while (ans.left != null)
                ans = ans.left;
            return ans;
        } else {
            BTNode<Integer> current = root;
            BTNode<Integer> parent = root.parent;
            while (parent != null) {
                if (parent.left == current)
                    break;
                else {
                    current = parent;
                    parent = parent.parent;
                }
            }
            return parent;
        }
    }


    public List<Integer> solve(BTNode<Integer> tree, int leftRange, int rightRange) {

        BTNode<Integer> iterator = tree;
        BTNode<Integer> first = null;
        List<Integer> result = new ArrayList<>();
        while (iterator != null) {
            if (iterator.val >= leftRange) {
                first = iterator;
                iterator = iterator.left;
            } else
                iterator = iterator.right;
        }
        if (first != null) {
            result.add(first.val);
            BTNode<Integer> successor = findSuccessor(first);
            while (successor != null) {
                if (successor.val > rightRange)
                    break;
                else {
                    result.add(successor.val);
                    successor = findSuccessor(successor);
                }
            }
        }
        return result;
    }

    public void test() {
        BTNode<Integer> root = BTNode.make(11);
        insertBST(root, BTNode.make(5));
        insertBST(root, BTNode.make(3));
        insertBST(root, BTNode.make(2));
        insertBST(root, BTNode.make(7));
        insertBST(root, BTNode.make(17));
        insertBST(root, BTNode.make(23));
        insertBST(root, BTNode.make(19));
        insertBST(root, BTNode.make(13));
        insertBST(root, BTNode.make(5));
        System.out.println(BTNode.inOrder(root));
        System.out.println(solve(root, 4, 24));
    }
}
