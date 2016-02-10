package recursion;

import java.util.*;
import java.util.stream.Collectors;


public class ComputeGreyCode {

    List<String> solve (int n){
        if (n == 1){
            return Arrays.asList("0", "1");
        } else {
            List<String> prev = solve(n-1);
            List<String> zeros = prev.stream().map(s -> '0' + s).collect(Collectors.toList());
            Collections.reverse(prev);
            List<String> ones = prev.stream().map(s -> '1' + s).collect(Collectors.toList());
            zeros.addAll(ones);
            return zeros;
        }
    }

    public void test (){
        System.out.println(solve(3));
    }
}
