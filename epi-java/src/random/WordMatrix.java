package random;

import util.Tuple2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordMatrix {

    Set<String> dictionary = new HashSet<>(Arrays.asList("ag", "tad", "ape", "apex"));
    Set<String> found = new HashSet<>();
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    boolean check(int x, int y, int M, int N) {
        return (0 <= x && x < M && 0 <= y && y < N);
    }

    void solve(char[][] matrix, int M, int N, int x, int y, String formed, Set<Tuple2<Integer, Integer>> visited) {
        visited.add(Tuple2.make(x, y));
        for (int i = 0; i < directions.length; i++) {
            String concat = formed + matrix[x][y];
            if (this.dictionary.contains(concat))
                this.found.add(concat);
            int xx = x + directions[i][0];
            int yy = y + directions[i][1];
            if (check(xx, yy, M, N) && !visited.contains(Tuple2.make(xx, yy)))
                solve(matrix, M, N, xx, yy, concat, visited);
        }
        visited.remove(Tuple2.make(x, y));
    }

    void solve(char[][] matrix, int M, int N) {
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++) {
                solve(matrix, M, N, i, j, "", new HashSet<>());
            }
    }

    public void test() {
        char[][] matrix = {{'a', 'p', 'e', 'x'}, {'g', 'b', 'y', 'e'}, {'t', 'a', 'd', 'x'}};
        int M = matrix.length;
        int N = matrix[0].length;
        solve(matrix, M, N);
        System.out.println(found);
    }
}
