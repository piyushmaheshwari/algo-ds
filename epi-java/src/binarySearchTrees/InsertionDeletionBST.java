package binarySearchTrees;

import util.BTNode;
import util.Tuple2;

public class InsertionDeletionBST {

    public boolean insertBST(BTNode<Integer> root, BTNode<Integer> key) {
        if (root == null || root.val == key.val) {
            return false;
        } else if (root.val > key.val) {
            if (root.left == null) {
                root.left = key;
                return true;
            } else
                return insertBST(root.left, key);
        } else {
            if (root.right == null) {
                root.right = key;
                return false;
            } else {
                return insertBST(root.right, key);
            }
        }
    }

    //deletes the root of the BST passed, and returns the root of the updated tree
    private BTNode<Integer> deleteBSTRoot(BTNode<Integer> root) {
        if (root == null)
            return null;

        if (root.left == null)
            return root.right;
        else if (root.right == null)
            return root.left;
        else {
            Tuple2<BTNode<Integer>, BTNode<Integer>> minTuple = extractMin(root.right);
            minTuple.second.left = root.left;
            minTuple.second.right = minTuple.first;
            return minTuple.second;
        }
    }

    //returns a tuple of the new root and the extracted node
    private Tuple2<BTNode<Integer>, BTNode<Integer>> extractMin(BTNode<Integer> root) {
        BTNode<Integer> tree = root;
        BTNode<Integer> minNode;
        if (root.left == null) {
            tree = root.right;
            minNode = root;
        } else {
            BTNode<Integer> t = root.left;
            BTNode<Integer> parent = root;
            while (t.left != null) {
                parent = t;
                t = t.left;
            }
            minNode = t;
            parent.left = t.right;
        }

        minNode.left = minNode.right = null;
        return Tuple2.make(tree, minNode);
    }

    //returns the root of updated tree after removing the node whose
    //value is the key passed
    public BTNode<Integer> deleteBST(BTNode<Integer> root, int key) {
        if (root == null)
            return null;

        if (key == root.val)
            return deleteBSTRoot(root);
        else if (key < root.val)
            root.left = deleteBST (root.left, key);
        else
            root.right =  deleteBST(root.right, key);

        return root;
    }

    public void test() {
        //        11
        //     5        17
        //   3  7   13     23
        // 2             19
        BTNode<Integer> root = BTNode.make(11);
        insertBST(root, BTNode.make(5));//root.left = BTNode.make(5);
        insertBST(root, BTNode.make(3));//root.left.left = BTNode.make(3);
        insertBST(root, BTNode.make(2));//root.left.left.left = BTNode.make(2);
        insertBST(root, BTNode.make(7)); //root.left.right = BTNode.make(7);
        insertBST(root, BTNode.make(17));//root.right = BTNode.make(17);
        insertBST(root, BTNode.make(13));//root.right.left = BTNode.make(13);
        insertBST(root, BTNode.make(23));//root.right.right = BTNode.make(23);
        insertBST(root, BTNode.make(19));//root.right.right.left = BTNode.make(19);
        BTNode<Integer> t = root;
        while (t != null){
            System.out.println(BTNode.inOrder(t));
            t = deleteBST(t, t.val);
        }
    }
}
