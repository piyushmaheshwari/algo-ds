package recursion;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GenerateParentheses {
    Set<String> solve(int k) {
        Set<String> ans = new HashSet<>();
        if (k == 0)
            ans.add("");
        else {
            for (int inside = 0; inside < k; inside++) {
                Set<String> innerSet = solve(inside);
                Set<String> outerSet = solve(k - inside - 1);
                Set<String> concat = innerSet.stream().map(s -> '(' + s + ')').collect(Collectors.toSet());
                for (String first : concat)
                    for (String second : outerSet)
                        ans.add(first + second);
            }
        }
        return ans;
    }

    public void test () {
        System.out.println(solve(3));
    }
}
