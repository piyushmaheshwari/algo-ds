package greedy;

import java.util.*;

public class LargestRectangleSkyline {

    int calculateLargestRectangle(List<Integer> heights) {
        int n = heights.size();
        int[] leftScan = new int[n];
        int[] rightScan = new int[n];
        int ans = Integer.MIN_VALUE;

        leftScan[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            int temp = i + 1;
            while (temp != n && heights.get(temp) >= heights.get(i))
                temp = leftScan[temp];
            leftScan[i] = temp;
        }

        rightScan[0] = -1;
        for (int i = 1; i < n; i++) {
            int temp = i - 1;
            while (temp != -1 && heights.get(temp) >= heights.get(i))
                temp = rightScan[temp];
            rightScan[i] = temp;
        }

        for (int i = 0; i < n; i++) {
            int left = (leftScan[i] - i);
            int right = (i - rightScan[i]);
            ans = Math.max(ans, (left + right - 1) * heights.get(i));
        }
        return ans;
    }

    // O(n^2) implementation checks answer.
    private static int checkAnswer(List<Integer> heights) {
        int max = -1;
        for (int i = 0; i < heights.size(); ++i) {
            int left = i - 1, right = i + 1;
            while (left >= 0 && heights.get(left) >= heights.get(i)) {
                --left;
            }
            while (right < heights.size() && heights.get(right) >= heights.get(i)) {
                ++right;
            }
            int area = (right - left - 1) * heights.get(i);
            if (area > max) {
                max = area;
            }
        }
        System.out.println(max);
        return max;
    }

    public void test() {
        List<Integer> heights = Arrays.asList(2, 3, 4, 1, 2);
        int area = calculateLargestRectangle(heights);
        System.out.println(area);
        assert (checkAnswer(heights) == area);
        assert (6 == area);
        heights = Arrays.asList(2, 2, 2);
        System.out.println(calculateLargestRectangle(heights));
        assert (6 == calculateLargestRectangle(heights));
        heights = Arrays.asList(1, 1, 2);
        System.out.println(calculateLargestRectangle(heights));
        assert (3 == calculateLargestRectangle(heights));
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n;
            n = r.nextInt(1000) + 1;
            List<Integer> heights1 = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                heights1.add(r.nextInt(999));
            }
            int area1 = calculateLargestRectangle(heights1);
            System.out.println(area1);
            if (checkAnswer(heights1) != area1)
                System.out.println(heights1);
            assert (checkAnswer(heights1) == area1);
        }
    }
}
