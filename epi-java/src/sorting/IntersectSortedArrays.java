package sorting;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays {
    List<Integer> solve (int [] a, int [] b) {
        int A = a.length;
        int B = b.length;
        List<Integer> c = new ArrayList<>();
        int first = 0;
        int second = 0;
        while (first < A && second < B){
            if (a[first] == b[second]){
                second += 1;
                continue;
            }

            else if (a[first] > b[second]) {
                c.add(b[second]);
                second += 1;
            } else {
                c.add (a[first]);
                first += 1;
            }
        }
        while (first < A){
            c.add (a[first++]);
        }
        while (second < B) {
            c.add (b[second++]);
        }
        return c;
    }

    public void test (){
        int [] a = { 1, 2, 5, 8, 9};
        int [] b = { 1, 3, 4, 8, 9};
        System.out.println(solve(a,b));
    }
}
