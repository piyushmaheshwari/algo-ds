package stackQueue;

import util.TupleDouble;

import java.util.*;

public class MaxSlidingWindow {

    List<Double> solve(List<TupleDouble> traffic, int size) {
        Deque<TupleDouble> window = new LinkedList<>();
        int index = 0;
        List<Double> ans = new ArrayList<>();

        while (index < traffic.size()) {
            TupleDouble currTuple = traffic.get(index);
            double currTime = currTuple.second;
            double currTraffic = currTuple.first;
            while (!window.isEmpty() &&
                    (window.peekFirst().second + size < currTime))
                window.pollFirst();

            while (!window.isEmpty() && window.peekLast().first <= currTraffic)
                window.pollLast();

            window.addLast(currTuple);
            ans.add(window.peekFirst().first);
            index += 1;
        }
        return ans;
    }

    List<Double> checkAns (List<TupleDouble> traffic, int size){
        List<Double> ans = new ArrayList<>();
        for (int i = 0; i < traffic.size(); i++) {
            int start = i;
            double temp = Double.MIN_VALUE;
            while (start >= 0 &&
                    traffic.get(start).second + size >= traffic.get(i).second){
                temp = Math.max (temp, traffic.get(start).first);
                start -= 1;
            }
            ans.add(temp);
        }
        return ans;
    }

    public void test (){
        int w = 3;
        List<TupleDouble> A = Arrays.asList(
                new TupleDouble(1.3, 0.0), new TupleDouble(2.5, 2.0),
                new TupleDouble(3.7, 3.0), new TupleDouble(1.4, 5.0),
                new TupleDouble(2.6, 6.0), new TupleDouble(2.2, 8.0),
                new TupleDouble(1.7, 9.0), new TupleDouble(1.1, 14.0));
        System.out.println(checkAns(A, w));
        System.out.println(solve(A, w));
    }
}
