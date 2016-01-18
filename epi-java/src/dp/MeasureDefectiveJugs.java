package dp;

import util.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeasureDefectiveJugs {
    boolean solve(List<Tuple2<Integer, Integer>> jugs, int max, int min) {
        int margin = max - min;
        boolean[][] dp = new boolean[max + 1][margin + 1];
        for (boolean[] t : dp) Arrays.fill(t, false);
        dp[0][0] = true;
        for (int i = 1; i <= max; i++) {
            for (int j = 1; j <= margin; j++){
                for (Tuple2<Integer, Integer> jug: jugs) {
                    if (i - jug.first >=0 && ((j - (jug.second - jug.first))>=0))
                        dp [i][j] = dp [i][j] || dp [i - jug.first][j - (jug.second - jug.first)];
                }
            }
        }

        for (int i = 1; i <= margin; i++)
            if (dp [min][i] == true)
                return true;

        return false;
    }

    public void test() {
        List<Tuple2<Integer, Integer>> jugs = new ArrayList<>();
        jugs.add(Tuple2.make(230, 240));
        jugs.add(Tuple2.make(290, 310));
        jugs.add(Tuple2.make(500, 515));
        System.out.println(solve(jugs, 2200, 2100));
    }
}
