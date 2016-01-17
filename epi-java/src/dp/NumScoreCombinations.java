package dp;

import java.util.Arrays;

public class NumScoreCombinations {
    int solve (int finalScore, int [] plays) {
        int n = plays.length;
        int [][] dp = new int[finalScore + 1][n];
        for (int [] t: dp) Arrays.fill(t, 0);

        for (int i = 1; i < n; i++){
            dp [0][i] = 1;
        }

        for (int play = 1; play < n ; play++){
            for (int score = 1; score < finalScore + 1; score++) {
                int diff = score - plays[play];
                dp [score][play] = dp [score][play-1] + ((diff >=0) ? (dp[diff][play]) : 0);
            }
        }
        return dp [finalScore][n - 1];
    }
    public void test () {
        int s = 12;
        int [] W = {0,2,3,7};
        System.out.println(solve (s,W));
    }
}
