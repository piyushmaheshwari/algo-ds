package greedy;

public class LoadBalancing {

    int solveDP(int[] hashes, int[] bytes, int M) {
        int N = hashes.length;

        int dp[][] = new int[N + 1][M + 1];
        int sum[] = new int[N + 1];

        for (int i = 0; i <= M; i++)
            dp[0][i] = 0;

        sum[0] = 0;
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + bytes[i - 1];
            dp[i][1] = sum[i];
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 2; j <= M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 1; k <= i; k++) {
                    int byteLoad = sum[i] - sum[k - 1];
                    int ans = Math.max(byteLoad, dp[k - 1][j - 1]);
                    dp[i][j] = Math.min(dp[i][j], ans);
                }
            }
        }

        return dp[N][M];
    }

    boolean check(int[] bytes, int M, int load) {
        int index = 0;
        int servers = M;
        while (M > 0 && index < bytes.length) {
            int found = 0;
            boolean kill = false;
            while (!kill && index < bytes.length) {
                found += bytes [index];
                if (found > load)
                    kill = true;
                else
                    index += 1;
            }

            if (kill){
                M -= 1;
            }
        }
        if (index == bytes.length)
            return true;
        else
            return false;
    }

    int solveBS(int[] hashes, int[] bytes, int M) {
        int N = bytes.length;
        int [] sum = new int[N + 1];
        sum [0] = 0;
        for (int i = 1; i <= N; i++)
            sum [i] = sum [i - 1] + bytes [i - 1];

        int minBytes = (int) Math.ceil(sum [N] / M);
        int maxBytes = sum [N];
        int left = minBytes;
        int right = maxBytes;
        int ans = -1;
        while (left <= right){
            int mid  = left + (right - left) / 2;
            if (check(bytes, M, mid)){
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public void test() {
        int[] bytes = {30, 20, 40, 80, 45};
        int[] hashes = {1, 2, 3, 4, 5};
        int ans1 = solveDP(hashes, bytes, 3);
        int ans2 = solveBS(hashes, bytes, 3);
        assert (ans1 == ans2);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
