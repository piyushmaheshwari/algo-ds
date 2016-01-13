package binaryTrees;


import sun.java2d.xr.MutableInteger;
import util.Tuple3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BtLCA {
    private Tuple3<BinaryTree, Boolean, Boolean> lcaInner(BinaryTree root, BinaryTree first, BinaryTree second) {
        if (root == null) {
            return new Tuple3<>(null, false, false);
        } else {
            Tuple3<BinaryTree, Boolean, Boolean> leftAns = lcaInner(root.left, first, second);
            Tuple3<BinaryTree, Boolean, Boolean> rightAns = lcaInner(root.right, first, second);

            if (root == first) {
                if (leftAns.third || rightAns.third)
                    return new Tuple3<>(root, true, true);
                else
                    return new Tuple3<>(null, true, false);
            } else if (root == second) {
                if (leftAns.second || rightAns.second)
                    return new Tuple3<>(root, true, true);
                else
                    return new Tuple3<>(null, false, true);
            } else {
                BinaryTree found = (leftAns.first != null) ? leftAns.first : ((rightAns.first != null) ? rightAns.first : null);
                if (found != null)
                    return new Tuple3<>(found, true, true);
                else {
                    if ((leftAns.second && rightAns.third) || (leftAns.third && rightAns.second))
                        return new Tuple3<>(root, true, true);
                    else {
                        return new Tuple3<>(null, (leftAns.second || rightAns.second), (leftAns.third || rightAns.third));
                    }
                }
            }
        }
    }

    public BinaryTree lca(BinaryTree root, BinaryTree first, BinaryTree second) {
        return lcaInner(root, first, second).first;
    }

    void findPathsInner1(BinaryTree root, int k, List<Integer> path, List<List<Integer>> paths) {
        path.add(root.val);
        findPathsInner1(root.left, k - root.val, path, paths);

    }

    void findPathsInner(BinaryTree root, int k, List<Integer> path, List<List<Integer>> paths) {
        path.add(root.val);
        if (root.left == null && root.right == null && (k == root.val)) {
            List<Integer> t = new ArrayList<>(path);
            paths.add(t);
        } else {
            int temp = k - root.val;
            if (temp >= 0) {
                if (root.left != null) {
                    findPathsInner(root.left, temp, path, paths);
                }

                if (root.right != null) {
                    findPathsInner(root.right, temp, path, paths);
                }
            }
        }
        path.remove(path.size() - 1);
        return;
    }

    public List<List<Integer>> findPaths(BinaryTree root, int k) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        findPathsInner(root, k, path, paths);
        return paths;
    }


    public BinaryTree constructPreOrderMarkerInner(List<String> traversal, MutableInteger index) {

        String v = traversal.get(index.getValue());
        index.setValue(index.getValue() + 1);
        if (v == "null") {
            return null;
        } else {
            BinaryTree n = BinaryTree.makeN(Integer.parseInt(v));
            n.left = constructPreOrderMarkerInner(traversal, index);
            n.right = constructPreOrderMarkerInner(traversal, index);
            return n;
        }
    }

    public BinaryTree constructPreOrderMarker(List<String> traversal) {
        return constructPreOrderMarkerInner(traversal, new MutableInteger(0));
    }

    public void test() {
//        BinaryTree bt = BinaryTree.makeN(314);
//        bt.left = BinaryTree.makeN(6);
//        bt.left.right = BinaryTree.makeN(2);
//        bt.left.right.right = BinaryTree.makeN(3);
//        bt.right = BinaryTree.makeN(6);
//        bt.right.left = BinaryTree.makeN(2);
//        bt.right.left.left = BinaryTree.makeN(32);
//        BinaryTree bt = BinaryTree.makeN(1);
//        bt.left = BinaryTree.makeN(2);
//        bt.left.left = BinaryTree.makeN(5);
//        bt.left.left.left = BinaryTree.makeN(4);
//        bt.left.left.right = BinaryTree.makeN(6);
//        bt.left.right = BinaryTree.makeN(9);
//        bt.right = BinaryTree.makeN(3);
//        bt.right.left = BinaryTree.makeN(7);
//        bt.right.right = BinaryTree.makeN(8);
//        List<List<Integer>> paths = findPaths(bt, 12);
//        for (List<Integer> t : paths) {
//            for (Integer p : t) {
//                System.out.print(p + " ");
//            }
//            System.out.println("");
//        }
        String[] arg = {"1", "2", "3", "null", "null", "4", "5", "null", "null", "null", "6", "null", "7", "null", "8", "9", "null", "null", "null"};
        BinaryTree t = constructPreOrderMarker(Arrays.asList(arg));
        System.out.println(t.getInOrder());
        System.out.println();
    }
}
