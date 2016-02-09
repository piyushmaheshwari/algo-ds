package recursion;

import java.util.ArrayList;
import java.util.List;

public class TowersOfHanoi {

    String getMove(String from, String to) {
        return "Move from " + from + " to " + to;
    }

    List<String> move(String from, String to, String intermediate, int rings) {
        List<String> ans = new ArrayList<>();

        if (rings == 1) {
            ans.add(getMove(from, to));
        } else {
            ans.addAll(move(from, intermediate, to, rings - 1));
            ans.add(getMove(from, to));
            ans.addAll(move(intermediate, to, from, rings - 1));
        }
        return ans;
    }

    List<String> solve(int n) {
        return move("P1", "P2", "P3", n);
    }

    public void test() {
        System.out.println(solve(10).size());
    }
}
