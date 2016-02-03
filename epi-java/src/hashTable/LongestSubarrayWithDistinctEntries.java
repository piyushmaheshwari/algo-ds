package hashTable;

import util.Tuple2;

import java.util.HashSet;
import java.util.Set;


public class LongestSubarrayWithDistinctEntries {

    class TupleInt extends Tuple2<Integer, Integer> {
        TupleInt(Integer x, Integer y) {
            super(x, y);
        }
    }

    TupleInt solve(int[] input) {
        Set<Integer> distinct = new HashSet<>();
        int maxFound = Integer.MIN_VALUE;
        Integer left = 0;
        Integer right = 0;
        TupleInt ans = new TupleInt(-1, -1);
        while (right < input.length) {
            if (distinct.contains(input[right])) {
                while (left < right) {
                    distinct.remove(input[left]);
                    left += 1;
                    if (input[left - 1] == input[right])
                        break;
                }
            }
            distinct.add(input[right]);
            if (maxFound < (right - left + 1)) {
                maxFound = right - left + 1;
                ans = new TupleInt(left, right);
            }
            right += 1;
        }
        return ans;
    }

    public void test() {
        int[] input = {5, 7, 5, 11, 13, 2, 11, 19, 2, 11};
        System.out.println(solve(input));
        int[] input2 = {1, 2, 2, 3, 3, 1, 1, 2, 1};
        System.out.println(solve(input2));

    }
}
