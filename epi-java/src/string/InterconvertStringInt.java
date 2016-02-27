package string;

public class InterconvertStringInt {

    String intToString (int arg){
        if (arg == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        boolean neg = arg < 0;
        arg = Math.abs(arg);
        while (arg > 0) {
            sb.append((char)('0' + arg % 10));
            arg = arg / 10;
        }
        if (neg)
            sb.append('-');
        String ans = sb.reverse().toString();
        return ans;
    }

    int stringToInt(String arg) {
        boolean neg = false;
        int index = 0;
        if (arg.charAt(index) == '-') {
            neg = true;
            index += 1;
        }

        int ans = 0;
        while (index < arg.length()) {
            ans = ans * 10 + (arg.charAt(index) - '0');
            index += 1;
        }
        if (neg)
            ans = ans * -1;
        return ans;
    }

    public void test (){
        System.out.println(stringToInt("123"));
        System.out.println(stringToInt("-342"));
        System.out.println(stringToInt("2147483647"));
        System.out.println(stringToInt("0"));
        System.out.println(stringToInt("6"));
        System.out.println(stringToInt("-4"));
        System.out.println(intToString(-2));
        System.out.println(intToString(0));
        System.out.println(intToString(-10));
        System.out.println(intToString(123434));
    }

}
