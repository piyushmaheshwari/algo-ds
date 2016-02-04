package util;

public class Interval {
    public int begin;
    public int end;

    public Interval(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public static Interval make(int begin, int end) {
        return new Interval(begin, end);
    }
}
