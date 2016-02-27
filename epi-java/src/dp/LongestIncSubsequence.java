package dp;

import java.util.*;

public class LongestIncSubSequence {

    int binarySearch(int[] arr, int low, int high, int val) {
        int ans = high + 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > val) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    List<Integer> solve(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int[] index = new int[n];
        int[] parent = new int[n];
        int high = 0;
        dp[0] = arr[0];
        parent[0] = -1;
        index[0] = 0;
        for (int i = 1; i < n; i++) {
            int t = binarySearch(dp, 0, high, arr[i]);
            dp[t] = arr[i];
            index[t] = i;
            parent[i] = index[t - 1];
            if (t > high)
                high = t;
        }

        List<Integer> result = new ArrayList<>();
        int p = index[high];
        while (p != -1) {
            result.add(arr[p]);
            p = parent[p];
        }

        Collections.reverse(result);
        return result;
    }

    public void test() {
        int[] input = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9};
        System.out.println(solve(input));
    }
}


