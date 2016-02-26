package random;

import java.util.Arrays;

public class FlowerMaze {

    static final int[][] shift = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    boolean check(int x, int y, int M, int N) {
        if (0 <= x && x < M && 0 <= y && y < N)
            return true;
        else
            return false;
    }

    Boolean solveInner(char[][] maze, Boolean[][][] cache, int x, int y, int direction) {
        if (cache[x][y][direction] != null)
            return cache[x][y][direction];

        Boolean ans = false;
        int M = maze.length;
        int N = maze[0].length;
        int dx = shift[direction][0];
        int dy = shift[direction][1];

        int goX = x + dx;
        int goY = y + dy;

        if (check(goX, goY, M, N)) {
            if (maze[goX][goY] == 'F')
                ans = true;
            else if (maze[goX][goY] == 'W')
                ans = false;
            else {
                ans = solveInner(maze, cache, goX, goY, direction);
            }
        }
        cache[x][y][direction] = ans;
        return ans;
    }

    int solve(char[][] maze) {
        int M = maze.length;
        int N = maze[0].length;
        Boolean[][][] cache = new Boolean[M][N][4];
        for (Boolean[][] row : cache)
            for (Boolean[] rowInner : row)
                Arrays.fill(rowInner, null);

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                for (int dir = 0; dir < 4; dir++) {
                    if (solveInner(maze, cache, i, j, dir))
                        count += 1;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    public void test() {
        char[][] maze = {
                {'F', ' ', ' ', 'F'},
                {' ', 'F', 'F', ' '},
                {' ', ' ', ' ', 'W'},
                {'F', 'W', ' ', 'F'}
        };
        System.out.println(solve(maze));
    }
}
