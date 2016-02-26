package random;

import util.TupleInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Given a number n, return the shortest list of numbers such that sum of squares of all numbers is equal to n
public class DecomposeNumSquares {

    Integer decompose (int n, Map<Integer, TupleInt> cache){
        if (cache.containsKey(n))
            return cache.get(n).first;
        else {
            int ans = Integer.MAX_VALUE;
            int prev = Integer.MAX_VALUE;
            int k = (int) Math.sqrt(n);
            if (k * k == n){
                ans = 1;
                prev = k;
            } else {
                while (k > 0){
                    int recur = decompose(n - k * k, cache);
                    if (ans > recur + 1){
                        ans = recur + 1;
                        prev = k;
                    }
                    k -= 1;
                }
            }
            cache.put(n, new TupleInt(ans, prev));
            return ans;
        }
    }

    List<Integer> solve (int n){
        Map<Integer, TupleInt> cache = new HashMap<>();
        decompose(n, cache);
        List<Integer> ans = new ArrayList<>();
        int k = n;
        while (k > 0){
            TupleInt x = cache.get(k);
            ans.add(x.second);
            k = k - (x.second *  x.second);
        }

        return ans;
    }

    public void test (){
        System.out.println(solve(13));
    }
}
