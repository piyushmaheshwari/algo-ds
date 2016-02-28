package string;

public class ReverseWords {

    String reverseInPlace (String arg, int start, int end){
        char [] buffer = arg.toCharArray();
        while (start < end){
            char t = buffer [start];
            buffer [start] = buffer [end];
            buffer [end] = t;
            start += 1;
            end -= 1;
        }
        return new String (buffer);
    }

    String solve (String arg) {
        String reverse  = reverseInPlace(arg, 0, arg.length() - 1);

        int start = 0;
        int end;
        while ((end = reverse.indexOf(' ', start)) != -1){
            reverse = reverseInPlace(reverse, start, end - 1);
            start = end + 1;
        }
        end = reverse.length() - 1;
        reverse= reverseInPlace(reverse, start, end);
        return reverse;
    }


    public void test () {

        System.out.println('#' + solve("I am man") + '#');
        System.out.println('#' + solve("Alice likes Bob") + '#');
    }
}
