package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GassingUp {

    private static final int MPG = 20;

    // O(n) time, O(n) space
    public int solve(List<Integer> gallons, List<Integer> distances) {
        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < gallons.size(); i++) {
            diff.add(gallons.get(i) * MPG - distances.get(i));
        }

        int N = distances.size();

        //forward [i] represents the min sub array in diff[0-i] starting at index 0
        int[] forward = new int[N + 1];
        //backward [i] represents the min sub array in diff [i-N] starting at index i
        int[] backward = new int[N + 1];
        //sum [i] represents the sum of elements of diff from 1 to i
        int[] sum = new int[N + 1];
        sum[0] = 0;
        for (int i = 1; i <= N; i++)
            sum[i] = sum[i - 1] + diff.get(i - 1);

        forward[1] = diff.get(0);
        for (int i = 2; i <= N; i++) {
            forward[i] = Math.min(forward[i - 1], sum[i]);
        }

        backward[N] = diff.get(N - 1);
        for (int i = N - 1; i > 0; i--) {
            backward[i] = Math.min(backward[i + 1] + diff.get(i - 1), diff.get(i - 1));
        }

        for (int i = 1; i <= N; i++) {
            int back = forward[i - 1] + (sum[N] - sum[i - 1]);
            int ans = Math.min(backward[i], back);
            if (ans >= 0) {
                return i - 1;
            }
        }
        return -1;
    }


    public void test() {
        List<Integer> gallons
                = Arrays.asList(20, 15, 15, 15, 35, 25, 30, 15, 65, 45, 10, 45, 25);
        List<Integer> distances = Arrays.asList(
                15 * MPG, 20 * MPG, 50 * MPG, 15 * MPG, 15 * MPG, 30 * MPG, 20 * MPG,
                55 * MPG, 20 * MPG, 50 * MPG, 10 * MPG, 15 * MPG, 15 * MPG);
        //List<Integer> gallons = Arrays.asList(20, 20, 20);
        //List<Integer> distances = Arrays.asList(400, 500, 300);
        System.out.println(solve(gallons, distances));
    }
}
