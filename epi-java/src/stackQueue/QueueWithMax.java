package stackQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class QueueWithMax {
    private Queue<Integer> queue;
    private Deque<Integer> maxQueue;

    public QueueWithMax() {
        this.queue = new LinkedList<>();
        this.maxQueue = new LinkedList<>();
    }

    public void enqueue(int val) {
        if (queue.isEmpty()) {
            assert (maxQueue.isEmpty());
            maxQueue.add(val);
        } else {
            while (!maxQueue.isEmpty() && maxQueue.peekLast() < val)
                maxQueue.removeLast();
            maxQueue.add(val);
        }
        this.queue.add(val);
    }

    public int dequeue() {
        if (this.queue.isEmpty()) {
            throw new NoSuchElementException("Removed called on empty queue");
        } else {
            int val = this.queue.poll();
            if (this.maxQueue.peekFirst() == val)
                this.maxQueue.pollFirst();
            return val;
        }
    }

    public int max() {
        if (this.maxQueue.isEmpty())
            throw new NoSuchElementException("Max called on empty queue");
        return this.maxQueue.peekFirst();
    }

    private static void assertDequeue(QueueWithMax q, Integer t) {
        Integer dequeue = q.dequeue();
        assert(t.equals(dequeue));
    }

    public void test() {
        QueueWithMax Q = new QueueWithMax();
        Q.enqueue(11);
        Q.enqueue(2);
        assert(11 == Q.max());
        assertDequeue(Q, 11);
        assert(2 == Q.max());
        assertDequeue(Q, 2);
        Q.enqueue(3);
        assert(3 == Q.max());
        assertDequeue(Q, 3);
        Q.enqueue(Integer.MAX_VALUE - 1);
        Q.enqueue(Integer.MAX_VALUE);
        Q.enqueue(-2);
        Q.enqueue(-1);
        Q.enqueue(-1);
        Q.enqueue(Integer.MIN_VALUE);
        assert(Integer.MAX_VALUE == Q.max());
        assertDequeue(Q, Integer.MAX_VALUE - 1);
        assert(Integer.MAX_VALUE == Q.max());
        assertDequeue(Q, Integer.MAX_VALUE);
        assert(-1 == Q.max());
        assertDequeue(Q, -2);
        assert(-1 == Q.max());
        assertDequeue(Q, -1);
        assertDequeue(Q, -1);
        assert(Integer.MIN_VALUE == Q.max());
        assertDequeue(Q, Integer.MIN_VALUE);
        try {
            System.out.println("Q is empty, max() call should except = " + Q.max());
            assert(false);
        } catch (NoSuchElementException e) {
            System.out.println("Take it easy");
            System.out.println(e.getMessage());
        }
    }
}
