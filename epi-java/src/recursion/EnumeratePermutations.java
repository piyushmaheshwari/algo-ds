package recursion;

import java.util.Arrays;

public class EnumeratePermutations {

    void solveInner (int [] input, int index){
        if (index == input.length) {
            System.out.println(Arrays.toString(input));
            return;
        }

        for (int i = index; i < input.length; i++){
            int t = input [index];
            input [index] = input [i];
            input [i] = t;
            solveInner(input, index + 1);
            t = input [index];
            input [index] = input [i];
            input [i] = t;
        }
    }
    void solve (int [] input){
        solveInner(input, 0);
    }

    public void test () {
        int [] input = {1, 2, 3, 4};
        solve(input);
    }
}
