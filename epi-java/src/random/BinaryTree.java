package random;

import com.sun.scenario.animation.shared.SingleLoopClipEnvelope;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.LinkedList;
import java.util.Queue;

class MutableInt {
    public int val;
    public MutableInt (int val) {
        this.val = val;
    }

    public void add (int that) {
        this.val += that;
    }
}
class SingleValue {
    public int val;
    public boolean isValid;

    public SingleValue (int val, boolean isValid) {
        this.isValid = isValid;
        this.val = val;
    }
}

public class BinaryTree {
    public BinaryTree left;
    public BinaryTree right;
    public int val;

    public BinaryTree (int val) {
        this.val = val;
        this.left = this.right = null;
    }

    private static SingleValue countSingleTreeInner (BinaryTree root, MutableInt mInt) {
        if (root.left == null && root.right == null) { //base case
            mInt.add(1);
            return new SingleValue(root.val, true);
        }

        SingleValue left = new SingleValue(root.val, true);
        SingleValue right = new SingleValue(root.val, true);
        if (root.left != null) {
            left = countSingleTreeInner(root.left, mInt);
        }
        if (root.right != null) {
            right = countSingleTreeInner(root.right, mInt);
        }
        if (left.isValid && right.isValid && left.val == right.val && right.val == root.val){
            mInt.add(1);
            return new SingleValue(root.val, true);
        } else {
            return new SingleValue(0, false);
        }
    }

    public static int countSingleTree (BinaryTree root) {
        if (root == null)
            return 0;
        MutableInt counter = new MutableInt(0);
        countSingleTreeInner(root, counter);
        return counter.val;
    }


    public static void levelOrder (BinaryTree root){
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add (root);
        while (!queue.isEmpty()){
            BinaryTree ele = queue.poll();
            System.out.println(ele.val + " ");
            if (ele.left != null){
                queue.add(ele.left);
            }
            if (ele.right != null){
                queue.add (ele.right);
            }
        }
    }
}



