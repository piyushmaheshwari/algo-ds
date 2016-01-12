package binaryTrees;

public class IsBtBalanced {
    public static Tuple2<Boolean, Integer> isBalancedInner(BinaryTree root) {
        Tuple2<Boolean, Integer> leftAns = (root.left != null) ? isBalancedInner(root.left) : new Tuple2<>(true, 0);
        Tuple2<Boolean, Integer> rightAns = (root.right != null) ? isBalancedInner(root.right) : new Tuple2<>(true, 0);
        int height = Math.max(leftAns.second, rightAns.second) + 1;
        boolean result = (leftAns.first && rightAns.first
                && (Math.abs(leftAns.second - rightAns.second) <= 1));
        return new Tuple2<>(result, height);
    }

    public static boolean isBalanced(BinaryTree root) {
        return isBalancedInner(root).first;
    }

    public void test() {
        BinaryTree b = new BinaryTree(5);
        b.left = new BinaryTree(10);
        b.left.left = new BinaryTree(15);
        System.out.println(isBalanced(b));
    }
}
