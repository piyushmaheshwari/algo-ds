package string;

import java.util.ArrayList;
import java.util.List;

public class ComputeValidIPAddress {

    public static final int PARTS = 4;

    boolean isValid (int start, int end, int index, String input){
        return (index <= end) && (index - start + 1 <= 3) &&
                (Integer.parseInt(input.substring(start, index + 1)) < 256);
    }

    void solveInner(String input, int index, int part, String formed, List<String> ans) {
        if (index == input.length()) {
            if (part == PARTS + 1)
                ans.add(formed.substring(0, formed.length() - 1));
            return;
        } else if (part > PARTS){
            return;
        }
        int start = index;
        int end = input.length() - 1 - (PARTS - part);
        for (int i = start; isValid(start, end, i, input); i++) {
            String con = formed + input.substring(start, i + 1) + '.';
            solveInner(input, i + 1, part + 1, con, ans);
        }
    }

    List<String> solve(String ip) {
        List<String> ans = new ArrayList<>();
        solveInner(ip, 0, 1, "", ans);
        return ans;
    }

    public void test() {
        System.out.println(solve("19216811"));
    }
}
