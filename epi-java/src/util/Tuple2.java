package util;


public class Tuple2<L, R> {
    public L first;
    public R second;

    public Tuple2(L first, R second) {
        this.first = first;
        this.second = second;
    }
    public static <L,R>  Tuple2<L,R> make (L first, R second){
        return new Tuple2<>(first, second);
    }

    @Override
    public String toString() {
        return "Tuple2{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}


