package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchMaze {


    private boolean solveInner(int[][] maze, int m, int n, int[][] visited, int x, int y, int endX, int endY, List<Integer> path) {
        int node = m * x + y;
        if (x == endX && y == endY) {
            path.add(node);
            return true;
        }
        visited[x][y] = 1;
        boolean result = false;
        path.add(node);

        if (x + 1 < m && visited[x + 1][y] == 0 && maze[x + 1][y] == 0) {
            result = solveInner(maze, m, n, visited, x + 1, y, endX, endY, path);
        }

        if (!result && x - 1 >= 0 && visited[x - 1][y] == 0 && maze[x - 1][y] == 0) {
            result = solveInner(maze, m, n, visited, x - 1, y, endX, endY, path);
        }

        if (!result && y + 1 < n && visited[x][y + 1] == 0 && maze[x][y + 1] == 0) {
            result = solveInner(maze, m, n, visited, x, y + 1, endX, endY, path);
        }
        if (!result && y - 1 >= 0 && visited[x][y - 1] == 0 && maze[x][y - 1] == 0) {
            result = solveInner(maze, m, n, visited, x, y - 1, endX, endY, path);
        }

        if (result == true)
            return true;
        else {
            path.remove(path.size() - 1);
            return false;
        }
    }

    public boolean solve(int[][] maze, int m, int n, int startX, int startY, int endX, int endY, List<Integer> path) {
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = 0;
            }
        }
        return solveInner(maze, m, n, visited, startX, startY, endX, endY, path);
    }

    public void test() {
        int m = 4;
        int n = 4;
        int[][] maze = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(maze[i], 1);
        }
        maze[0][0] = 0;
        maze[1][0] = 0;
        maze[2][0] = 0;
        maze[2][1] = 0;
        maze[2][2] = 0;
        maze[3][2] = 0;
        maze [3][3] = 0;
        List<Integer> path = new ArrayList<>();
        if (solve(maze, m, n, 0, 0, 3, 3, path)){
            path.stream().forEach(p -> System.out.print(p + " "));
        } else {
            System.out.println(false);
        }

    }
}
