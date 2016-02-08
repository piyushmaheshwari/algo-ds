package hashTable;

import util.TupleInt;

import java.util.HashMap;
import java.util.Map;

public class LongestContainedRange {
    int solve(int[] input) {
        Map<Integer, TupleInt> cache = new HashMap<>();
        for (int num : input){
            TupleInt self = TupleInt.make(num, num);
            TupleInt leftRange = cache.getOrDefault(num - 1, self);
            TupleInt rightRange = cache.getOrDefault(num + 1, self);
            TupleInt merged = TupleInt.make(leftRange.first, rightRange.second);
            cache.put(merged.first, merged);
            cache.put (merged.second, merged);
        }

        int ans = Integer.MIN_VALUE;

        TupleInt maxTuple =  cache.entrySet().stream().max((Map.Entry<Integer, TupleInt> o1, Map.Entry<Integer, TupleInt> o2) ->
            (o1.getValue().second - o1.getValue().first + 1) - (o2.getValue().second - o2.getValue().first + 1)
        ).get().getValue();

        return (maxTuple.second - maxTuple.first + 1);
    }


    public void test () {
        int [] input = {3, -2, 7, 9, 8, 1, 2, 0, 0, 1, 2};
        int [] input2 = {10, 5, 3, 11, 6, 100, 4};
        System.out.println(solve (input));
        System.out.println(solve(input2));
    }
}
