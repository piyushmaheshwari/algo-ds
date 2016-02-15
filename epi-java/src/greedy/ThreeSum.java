package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {

    List<List<Integer>> solve2Sum(List<Integer> input, int start, int sum) {
        int end = input.size() - 1;
        List<List<Integer>> ans = new ArrayList<>();
        while (start <= end) {
            int t = input.get(start) + input.get(end);
            if (t < sum)
                start += 1;
            else if (t > sum)
                end -= 1;
            else {
                ans.add(Arrays.asList(input.get(start), input.get(end)));
                end -= 1;
                start += 1;
            }
        }
        return ans;
    }

    List<List<Integer>> solve(List<Integer> input, int sum) {
        Collections.sort(input);
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            int val = input.get(i);
            final List<List<Integer>> twoSum = solve2Sum(input, i, sum - val);
            twoSum.forEach(s -> {
                List<Integer> found =  new ArrayList<Integer>();
                found.add(val);
                found.addAll(s);
                ans.add(found);
            });
        }
        return ans;
    }

    public void test() {
        List<Integer> input = Arrays.asList(5, 2, 3, 4, 3);
        List<List<Integer>> ans = solve(input, 9);
        ans.forEach(s -> System.out.println(s));
    }
}
