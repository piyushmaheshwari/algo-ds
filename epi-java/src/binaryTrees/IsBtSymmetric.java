package binaryTrees;

public class IsBtSymmetric {
    public boolean isBtSymmetricInner (BinaryTree left, BinaryTree right){
        if (left == null && right == null)
            return true;
        else if (left == null && right != null)
            return false;
        else if (left != null && right == null)
            return false;
        else {
            return (left.val == right.val) &&
                    (isBtSymmetricInner(left.left, right.right) &&
                            isBtSymmetricInner(left.right, right.left));
        }
    }
    public boolean isBtSymmetric (BinaryTree root) {
        if ((root.left != null && root.right == null) &&
                (root.left == null && root.right != null))
            return false;
        else
            return isBtSymmetricInner (root.left, root.right);
    }
    public void test (){
        BinaryTree bt = BinaryTree.makeN(314);
        bt.left = BinaryTree.makeN(6);
        bt.left.right = BinaryTree.makeN(2);
        bt.left.right.right = BinaryTree.makeN(3);
        bt.right = BinaryTree.makeN(6);
        bt.right.left = BinaryTree.makeN(2);
        bt.right.left.left = BinaryTree.makeN(32);
        System.out.println(isBtSymmetric(bt));
    }
}
