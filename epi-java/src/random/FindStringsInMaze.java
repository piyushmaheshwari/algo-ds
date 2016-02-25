package random;

import java.util.*;

public class FindStringsInMaze {
    boolean check(int x, int y, int M, int N) {
        return (0 <= x && x < M && 0 <= y && y < N);
    }

    final int[][] move = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    List<String> traverse(char[][] maze, boolean[][] visited,
                          Set<String> dict, int x, int y, String formed) {

        List<String> ans = new ArrayList<>();
        visited[x][y] = true;
        int M = maze.length;
        int N = maze[0].length;
        String concat = formed + maze[x][y];
        if (dict.contains(concat))
            ans.add(concat);

        for (int i = 0; i < move.length; i++) {
            int dx = move[i][0];
            int dy = move[i][1];
            if (check(x + dx, y + dy, M, N) && !visited[x + dx][y + dy]) {
                ans.addAll(traverse(maze, visited, dict, x + dx, y + dy, concat));
            }
        }
        visited[x][y] = false;
        return ans;
    }

    List<String> solve(char[][] maze, Set<String> dict) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                boolean[][] visited = new boolean[maze.length][maze[i].length];
                for (boolean[] row : visited)
                    Arrays.fill(row, false);
                ans.addAll(traverse(maze, visited, dict, i, j, ""));
            }
        }
        return ans;
    }

    public void test() {
        char[][] maze = {{'p', 'e', 's', 'h'}, {'a', 'm', 'z', 'i'}, {'i', 'n', 't', 'e'}, {'p', 'a', 'n', 'a'}};
        Set<String> dict = new HashSet<>(Arrays.asList("pin", "mesh", "pant", "ant", "cos", "tea", "dis", "ante", "pint"));
        System.out.println(solve(maze, dict));
    }
}
