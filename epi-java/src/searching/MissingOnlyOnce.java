package searching;

import java.util.Arrays;

public class MissingOnlyOnce {

    int[] masks = {1,2,4,8};

    int findMissingOnce (int [] input){
        int [] bitSums = new int [32];
        Arrays.fill(bitSums, 0);
        for (int i = 0; i < 32; i++){
            for (int j = 0; j < input.length; j++) {
                if ((input [j] & (1 << i)) != 0){
                    bitSums [i] += 1;
                }
            }
        }
        int result = 0 ;
        for (int i = 0; i < 32; i++){
            bitSums [i] = bitSums [i] % 3;
            if (bitSums [i] == 1)
                result += Math.pow(2,i);
        }

        return result;
    }

    public void test (){
        int [] input = {0,0,0,3,3,3,101,101,101, -1};
        System.out.println(findMissingOnce(input));
    }
}
