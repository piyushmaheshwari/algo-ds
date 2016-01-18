package dp;

public class CoinsMaxGain {
    int getCoin (int [] coins, int index){
        return coins [index - 1];
    }
    int solve (int [] coins) {
        int n = coins.length;
        int [] sum = new int[n + 1];
        int [][] dp = new int[n + 1][n + 1];
        sum [0] = 0;
        for (int i = 1; i <= n; i++){
            sum [i] = sum [i - 1] + getCoin(coins, i);
        }

        for (int i = 1; i <= n; i++){
            dp [i] [i] = coins [i - 1];
        }

        for (int len = 2; len <= n; len ++){
            for (int i = 1; i <= (n - len + 1); i++){
                int j = i + len - 1;
                int runningSumLeft = sum [j] - sum [i];
                int runningSumRight = sum [j-1] - sum [i -1];
                int left = getCoin(coins, i) + runningSumLeft - dp[i + 1][j];
                int right = getCoin(coins, j) + runningSumRight - dp[i][j - 1];
                dp [i] [j] = Math.max(left, right);
            }
        }
        return dp [1][n];
    }

    public void test (){
        int [] coins = {25,5, 10, 5, 10, 5, 10, 25, 1, 25, 1, 25, 1, 25, 5, 10};
        System.out.println(solve(coins));
    }
}
