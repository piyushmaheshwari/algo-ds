package random;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    Map<String, List<Edge>> g;

    public Graph() {
        g = new HashMap<>();
    }

    public class Edge {
        public String from;
        public String to;
        public int weight;

        public Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public void addEdge(String from, String to) {
        addNode(from);
        g.get(from).add(new Edge(from, to, -1));
    }

    public Graph getTranspose() {
        Set<String> nodes = this.getNodeSet();
        Graph g = new Graph();
        nodes.stream().forEach(node -> g.addNode(node));
        nodes.stream().forEach(node -> {
            this.getEdgeList(node).stream().forEach(edge -> {
                g.addEdge(edge.to, edge.from);
            });
        });
        return g;
    }

    public void addNode(String node) {
        g.putIfAbsent(node, new ArrayList<>());
    }

    public int getSize() {
        return g.size();
    }

    public boolean isNodePresent(String node) {
        return g.containsKey(node);
    }

    public List<Edge> getEdgeList(String from) {
        return g.getOrDefault(from, new ArrayList<>());
    }

    public Set<String> getNodeSet() {
        return g.keySet();
    }

    void dfsInner(String start, Set<String> visited, List<String> sortedList) {
        visited.add(start);
        List<String> neighbours = this.getEdgeList(start).stream().map(e -> (e.to)).collect(Collectors.toList());
        neighbours.stream().forEach(neighbour -> {
            if (!visited.contains(neighbour)) {
                dfsInner(neighbour, visited, sortedList);
            }
        });
        sortedList.add(start);
    }

    void dfs(List<String> sortedList) {
        Set<String> visited = new HashSet<>();
        this.getNodeSet().stream().forEach(node -> {
            if (!visited.contains(node)) {
                dfsInner(node, visited, sortedList);
            }
        });
    }

    List<String> topologicalSort() {
        List<String> ans = new ArrayList<>();
        dfs(ans);
        Collections.reverse(ans);
        return ans;
    }

    List<Set<String>> getStronglyConnectedComponents() {
        List<String> topologicallySorted = this.topologicalSort();
        Graph transposed = this.getTranspose();
        Set<String> visited = new HashSet<>();
        List<Set<String>> ans = new ArrayList<>();
        topologicallySorted.stream().forEach(node -> {
            if (!visited.contains(node)) {
                List<String> temp = new ArrayList<>();
                transposed.dfsInner(node, visited, temp);
                ans.add(new HashSet<>(temp));
            }
        });
        return ans;
    }

    void test() {
        Graph g = new Graph();
//        g.addEdge("undershorts", "pants");
//        g.addEdge("pants", "belt");
//        g.addEdge("belt", "jacket");
//        g.addEdge("pants", "shoes");
//        g.addEdge("socks", "shoes");
//        g.addEdge("shirt", "belt");
//        g.addEdge("shirt", "tie");
//        g.addEdge("tie", "jacket");
//        g.addEdge("undershorts", "shoes");
//        g.addNode("watch");
        //List<String> ordering = g.topologicalSort();
        //ordering.stream().forEach(s -> System.out.print(s + " "));
        g.addEdge("a", "b");
        g.addEdge("b", "e");
        g.addEdge("e", "a");
        g.addEdge("e", "f");
        g.addEdge("f", "g");
        g.addEdge("g", "f");
        g.addEdge("g", "h");
        g.addEdge("b", "f");
        g.addEdge("c", "g");
        g.addEdge("h", "h");
        g.addEdge("c", "d");
        g.addEdge("d", "c");
        g.addEdge("d", "h");
        g.addEdge("h", "h");
        g.getStronglyConnectedComponents().stream().forEach(scc -> {
            scc.stream().forEach(n -> System.out.print(n + " "));
            System.out.println("");
        });
    }
}

