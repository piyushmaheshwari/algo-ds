package heaps;

import util.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeArrays {
    List<Integer> mergeKArrays (List<List<Integer>> input) {
        int  k = input.size();
        PriorityQueue<Tuple2<Integer, Integer>> q = new PriorityQueue<>(k,
                (first, second)->(first.first.compareTo(second.first)));
        int [] indexes = new int[k];
        for (int i = 0; i < k; i++){
            if (input.get(i).size() > 0){
                q.add(new Tuple2<>(input.get(i).get(0), i));
                indexes [i] = 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()){
            Tuple2<Integer, Integer> v = q.poll();
            ans.add (v.first);
            int index = indexes [v.second];
            indexes [v.second] += 1;
            if (index < input.get(v.second).size())
                q.add(new Tuple2<>(input.get(v.second).get(index), v.second));
        }
        return ans;
    }

    public void test (){
        Integer [] a1 = {2,5,7,9};
        Integer [] a2 = {1,4,11};
        List<List<Integer>> input = new ArrayList<>();
        input.add (Arrays.asList(a1));
        input.add (Arrays.asList(a2));
        System.out.println(mergeKArrays(input));
    }
}
