package hashTable;

import java.util.HashMap;
import java.util.Map;

public class NearestRepetition {

    void solve (String [] input) {
        int minDist = Integer.MAX_VALUE;
        int minStart = -1;
        int minEnd = -1;
        String minString = null;
        Map<String, Integer> cache = new HashMap<>();
        for (int i = 0; i < input.length; i++){
            Integer oldIndex = cache.put(input[i], i);
            if (oldIndex != null && (i - oldIndex < minDist)){
                minDist = i - oldIndex;
                minStart = oldIndex;
                minEnd = i;
                minString = input [i];
            }
        }
        System.out.println("Ans : " + minString + " at index : " + minStart + " " + minEnd);
    }
    public void test () {
        String [] input = {"All", "work", "and", "no", "play", "makes", "for", "no", "work", "no", "fun", "and", "no", "results"};
        solve (input);
    }
}
