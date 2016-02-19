package greedy;

import java.util.*;

public class LargestRectangleSkyline {

    int calculateLargestRectangle(List<Integer> heights) {
        int n = heights.size();
        int[] leftScan = new int[n];
        leftScan[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            if (heights.get(i) > heights.get(i + 1))
                leftScan[i] = i + 1;
            else {
                int temp = leftScan[i + 1];
                while (temp != n && heights.get(temp) >= heights.get(i))
                    temp = leftScan[temp];
                leftScan[i] = temp;
            }
        }


        int[] rightScan = new int[n];
        rightScan[0] = -1;
        for (int i = 1; i < n; i++) {
            if (heights.get(i) > heights.get(i - 1))
                rightScan[i] = i - 1;
            else {
                int temp = rightScan[i - 1];
                while (temp != -1 && heights.get(temp) >= heights.get(i))
                    temp = rightScan[temp];
                rightScan[i] = temp;
            }
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int left = (leftScan[i] - i);
            int right = (i - rightScan[i]);
            ans = Math.max(ans, (left + right - 1) * heights.get(i));
        }

        return ans;
    }

    public static int calculateLargestRectangleAlternative(
            List<Integer> heights) {
        // Calculate L.
        Deque<Integer> s = new LinkedList<>();
        List<Integer> L = new ArrayList<>();
        for (int i = 0; i < heights.size(); ++i) {
            while (!s.isEmpty() && heights.get(s.peekFirst()) >= heights.get(i)) {
                s.removeFirst();
            }
            L.add(s.isEmpty() ? Integer.valueOf(-1) : s.peekFirst());
            s.addFirst(i);
        }

        // Clear stack for calculating R.
        while (!s.isEmpty()) {
            s.removeFirst();
        }
        List<Integer> R = new ArrayList<>(Collections.nCopies(heights.size(), 0));
        for (int i = heights.size() - 1; i >= 0; --i) {
            while (!s.isEmpty() && heights.get(s.peekFirst()) >= heights.get(i)) {
                s.removeFirst();
            }
            R.set(i, s.isEmpty() ? heights.size() : s.peekFirst());
            s.addFirst(i);
        }

        // For each heights.get(i), find its maximum area include it.
        int maxRectangleArea = 0;
        for (int i = 0; i < heights.size(); ++i) {
            maxRectangleArea = Math.max(maxRectangleArea,
                    heights.get(i) * (R.get(i) - L.get(i) - 1));
        }
        return maxRectangleArea;
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
//        List<Integer> heights = Arrays.asList(2, 3, 4, 1, 2);
//        int area = calculateLargestRectangle(heights);
//        //int alterArea = calculateLargestRectangleAlternative(heights);
//        //assert(area == alterArea);
//        System.out.println(area);
//        assert (checkAnswer(heights) == area);
//        assert (6 == area);
//        heights = Arrays.asList(2, 2, 2);
//        System.out.println(calculateLargestRectangle(heights));
//        assert (6 == calculateLargestRectangle(heights));
//        heights = Arrays.asList(1, 1, 2);
//        System.out.println(calculateLargestRectangle(heights));
//        assert (3 == calculateLargestRectangle(heights));
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
            if (checkAnswer(heights1)!= area1)
                System.out.println(heights1);
            assert (checkAnswer(heights1) == area1);
        }
    }
}
