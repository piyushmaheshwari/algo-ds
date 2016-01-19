package dp;

public class LongestIncSubsequence {


    int binarySearch(int[] arr, int low, int high, int val) {
        int index = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > val) {
                high = mid - 1;
            } else { // arr[mid] <= val
                if ((mid + 1 > high) || (arr[mid + 1] >= val)) {
                    index = mid + 1;
                }
                low = mid + 1;
            }
        }
        return index;
    }

    int solve(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        int high = 0;
        dp [0] = arr [0];
        for (int i = 1; i < n; i++){
            int t = binarySearch(dp, 0, high, arr[i]);
            dp [t] = arr[i];
            if (t > high)
                high = t;
        }
        for (int i = 0; i < high + 1; i++){
            System.out.print(dp[i] + " ");
        }
        System.out.println("");

        return high + 1;
    }

    public void test() {
        int[] input = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9};
        //int [] test = {1,3,5};
        System.out.println(solve(input));
    }
}
