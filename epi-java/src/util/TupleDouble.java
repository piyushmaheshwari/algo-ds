package util;

public class TupleDouble {
    public Double first;
    public Double second;
    public TupleDouble(Double x, Double y) {
        this.first = x;
        this.second = y;
    }

    public static TupleDouble make (Double first, Double second){
        return new TupleDouble(first, second);
    }
}
