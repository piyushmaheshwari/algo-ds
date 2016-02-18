package greedy;

import util.TupleInt;

import java.util.Random;

public class LongestSubArrayLessK {

    TupleInt solve(int[] input, int k) {
        int n = input.length;
        int[] lesser = new int[n];
        int[] lesserIndex = new int[n];
        lesser[n - 1] = input[n - 1];
        lesserIndex[n - 1] = n - 1;

        for (int i = n - 2; i >= 0; i--) {
            if (lesser[i + 1] + input[i] < input[i]) {
                lesser[i] = lesser[i + 1] + input[i];
                lesserIndex[i] = lesserIndex[i + 1];
            } else {
                lesser[i] = input[i];
                lesserIndex[i] = i;
            }
        }

        int left = 0;
        int right = 1;
        int running = input[0];
        TupleInt ans = TupleInt.make(-1, -1);

        if (input[0] <= k) {
            ans = TupleInt.make(0, 0);
        }

        while (right < n) {
            if (running + input[right] <= k) {
                running += input[right];
            } else {
                if (running + lesser[right] > k) { //this means we can't get that subarray from left, so move left forward
                    while (left < right && (running + lesser[right] > k)) {
                        running -= input[left];
                        left += 1;
                    }
                    running += input[right];
                } else {
                    running += lesser[right];
                    right = lesserIndex[right];
                }
            }
            if (running <= k && (right - left > ans.second - ans.first)) {
                ans = TupleInt.make(left, right);
            }
            right += 1;
        }
        return ans;
    }

    boolean checkAns(int[] input, TupleInt ans, int k) {
        TupleInt bruteAns = TupleInt.make(0, -1);
        int n = input.length;

        int [] sum = new int[n];
        sum [0] = input [0];
        for (int i = 1; i < n; i++) {
            sum [i] = sum [i - 1] + input [i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) { //subarray from i to j
                int s = sum [j] - ((i - 1 >= 0) ? sum [i - 1] : 0);
                if (s <= k && ((j - i) > (bruteAns.second - bruteAns.first)))
                    bruteAns = TupleInt.make(i, j);
            }
        }

        if (ans.equals(bruteAns))
            return true;
        else
            return false;
    }

    public void test() {
        int[] input = {431, -15, 639, 342, -14, 565, -924, 635, 167, -70};
        assert (solve(input, 184).equals(TupleInt.make(3,6)));
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n, k;
            n = r.nextInt(10000) + 1;
            k = r.nextInt(10000);
            int [] A = new int[n];
            for (int i = 0; i < n; ++i) {
                A [i] = (r.nextInt(2001) - 1000);
            }
            TupleInt ans = solve(A, k);
            System.out.println(k + " " + ans);
            assert (checkAns(A, ans, k));
        }
    }
}
