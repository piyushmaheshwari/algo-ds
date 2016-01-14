package heaps;


import random.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FairBonuses {
    class Node {
        public String name;
        public int val;
        public Node next;

        public Node(String name, int val) {
            this.name = name;
            this.val = val;
            this.next = null;
        }
    }

    Node addNode(Node head, Node toAdd) {
        if (head == null)
            return toAdd;
        else {
            toAdd.next = head;
            return toAdd;
        }
    }

    Node topologicalSort(Graph g, String node, Set<String> visited, Node chain) {
        visited.add(node);

        List<Graph.Edge> edgeList = g.getEdgeList(node);
        for (Graph.Edge e : edgeList) {
            if (!visited.contains(e.to)) {
                chain = topologicalSort(g, e.to, visited, chain);
            }
        }
        return addNode(chain, new Node(node, 0));
    }


    void printChain(Node chain) {
        while (chain != null) {
            System.out.print(chain.name + " ");
            chain = chain.next;
        }
        System.out.println("");
    }

    Map<String, Integer> setBonus (Node chain, Graph g){
        Map<String, Integer> bonuses = new HashMap<>();
        while (chain != null){
            if (!bonuses.containsKey(chain.name))
                bonuses.put(chain.name, 1);

            for (Graph.Edge e : g.getEdgeList(chain.name)){
                int bonus = Math.max( bonuses.get(chain.name) + 1, bonuses.getOrDefault(e.to, 0));
                bonuses.put (e.to, bonus);
            }
            chain = chain.next;
        }
        return bonuses;
    }

    //algorithm - Find do a topological sort on the graph and then assign weights in the same order
    public void FindFairBonuses(int[] code, String[] name) {
        int size = code.length;
        Graph g = new Graph();
        for (int i = 0; i < size - 1; i++) {
            if (code[i] < code[i + 1])
                g.addEdge(name[i], name[i + 1]);
            else
                g.addEdge(name[i + 1], name[i]);
        }
        Set<String> visited = new HashSet<>();
        Node chain = null;
        for (String s : g.getNodeSet()) {
            if (!visited.contains(s))
                chain = topologicalSort(g, s, visited, chain);
        }
        Map<String, Integer> bonuses = setBonus(chain, g);
        System.out.println(bonuses);
    }

    public void test() {
        int[] code = {300, 400, 500, 200};
        String[] name = {"Andy", "Bob", "Charlie", "David"};
        FindFairBonuses(code, name);
    }
}
