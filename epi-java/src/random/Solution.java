package random;

import java.io.*;
import java.util.*;

public class Solution {

    public static long solve(int arg, int n) {
        long ans = 0;
        long x = n / arg;
        ans = (arg * x * (x + 1)) / 2;
        return ans;
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int t = in.nextInt();
        while (t != 0) {
            int n = in.nextInt();
            long ans = solve(3, n) + solve(5, n) - solve(15, n);
            out.println(ans);
            t -= 1;
        }
        out.close();
    }


}

class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
