package graphs;

import java.util.Arrays;

public class EnclosedRegions {

    int[][] shift = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean isBoundary(int x, int y, int m, int n) {
        if (x == 0 || x == m - 1 || y == 0 || y == n - 1)
            return true;
        else
            return false;
    }

    private boolean helper(int[][] maze, int x, int y, boolean[][] visited, int m, int n) {

        assert (maze[x][y] == 0);
        visited[x][y] = true;
        if (isBoundary(x, y, m, n))
            return true;
        else {
            for (int i = 0; i < 4; i++) {
                int X = x + shift[i][0];
                int Y = y + shift[i][1];
                if (maze[X][Y] == 0 && visited[X][Y] == false && helper(maze, X, Y, visited, m, n))
                    return true;
            }

            maze[x][y] = 1;
            return false;
        }
    }

    public void replaceEnclosed(int[][] maze) {
        int m = maze.length;
        int n = maze[0].length;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited[i], false);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == false && maze[i][j] == 0) {
                    helper(maze, i, j, visited, m, n);
                }
            }
        }
    }


    public void test() {
        int[][] maze = {{1, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 0, 1}, {1, 1, 0, 1}};
        replaceEnclosed(maze);
        for (int[] row : maze) {
            for (int ele : row) {
                System.out.print(ele + " ");
            }
            System.out.println("");
        }
    }
}
