package binarySearchTrees;

import java.util.ArrayList;
import java.util.List;

public class SortedListToBST {
    class ListNode {
        public int val;
        public ListNode next;
        public ListNode prev;

        public ListNode(int val) {
            this.val = val;
            this.next = this.prev = null;
        }
    }

    class DLL {
        public ListNode head;
        public ListNode tail;

        public DLL() {
            this.head = this.tail = null;
        }

        public void addNode(int val) {
            ListNode n = new ListNode(val);
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
            ListNode t = this.head;
            List<Integer> l = new ArrayList<>();
            while (t != null) {
                l.add(t.val);
                t = t.next;
            }
            System.out.println(l);
        }

        public int length() {
            int ans = 0;
            ListNode t = this.head;
            while (t != null) {
                ans += 1;
                t = t.next;
            }
            return ans;
        }
    }

    private ListNode nextPointer;

    private ListNode solveInner(int len) {
        ListNode root = null;
        if (len == 1) {
            root = this.nextPointer;
            this.nextPointer = this.nextPointer.next;
            root.next = root.prev = null;
        } else if (len > 1) {
            ListNode leftTree = solveInner(len / 2);
            root = this.nextPointer;
            this.nextPointer = this.nextPointer.next;
            ListNode rightTree = solveInner(len - 1 - len / 2);
            root.prev = leftTree;
            root.next = rightTree;
        }
        return root;
    }

    private ListNode solve(DLL list) {
        this.nextPointer = list.head;
        return solveInner(list.length());
    }

    private List<Integer> inOrder(ListNode root) {
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
        ListNode tree = solve(list);
        System.out.println(inOrder(tree));
    }
}
