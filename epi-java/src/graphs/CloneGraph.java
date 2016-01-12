package graphs;

import java.util.*;

public class CloneGraph {
    private class Vertex {
        List<Vertex> neighbours;
        String val;

        public Vertex(String val) {
            this.neighbours = new ArrayList<>();
            this.val = val;
        }

        public void addNeighbour (Vertex n){
            this.neighbours.add (n);
        }
        public void addNeighbourList (Vertex... nList){
            for (Vertex v: nList){
                this.addNeighbour(v);
                v.addNeighbour (this);
            }
        }
    }

    void printGraph (Vertex node, Set<Vertex> visited){
        System.out.println(node.val);
        visited.add(node);
        node.neighbours.stream().forEach(n -> {
            if (! visited.contains(n))
                printGraph(n, visited);
        });

    }

    private Vertex cloneHelper(Vertex start, Map<String, Vertex> visited) {
        if (visited.containsKey(start.val)) {
            return visited.get(start.val);
        } else {
            Vertex startCopy = new Vertex(start.val);
            visited.put(start.val, startCopy);
            start.neighbours.stream().forEach(neighbour -> {
                startCopy.neighbours.add(cloneHelper(neighbour, visited));
            });
            return startCopy;
        }
    }

    public Vertex clone(Vertex start) {
        Map<String, Vertex> visited = new HashMap<>();
        return cloneHelper(start, visited);
    }

    public void test (){
        Vertex a = new Vertex ("a");
        Vertex b = new Vertex ("b");
        Vertex c = new Vertex ("c");
        Vertex d = new Vertex ("d");
        a.addNeighbourList(b, c, d);
        b.addNeighbour(c);
        Vertex clone = clone(a);

        printGraph(clone, new HashSet<Vertex>());
    }
}
