package dp;

import java.util.Arrays;

public class NumStepsBoardGame {
    int solve(int N, int K) {
        int[] dp = new int[N + 1];
        Arrays.fill(dp, 0);
        dp [0] = 1;
        for (int n = 1; n <= N; n++)
            for (int k = 1; k <= K; k++)
                dp [n] += ((n - k >= 0) ? dp [n - k] : 0);

        return dp[N];
    }

    public void test() {
        System.out.println(solve(4,2));
    }
}
