package greedy;

import util.Tuple2;

import java.util.*;

public class HuffmanCoding {

    class Node implements Comparable<Node> {
        public Double frequency;
        public Character value;
        public Node zero;
        public Node one;

        public Node(Double freq, Character value, Node one, Node zero) {
            this.frequency = freq;
            this.value = value;
            this.one = one;
            this.zero = zero;
        }

        @Override
        public int compareTo(Node o) {
            return this.frequency.compareTo(o.frequency);
        }
    }

    List<Tuple2<Character, String>> findPaths(Node root, String path) {
        if (root.value != null) {
            return Arrays.asList(Tuple2.make(root.value, path));
        } else {
            List<Tuple2<Character, String>> left = findPaths(root.zero, path + '0');
            List<Tuple2<Character, String>> right = findPaths(root.one, path + '1');
            List<Tuple2<Character, String>> ans = new ArrayList<>();
            ans.addAll(left);
            ans.addAll(right);
            return ans;
        }
    }

    public List<Tuple2<Character, String>> solve(Map<Character, Double> frequencies) {
        final PriorityQueue<Node> queue = new PriorityQueue<>();
        frequencies.entrySet().forEach(s -> queue.add(new Node(s.getValue(), s.getKey(), null, null)));
        while (queue.size() > 1) {
            Node first = queue.poll();
            Node second = queue.poll();
            Node n = new Node(first.frequency + second.frequency, null, first, second);
            queue.add(n);
        }
        Node root = queue.poll();
        return findPaths(root, "");
    }

    public void test() {
        Map<Character, Double> m = new HashMap<>();
        m.put('a', .27);
        m.put('b', .26);
        m.put('c', .24);
        m.put('d', .23);
        System.out.println(solve(m));
    }
}
