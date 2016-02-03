package hashTable;

import util.Tuple2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SmallestSubArrayCoveringSet {

    private void addM(Map<String, Integer> cache, String word) {
        cache.put(word, cache.getOrDefault(word, 0) + 1);
    }

    private void removeM(Map<String, Integer> cache, String word) {
        if (cache.containsKey(word)) {
            Integer val = cache.get(word);
            assert (val != 0);
            if (val == 1)
                cache.remove(word);
            else
                cache.put(word, val - 1);
        }
    }


    String solve(String[] A, Set<String> Q) {
        int minLen = Integer.MAX_VALUE;
        Map<String, Integer> cache = new HashMap<>();
        int left = 0, right = 0;
        int leftAns = -1;
        int rightAns = -1;
        while (left < A.length) {
            if (Q.contains(A[left])) {
                right = left + 1;
                addM(cache, A[left]);
                break;
            }
            left += 1;
        }

        while (right < A.length) {
            if (cache.size() == Q.size()) {
                if (minLen > (right - left)) {
                    minLen = right - left;
                    leftAns = left;
                    rightAns = right;
                }
                minLen = (minLen > (right - left)) ? (right - left) : minLen;
                while (left < right && cache.size() >= Q.size()) {
                    removeM(cache, A[left]);
                }
            }

            if (Q.contains(A[right])) {
                addM(cache, A[right]);
            }
            right += 1;
        }


        String ans;
        if (minLen < Integer.MAX_VALUE) {
            ans = Arrays.asList(A).subList(leftAns, rightAns).toString();
        } else {
            ans = "Not found";
        }

        return ans;
    }

    public void test() {
        String[] A = {"My", "paramount", "object", "in", "this", "struggle", "is", "to", "save", "the", "union",
                ",", "and", "is", "not", "either", "to", "save", "or", "to", "destroy", "slavery",
                ".", "If", "I", "could", "save", "union", "without", "freeing", "any", "slave", "I", "would", "do", "it", ",", "and",
                "I", "could", "save", "it", "by", "freeing", "the", "slaves", "I", "would", "do", "it", ";"
        };
        String[] Q = {"save", "union"};
        System.out.println(solve(A, new HashSet<>(Arrays.asList(Q))));

    }
}
