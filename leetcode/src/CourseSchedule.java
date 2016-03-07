import java.util.*;

public class CourseSchedule {


    public final int WHITE = 0;
    public final int GREY = 1;
    public final int BLACK = 2;

    class Graph {
        public Map<Integer, List<Integer>> vertices;

        public Graph() {
            this.vertices = new HashMap<>();
        }

        public void addEdge(int from, int to) {
            List<Integer> edgeList = this.vertices.getOrDefault(from, null);
            if (edgeList == null)
                edgeList = new ArrayList<>();

            edgeList.add(to);
            this.vertices.put(from, edgeList);
        }

        public void addVertex(int n) {
            this.vertices.put (n, new ArrayList<>());
        }
    }

    boolean dfs(int start, Graph g, Map<Integer, Integer> color) {
        boolean cycle = false;
        color.put(start, GREY);
        for (Integer to : g.vertices.get(start)) {
            if (color.get(to).equals(GREY)) {
                cycle = true;
                break;
            } else if (color.get(to).equals(WHITE)) {
                if (dfs(to, g, color)){
                    cycle = true;
                    break;
                }
            }
        }
        color.put(start, BLACK);
        return cycle;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph g = new Graph();
        Map<Integer, Integer> color = new HashMap<>();
        boolean canFinish = true;

        for (int i = 0; i < numCourses; i++){ //mark all vertices white initially
            color.put(i, WHITE);
            g.addVertex(i);
        }

        for (int i = 0; i < prerequisites.length; i++)
            g.addEdge(prerequisites[i][0], prerequisites[i][1]);

        for (int i = 0; i < numCourses; i++) {
            if (color.get(i).equals(WHITE)) {
                boolean cycle = dfs(i, g, color);
                if (cycle) {
                    canFinish = false;
                    break;
                }
            }
        }
        return canFinish;
    }

    public void test() {
        int[][] preReq = {{1, 0}};
        System.out.println(canFinish(2, preReq));
    }
}
