package dp;

import java.util.Arrays;

public class TestTiePossible {

    int getVal (int [] arr, int index){
        return arr[index-1];
    }

    //subset sum
    boolean solve(int[] set, int sum) {
        int n = set.length;
        boolean dp[][] = new boolean[sum + 1][n + 1];
        for (boolean[] t : dp)
            Arrays.fill(t, false);
        for (int i = 0; i <= n; i++)
            dp[0][i] = true;

        for (int s = 1; s <= sum; s++){
            for (int ele = 1; ele <= n; ele++){
                int diff = s - getVal(set, ele);
                dp [s][ele] = dp [s][ele-1] || ((diff >= 0) ? dp [diff][ele - 1] : false) ;
            }
        }
        return dp [sum][n];
    }

    public void test() {
        int [] set = {2, 3, 4, 5};
        System.out.println(solve(set, 1));
    }
}
