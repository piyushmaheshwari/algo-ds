package dp;

import java.util.Arrays;

public class MaxSubArrayCircular {
    private int maxCrossSum(int[] input) {
        int n = input.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        Arrays.fill(prefix, 0);
        Arrays.fill(suffix, 0);

        for (int i = 0; i < n; i++) {
            prefix[i] = (i > 0) ? (prefix[i - 1] + input[i]) : input[i];
            int j = n - i - 1;
            suffix[j] = (j < n - 1) ? input[j] + suffix[j + 1] : input[j];
        }

        int m = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            m = (prefix[i] > m) ? prefix[i] : m;
            prefix[i] = m;
        }

        m = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            m = (suffix[i] > m) ? suffix[i] : m;
            suffix[i] = m;
        }

        m = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            if ((prefix[i] + suffix[i + 1]) > m)
                m = prefix[i] + suffix[i + 1];
        }
        return m;
    }

    private int maxSubArray(int[] input) {
        int n = input.length;
        int[] dp = new int[n];
        int m = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int poss = (i - 1 >= 0) ? (input[i] + dp[i - 1]) : Integer.MIN_VALUE;
            dp[i] = Math.max(input[i], poss);
            m = Math.max(m, dp[i]);
        }
        return m;
    }

    public int solve(int[] input) {
        return Math.max(maxSubArray(input), maxCrossSum(input));
    }

    public void test() {
        int[] input = {904, 40, 523, 12, -335, -385, -124, 481, -31};
        System.out.println(solve(input));
    }
}
