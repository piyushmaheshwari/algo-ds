package stackQueue;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EvaluateRPNExpression {

    boolean isOp (Character c){
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    int charToInt (Character c){
        return c - '0';
    }
    int apply (int first, int second, Character op){
        if (op == '+') return first + second;
        else if (op == '*') return first * second;
        else if (op == '-') return first - second;
        else if (op == '/') return first / second;
        else throw new IllegalArgumentException ("Operator not recognized " + op);
    }

    Character intToChar (int t){
        return (char)(t + (int)'0');
    }

    int solve (List<Character> expr){

        Stack<Character> s = new Stack<>();
        for (Character c: expr) {
            Character p = c;
            if (isOp(c)){
                if ((s.size() == 0 && c != '-') || (s.size() == 1)){
                    throw new IllegalArgumentException("Malformed expression passed");
                } else if (s.size() > 1){
                    int op2 = charToInt(s.pop());
                    int op1 = charToInt(s.pop());
                    int result = apply (op1, op2, c);
                    p = intToChar(result);
                }
            }
            s.push(p);
        }

        int ans = 0;
        int count = 0;
        while (!s.empty()){
            char v = s.pop();
            if (v != '-'){
                ans += charToInt(v) * Math.pow(10, count);
                count += 1;
            } else
                ans = ans * -1;
        }
        return ans;
    }

    public void test () {
        assert (solve(Arrays.asList('1')) == 1);
        assert (solve(Arrays.asList('1', '2', '+')) == 3);
        assert (solve(Arrays.asList('1', '2', '-')) == -1);
        assert (solve(Arrays.asList('1', '2', '/')) == 0);
        assert (solve(Arrays.asList('1', '2', '3')) == 123);
        assert (solve(Arrays.asList('-', '2', '3')) == -23);
        assert (solve(Arrays.asList('6', '2', '/', '3', '/')) == 1);
        assert(12 == solve(Arrays.asList('1','0','2','+')));
        assert(15 == solve(Arrays.asList('1','2','+','3','4','*','+')));
        assert(42 == solve(Arrays.asList('1','2','3','4','5','+','*','+','+','3','4','*','+')));
    }
}
