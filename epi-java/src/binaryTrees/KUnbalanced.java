package binaryTrees;

import util.Tuple2;

public class KUnbalanced {
    Tuple2<BinaryTree, Integer> isKUnbalancedInner (BinaryTree root, int k) {
        Tuple2 <BinaryTree, Integer> leftAns = (root.left != null) ? isKUnbalancedInner(root.left,k) : new Tuple2<>(null, 0);
        Tuple2 <BinaryTree, Integer> rightAns = (root.right != null) ? isKUnbalancedInner(root.right, k) : new Tuple2<>(null, 0);

        int totalNodes = leftAns.second + rightAns.second + 1;
        if (leftAns.first != null) {
            return new Tuple2<>(leftAns.first, totalNodes);
        } else if (rightAns.first != null) {
            return new Tuple2<>(rightAns.first, totalNodes);
        } else {
            BinaryTree ans = (Math.abs(leftAns.second - rightAns.second) > k) ? root : null;
            return new Tuple2<> (ans, totalNodes);
        }
    }

    BinaryTree isKUnbalanced (BinaryTree root, int k) {
        return isKUnbalancedInner(root, k).first;
    }
    public void test () {
        int k = 3;
        BinaryTree root = new BinaryTree(314);
        root.left = new BinaryTree(6);
        root.left.left = new BinaryTree(271);
        root.left.left.left  = new BinaryTree(28);
        root.left.left.right = new BinaryTree(17);
        root.right = new BinaryTree(6);
        root.right.right = new BinaryTree(271);
        root.right.right = new BinaryTree(28);
        root.right.left = new BinaryTree(2);
        root.right.left.right = new BinaryTree(1);
        root.right.left.right.left = new BinaryTree(401);
        root.right.left.right.left.right = new BinaryTree(641);
        root.right.left.right.right = new BinaryTree(257);
        System.out.println(isKUnbalanced(root, k).val);
    }
}
