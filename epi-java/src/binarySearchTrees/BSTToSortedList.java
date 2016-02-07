package binarySearchTrees;

import util.BTNode;
import util.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class BSTToSortedList {


    Tuple2<BTNode<Integer>, BTNode<Integer>> solveInner(BTNode<Integer> root) {
        if (root == null)
            return null;
        Tuple2<BTNode<Integer>, BTNode<Integer>> leftList = solveInner(root.left);
        Tuple2<BTNode<Integer>, BTNode<Integer>> rightList = solveInner(root.right);
        BTNode<Integer> ansLeft = null;
        BTNode<Integer> ansRight = null;

        if (leftList != null) {
            root.left = leftList.second;
            leftList.second.right = root;
            ansLeft = leftList.first;
        } else {
            root.left = null;
            ansLeft = root;
        }

        if (rightList != null) {
            root.right = rightList.first;
            rightList.first.left = root;
            ansRight = rightList.second;
        } else {
            root.right = null;
            ansRight = root;
        }
        return new Tuple2<>(ansLeft, ansRight);
    }

    private <T> List<T> getList(BTNode<T> root) {
        List<T> ans = new ArrayList<>();
        while (root != null) {
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }

    BTNode<Integer> solve(BTNode<Integer> root) {
        return solveInner(root).first;
    }

    public void test() {
        BTNode<Integer> root = BTNode.make(7);
        root.left = BTNode.make(3);
        root.left.left = BTNode.make(2);
        root.left.right = BTNode.make(5);
        root.right = BTNode.make(11);
        System.out.println(BTNode.inOrder(root));

        BTNode<Integer> list = solve(root);
        System.out.println(getList(list));
    }

}
