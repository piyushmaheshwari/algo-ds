package random;

import java.util.*;

class Interval implements Comparable<Interval> {
    public int start;
    public int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString (){
        return "[" + start + "-" + end + "]";
    }
    @Override
    public int compareTo(Interval that) {
        return (this.start - that.start);
    }

    boolean doesIntersect(Interval that) {
        if ((that.end < this.start) || (that.start > this.end))
            return false;
        else
            return true;
    }

    Interval merge(Interval that) {
        return new Interval(Math.min(that.start, this.start), Math.max(that.end, this.end));
    }

    void mergeIn (Interval that) {
        this.start = Math.min(that.start, this.start);
        this.end = Math.max (that.end, this.end);
        return;
    }

    public static List<Interval> mergeIntervals(List<Interval> intervals) {
        List<Interval> retList = new ArrayList<Interval>();
        if (intervals.size() == 0)
            return new ArrayList<>();

        Interval current = null;
        Collections.sort(intervals);
        for (int i = 0; i < intervals.size(); i++) {
            if (i == 0) {
                current = new Interval(intervals.get(i).start, intervals.get(i).end);
            } else {
                if (current.doesIntersect(intervals.get(i))) {
                    current.mergeIn(intervals.get(i));
                } else {
                    retList.add(current);
                    current = new Interval(intervals.get(i).start, intervals.get(i).end);
                }
            }
        }
        retList.add(current);
        return retList;
    }
}

