package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnumeratePowerSet {
    void solve (List<Integer> input, int index, Set<Integer> answer){
        if (index == input.size()){
            System.out.println(answer);
            return;
        }
        solve (input, index + 1, answer);
        answer.add(input.get(index));
        solve(input, index + 1, answer);
        answer.remove(input.get(index));
    }

    void solve (Set<Integer> input){
        solve(new ArrayList<>(input), 0, new HashSet<>());
    }
    public void test (){
        solve(new HashSet<>(Arrays.asList(1,2,3,4)));
    }
}
