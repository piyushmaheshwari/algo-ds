package stackQueue;

import util.BTNode;
import util.Tuple2;
import util.TupleInt;

import java.util.*;

public class PrintBTIncDepth {
    List<List<Integer>> solve (BTNode<Integer> root) {
        List<List<Integer>> ans = new ArrayList<>();
        int prev = 0;
        List<Integer> current = new ArrayList<>();
        Queue <Tuple2<BTNode<Integer>, Integer>> q = new LinkedList<>();
        q.add(Tuple2.make(root, 0));
        while (! q.isEmpty()){
            Tuple2<BTNode<Integer>, Integer> ele = q.poll();
            if (ele.second != prev){
                ans.add(current);
                current = new ArrayList<>();
            }
            prev = ele.second;
            current.add(ele.first.val);
            if (ele.first.left != null)
                q.add(Tuple2.make(ele.first.left, ele.second + 1));
            if (ele.first.right != null)
                q.add(Tuple2.make(ele.first.right, ele.second + 1));
        }

        ans.add(current);
        return ans;
    }

    public void test (){
        BTNode<Integer> root = BTNode.make(43);
        root.left = BTNode.make(23);
        root.left.right = BTNode.make(37);
        root.left.right.left = BTNode.make(29);
        root.left.right.left.right = BTNode.make(31);
        root.left.right.right = BTNode.make(41);
        root.right = BTNode.make(47);
        root.right.right = BTNode.make(53);
        System.out.println(solve(root));

    }
}
