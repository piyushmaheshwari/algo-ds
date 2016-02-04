package sorting;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import util.EndPoint;
import util.Interval;


public class RenderCalender {

    public int solve(List<Interval> intervals) {
        List<EndPoint> endPoints = new ArrayList<>();
        for (Interval i : intervals) {
            endPoints.add(new EndPoint(i.begin, true));
            endPoints.add(new EndPoint(i.end, false));
        }

        Collections.sort(endPoints);
        int ans = Integer.MIN_VALUE;
        int counter = 0;
        for (EndPoint e : endPoints) {
            if (e.isStart) {
                counter += 1;
                ans = Math.max(ans, counter);
            } else {
                counter -= 1;
            }
        }
        return ans;
    }

    public void test() {
        Interval[] t = {Interval.make(1, 5), Interval.make(2, 7), Interval.make(4, 5),
                Interval.make(6, 10), Interval.make(8, 9), Interval.make(9, 17), Interval.make(11, 13),
                Interval.make(12, 15), Interval.make(14, 15)};
        System.out.println(solve(Arrays.asList(t)));
    }
}
