package random;

import java.util.HashMap;
import java.util.Map;

class Node {
    public Object val;
    Node left;
    Node right;

    public Node(Object val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class DLL {

    public Node head;
    public Node tail;

    public DLL() {
        this.head = null;
        this.tail = null;
    }

    public void insertElementAtEnd(Node e) {
        e.left = null;
        e.right = null;
        if (head == null) {
            head = tail = e;
        } else {
            tail.right = e;
            e.left = tail;
            tail = e;
        }
    }

    Node removeHead() {
        if (head == null)
            return null;
        else {
            Node t = head;
            head = head.right;
            return t;
        }
    }

    void removeElementInPlace(Node ele) {
        if (ele.left == null) {
            head = head.right;
        } else if (ele.right == null) {
            tail = tail.left;
        } else {
            Node tl = ele.left;
            Node tr = ele.right;
            tl.right = tr;
            tr.left = tl;
        }
    }
}


public class LRUCache {
    private class Payload {
        public Object val;
        public Node ptr;

        public Payload(Object val, Node ptr) {
            this.val = val;
            this.ptr = ptr;
        }
    }

    public int capacity;
    public int size;
    private Map<Object, Payload> cache;
    public DLL dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();
        this.dll = new DLL();
    }

    public void put(Object key, Object value) {

        Object cached = get(key);
        if (cached != null) {
            Payload p = this.cache.get(key);
            p.val = value;
            this.cache.put(key, p);
        } else {
            if (this.size == this.capacity) {
                Node remove = dll.removeHead();
                this.cache.remove(remove.val);
                this.size = this.size - 1;
            }
            Node n = new Node(key);
            this.dll.insertElementAtEnd(n);
            cache.put(key, new Payload(value, n));
            this.size += 1;
        }
    }

    public Object get(Object key) {
        if (this.cache.containsKey(key)) {
            Payload p = this.cache.get(key);
            this.dll.removeElementInPlace(p.ptr);
            this.dll.insertElementAtEnd(p.ptr);
            return p.val;
        } else {
            return null;
        }
    }

    //for debugging
    void printDLL (){
        DLL l = this.dll;
        Node t = l.head;
        while (t != l.tail.right){
            System.out.print(t.val + " ");
            t = t.right;
        }
        System.out.println();
    }
}
