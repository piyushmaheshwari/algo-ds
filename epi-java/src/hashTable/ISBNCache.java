package hashTable;

import java.util.HashMap;

class Node {
    Node next;
    Node prev;
    Object val;

    public Node(Object val) {
        super();
        this.val = val;
    }

    public Node() {
        this.next = this.prev = null;
    }
}

class DLL {

    private Node head;
    private Node tail;

    public DLL() {
        this.head = null;
        this.tail = null;
    }

    public void insert(Node n) {
        if (head == null) {
            head = tail = n;
        } else {
            n.next = head;
            head.prev = n;
            this.head = n;
        }
    }

    public Node pop() {
        Node ret = tail;
        if (tail != null) {
            if (tail.prev == null) { //only one element in DLL
                head = tail = null;
            } else {
                tail.prev.next = null;
                this.tail = tail.prev;
            }
        }

        return ret;
    }

    public void deleteNode(final Node n) {
        if (n.prev == null && n.next == null) {
            this.head = this.tail = null;
        } else if (n.next == null) {
            this.tail = n.prev;
            this.tail.next = null;
        } else if (n.prev == null) {
            this.head = n.next;
            this.head.prev = null;
        } else {
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }
    }

    public void printList() {
        Node t = this.head;
        if (t == null) {
            assert (this.tail == null);
            System.out.println("Empty list");
            return;
        }
        while (t != null) {
            System.out.print(t.val + " -> ");
            t = t.next;
        }
        System.out.println("");
    }
}


public class ISBNCache {
    class Payload {
        public Node node;
        public Object val;

        public Payload(Node n, Object v) {
            this.node = n;
            this.val = v;
        }
    }

    private HashMap<Object, Payload> cache;
    int size;
    int capacity;
    DLL chain;

    public ISBNCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
        this.chain = new DLL();
    }

    //public interfaces
    public Integer lookup(Object key) {
        Payload p = this.cache.getOrDefault(key, null);
        if (p != null) {
            //put the node at the beginning of DLL
            refresh(p.node);
            return (Integer) p.val;
        } else {
            return null;
        }
    }

    //for maintaining LRU property
    private void refresh(Node n) {
        this.chain.deleteNode(n);
        n.prev = n.next = null;
        this.chain.insert(n);
    }

    public Object insert(Object key, Object value) {
        Payload p = this.cache.getOrDefault(key, null);
        if (p != null) {
            refresh(p.node);
            Payload pp = new Payload(p.node, value);
            this.cache.put(key, pp);
            //return old value
            return p.val;
        } else {
            if (size == capacity)
                this.chain.pop();
            else
                size++;

            Node n = new Node(value);
            Payload pp = new Payload(n, value);
            this.cache.put(key, pp);
            this.chain.insert(n);
            return null;
        }
    }

    public void erase(Object key) {
        Payload p = this.cache.getOrDefault(key, null);
        if (p != null) {
            this.chain.deleteNode(p.node);
            this.cache.remove(key);
        }
        return;
    }

    public void test() {
        final int CAPACITY = 2;
        ISBNCache c = new ISBNCache(CAPACITY);
        System.out.println("c.insert(1, 1)");
        c.insert(1, 1);
        System.out.println("c.insert(1, 10)");
        c.insert(1, 10);
        System.out.println("c.lookup(2, val)");
        assert (null == c.lookup(2));
        System.out.println("c.lookup(1, val)");
        assert (c.lookup(1) == 1);
        c.erase(1);
        assert (null == c.lookup(1));

        // test capacity constraints honored, also FIFO ordering
        c = new ISBNCache(CAPACITY);
        c.insert(1, 1);
        c.insert(2, 1);
        c.insert(3, 1);
        c.insert(4, 1);
        assert (null == c.lookup(1));
        assert (null == c.lookup(2));
        assert (1 == c.lookup(3));
        assert (1 == c.lookup(4));

        // test retrieval moves to front
        c = new ISBNCache(CAPACITY);
        c.insert(1, 1);
        c.insert(2, 1);
        c.insert(3, 1);
        c.lookup(2);
        c.insert(4, 1);
        assert (null == c.lookup(1));
        assert (1 == c.lookup(2));
        assert (null == c.lookup(3));
        assert (1 == c.lookup(4));

        // test update moves to front
        c = new ISBNCache(CAPACITY);
        c.insert(1, 1);
        c.insert(2, 1);
        c.insert(3, 1);
        c.insert(2, 2);
        c.insert(4, 1);
        assert (null == c.lookup(1));
        assert (1 == c.lookup(2));
        assert (null == c.lookup(3));
        assert (1 == c.lookup(4));

        // test erase
        c = new ISBNCache(CAPACITY);
        c.insert(1, 1);
        c.insert(2, 1);
        c.erase(2);
        c.insert(3, 3);
        assert (1 == c.lookup(1));
        assert (null == c.lookup(2));
        assert (3 == c.lookup(3));
    }
}
