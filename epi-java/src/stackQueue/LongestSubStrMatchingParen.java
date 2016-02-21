package stackQueue;

import java.util.Arrays;
import java.util.Stack;

public class LongestSubStrMatchingParen {

    int solve(String str) {
        Stack<Integer> s = new Stack<>();
        int[] count = new int[str.length()];
        Arrays.fill(count, 0);
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '(') {
                if (!s.empty()) {
                    count[i] = s.pop() - i + 1;
                }
            } else {
                s.push(i);
            }
        }
        int ans = Integer.MIN_VALUE;
        int prev = 0;

        int i = 0;
        while (i < str.length()) {
            if (count[i] == 0) {
                ans = Math.max(ans, prev);
                prev = 0;
                i += 1;
            } else {
                prev += count[i];
                i += count[i];
            }
        }
        ans = Math.max(ans, prev);
        return ans;
    }


    public void test() {
        assert (solve("((())()(()(") == 6);
        assert (solve(")(((())()(()(") == 6);
        assert (solve("((())()(()(") == 6);
        assert (solve(")(") == 0);
        assert (solve("()") == 2);
        assert (solve("") == 0);
        assert (solve("()()())") == 6);
    }
}
