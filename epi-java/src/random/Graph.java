package random;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    Map<String, List<Edge>> g;

    public Graph() {
        g = new HashMap<>();
    }

    private class Edge {
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
                List<String> temp = new ArrayList<String>();
                transposed.dfsInner(node, visited, temp);
                ans.add(new HashSet<>(temp));
            }
        });
        return ans;
    }
}

