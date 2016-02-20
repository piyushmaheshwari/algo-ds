package stackQueue;

import java.util.Stack;

public class StackWithMax {

    class StackMax<T extends Comparable<T>> {
        private Stack<T> store;

        public StackMax() {
            this.store = new Stack<T>();
        }

        public void push(T val) {
            if (this.store.empty()) {
                store.push(val);
                store.push(val);
            } else {
                T m = store.peek();
                store.push(val);
                T x = (val.compareTo(m) > 0) ? val : m;
                store.push(x);
            }
        }

        public T pop() {
            if (this.store.empty())
                return null;
            this.store.pop();
            return this.store.pop();
        }

        public T max() {
            if (this.store.empty())
                throw new UnsupportedOperationException("Stack is empty");
            else
                return this.store.peek();
        }
    }


    public void test() {
        StackMax<Integer> s = new StackMax<>();
        s.push(5);
        System.out.println(s.max());
        s.push(6);
        System.out.println(s.max());
        s.pop();
        System.out.println(s.max());
        s.pop();
        s.push(-1);
        System.out.println(s.max());
        s.push(-2);
        System.out.println(s.max());
    }
}
