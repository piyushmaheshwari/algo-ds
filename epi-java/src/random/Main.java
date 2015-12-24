package random;

import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws Exception {
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
        g.addEdge("a","b");
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
