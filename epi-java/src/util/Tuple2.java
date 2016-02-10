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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;

        if (first != null ? !first.equals(tuple2.first) : tuple2.first != null) return false;
        return second != null ? second.equals(tuple2.second) : tuple2.second == null;

    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}


