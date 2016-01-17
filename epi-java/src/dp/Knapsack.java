package dp;

import java.util.Arrays;

public class Knapsack {
    int indexArray (int [] array, int index){
        if (index == 0)
            return 0;
        else
            return array [index - 1];
    }

    int getValue (int [] arr, int index) {
        return arr [index - 1];
    }
    int solve (int [] weights, int [] values, int maxWeight) {
        int n = weights.length + 1;
        int [][] dp = new int[maxWeight + 1][n];

        for (int [] t : dp)
            Arrays.fill(t,0);

        for (int i = 1; i <= maxWeight; i++){
            for (int j = 1; j < n; j++){
                int diff = i -  getValue(weights , j);
                dp [i] [j] = Math.max(dp[i][j - 1], (diff >= 0) ? (dp[diff][j - 1] + getValue(values, j)): 0);
            }
        }
        return dp [maxWeight][n - 1];
    }
    public void test () {
        int [] values = {65, 35, 245, 195, 65, 150, 275, 155, 120, 320, 75, 40, 200, 100, 220, 99};
        int [] weights= {20, 8, 60, 55, 40, 70, 85, 25, 30, 65, 75, 10, 95, 50, 40, 10};
        System.out.println(solve(weights, values, 130));
    }
}
