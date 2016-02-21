package stackQueue;

import util.BTNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintKeysBST {
    List<Integer> solve (BTNode<Integer> root, BTNode<Integer> node) {
        BTNode<Integer> temp = root;
        while (temp != null && temp != node){
            if (node.val >= temp.val) {
                temp = temp.right;
            } else
                temp = temp.left;
        }

        List<Integer> ans = new ArrayList<>();
        if (temp == null)
            return ans;
        else {
            Stack<BTNode<Integer>> s = new Stack<>();
            while (temp != null || !s.empty()){
                if (temp != null){
                    s.push(temp);
                    temp = temp.left;
                } else {
                    temp = s.pop();
                    ans.add(temp.val);
                    temp = temp.right;
                }
            }
            return ans;
        }
    }

    public void test () {
        BTNode<Integer> root = BTNode.make(43);
        root.left = BTNode.make(23);
        root.left.right = BTNode.make(37);
        root.left.right.left = BTNode.make(29);
        root.left.right.left.right = BTNode.make(31);
        root.left.right.right = BTNode.make(41);
        root.right = BTNode.make(47);
        root.right.right = BTNode.make(53);
        System.out.println(solve(root, root.left.right));
    }
}
