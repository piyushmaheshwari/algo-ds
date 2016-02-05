package util;

public class TupleInt extends Tuple2<Integer, Integer> {
    public TupleInt(Integer x, Integer y) {
        super(x, y);
    }

    public static TupleInt make (Integer first, Integer second){
        return new TupleInt(first, second);
    }

}
