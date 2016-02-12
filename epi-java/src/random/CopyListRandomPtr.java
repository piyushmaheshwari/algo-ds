package random;

import util.DLLNode;
import util.TupleInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyListRandomPtr {

    //uses O(n) space
    DLLNode<Integer> solve(DLLNode<Integer> list) {
        DLLNode<Integer> copy = null;
        Map<DLLNode<Integer>, DLLNode<Integer>> mapping = new HashMap<>();
        DLLNode<Integer> copyNext = null;

        while (list != null) {
            DLLNode<Integer> nextNode = mapping.getOrDefault(list, null);
            DLLNode<Integer> randomNode = null;
            if (list.prev != null)
                mapping.getOrDefault(list.prev, null);
            if (nextNode == null) {
                nextNode = DLLNode.make(list.val);
                mapping.put(list, nextNode);
            }

            if (list.prev != null && randomNode == null) {
                randomNode = DLLNode.make(list.prev.val);
                mapping.put(list.prev, randomNode);
            }

            nextNode.prev = randomNode;

            if (copy == null) {
                copy = nextNode;
            } else {
                copyNext.next = nextNode;
            }
            copyNext = nextNode;
            list = list.next;
        }
        return copy;
    }

    //uses O(1) space
    DLLNode<Integer> solveBetter(DLLNode<Integer> list) {
        DLLNode<Integer> temp = list;
        while (temp != null) {
            addNode(temp, DLLNode.make(temp.val));
            temp = temp.next.next;
        }

        temp = list;
        while (temp != null) {
            if (temp.prev != null)
                temp.next.prev = temp.prev.next;
            temp = temp.next.next;
        }

        DLLNode<Integer> copy = null;
        DLLNode<Integer> copyTemp = null;
        temp = list;

        while (temp != null) {
            DLLNode<Integer> nextTemp = temp.next.next;
            DLLNode<Integer> copyNext = temp.next;
            temp.next = nextTemp;
            if (copy == null){
                copy = copyNext;
            } else {
                copyTemp.next = copyNext;
            }
            copyTemp = copyNext;
            temp = nextTemp;
        }

        return copy;
    }

    private void addNode(DLLNode<Integer> head, DLLNode<Integer> add) {
        DLLNode<Integer> nextHead = head.next;
        head.next = add;
        add.next = nextHead;
    }

    void setNext(DLLNode<Integer> first, DLLNode<Integer> second) {
        first.next = second;
    }

    void setRandom(DLLNode<Integer> first, DLLNode<Integer> second) {
        first.prev = second;
    }

    List<TupleInt> print(DLLNode<Integer> head) {
        List<TupleInt> ans = new ArrayList<>();
        while (head != null) {
            ans.add(TupleInt.make(head.val, (head.prev != null) ? head.prev.val : -1));
            head = head.next;
        }
        return ans;
    }

    public void test() {
        DLLNode<Integer> one = DLLNode.make(1);
        DLLNode<Integer> two = DLLNode.make(2);
        DLLNode<Integer> three = DLLNode.make(3);
        DLLNode<Integer> four = DLLNode.make(4);
        setNext(one, two);
        setNext(two, three);
        setNext(three, four);
        setRandom(one, four);
        setRandom(two, one);
        setRandom(three, one);
        setRandom(four, null);
        System.out.println(print(solveBetter(one)));
        System.out.println(print(one));
    }
}
