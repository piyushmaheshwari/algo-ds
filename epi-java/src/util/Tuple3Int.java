package util;

public class Tuple3Int extends Tuple3<Integer, Integer, Integer> {

    public Tuple3Int (Integer first, Integer second, Integer third){
        super (first, second, third);
    }

    public static Tuple3Int make (Integer first, Integer second, Integer third){
        return new Tuple3Int(first, second, third);
    }
}
