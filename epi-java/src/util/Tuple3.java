package util;

public class Tuple3<L, R, S> {
    public L first;
    public R second;
    public S third;

    public Tuple3(L first, R second, S third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() {
        return "Tuple3{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
