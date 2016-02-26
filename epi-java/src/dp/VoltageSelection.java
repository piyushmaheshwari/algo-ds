package dp;

import util.Tuple2;

import java.util.HashMap;
import java.util.Map;

public class VoltageSelection {

    class Node {
        public Node left;
        public Node right;
        public Node () {
            this.left = null;
            this.right = null;
        }
    }

    int solveInner (Node root, int val, Map<Tuple2<Node, Integer>, Integer> cache){

        Tuple2<Node, Integer> key = new Tuple2<>(root, val);
        if (cache.containsKey(key))
            return cache.get(key);

        int children = 0;
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;

        if (root.left != null){
            children += 1;
            left = solveInner(root.left, 1, cache);
            if (val != 0)
                left = Math.min(left, solveInner(root.left, 0, cache));

        }

        if (root.right != null) {
            children += 1;
            right = solveInner(root.right, 1, cache);
            if (val != 0)
                right = Math.min(right, solveInner(root.right, 0, cache));
        }

        int ans = 0;
        if (val == 0)
            ans += children + 1;
        else
            ans += 2 * (children + 1);

        if (left != Integer.MAX_VALUE)
            ans += left;

        if (right != Integer.MAX_VALUE)
            ans += right;

        cache.put(key, ans);

        return  ans;
    }

    int solve (Node root) {
        HashMap<Tuple2<Node, Integer>, Integer> cache = new HashMap<>();
        return Math.min(solveInner(root, 0, cache), solveInner(root, 1, cache));
    }


    public void test (){
        Node root = new Node();
        root.left = new Node();
        root.left.left = new Node();
        root.left.right = new Node();
        root.right = new Node();

        System.out.println(solve(root));
    }
}
