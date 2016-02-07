package binarySearchTrees;

import java.util.ArrayList;
import java.util.List;

public class SortedListToBST {
    class Node {
        public int val;
        public Node next;
        public Node prev;

        public Node(int val) {
            this.val = val;
            this.next = this.prev = null;
        }
    }

    class DLL {
        public Node head;
        public Node tail;

        public DLL() {
            this.head = this.tail = null;
        }

        public void addNode(int val) {
            Node n = new Node(val);
            if (head == null) {
                assert (tail == null);
                head = tail = n;
            } else {
                tail.next = n;
                n.prev = tail;
                tail = n;
            }
        }

        public void printList() {
            Node t = this.head;
            List<Integer> l = new ArrayList<>();
            while (t != null) {
                l.add(t.val);
                t = t.next;
            }
            System.out.println(l);
        }

        public int length() {
            int ans = 0;
            Node t = this.head;
            while (t != null) {
                ans += 1;
                t = t.next;
            }
            return ans;
        }
    }

    private Node nextPointer;

    private Node solveInner(int len) {
        Node root = null;
        if (len == 1) {
            root = this.nextPointer;
            this.nextPointer = this.nextPointer.next;
            root.next = root.prev = null;
        } else if (len > 1) {
            Node leftTree = solveInner(len / 2);
            root = this.nextPointer;
            this.nextPointer = this.nextPointer.next;
            Node rightTree = solveInner(len - 1 - len / 2);
            root.prev = leftTree;
            root.next = rightTree;
        }
        return root;
    }

    private Node solve(DLL list) {
        this.nextPointer = list.head;
        return solveInner(list.length());
    }

    private List<Integer> inOrder(Node root) {
        List<Integer> l = new ArrayList<>();
        if (root != null) {
            l.addAll(inOrder(root.prev));
            l.add(root.val);
            l.addAll(inOrder(root.next));
        }
        return l;
    }

    public void test() {
        DLL list = new DLL();
        list.addNode(2);
        list.addNode(3);
        list.addNode(4);
        list.addNode(5);
        list.printList();
        Node tree = solve(list);
        System.out.println(inOrder(tree));
    }
}
