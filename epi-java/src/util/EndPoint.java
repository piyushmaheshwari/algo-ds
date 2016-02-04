package util;

public class EndPoint implements Comparable<EndPoint> {
    public int time;
    public boolean isStart;

    public EndPoint(int time, boolean isStart) {
        this.time = time;
        this.isStart = isStart;
    }

    @Override
    public int compareTo(EndPoint o) {
        if (this.time != o.time)
            return (this.time - o.time);
        else {
            if (this.isStart)
                return -1;
            else
                return 1;
        }
    }
}
