package random;

public class BigInt {
    String number;

    public BigInt (){}

    public BigInt(String number) {
        this.number = new StringBuilder(number).reverse().toString();
    }

    private String multiplyInner (String first, String second){
        if (second.length() == 1)
            return multiplyDigit(first, second.charAt(0));
        else {
            char f = second.charAt(0);
            String remaining = second.substring(1);
            String result1 = multiplyDigit(first, second.charAt(0));
            String result2 = '0' + multiplyInner(first, remaining);
            return addInner(result2 ,result1);
        }
    }

    public BigInt multiply(BigInt n) {
        String result = multiplyInner(this.number, n.number);
        return new BigInt(new StringBuilder(result).reverse().toString());
    }
    
    private String multiplyDigit(String number, char n) {
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < number.length(); i++) {
            int v = (number.charAt(i) - '0') * (n - '0');
            sb.append(v % 10);
            carry = v / 10;
        }

        if (carry > 0) {
            sb.append(carry + '0');
        }
        return sb.toString();
    }

    private String addInner (String first, String second){

        String small = (first.length() > second.length()) ? second : first;
        String bigger = (first.length() > second.length()) ? first : second;

        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < small.length(); i++) {
            int v = (small.charAt(i) - '0') + (bigger.charAt(i) - '0') + carry;
            sb.append(v % 10);
            carry = v / 10;
        }

        while (i < bigger.length()) {
            int v = (bigger.charAt(i) - '0') + carry;
            sb.append(v % 10);
            carry = v / 10;
            i += 1;
        }

        if (carry > 0)
            sb.append(Integer.toString(carry));

        return sb.toString();
    }

    public BigInt add(BigInt n) {
        String result = addInner(this.number, n.number);
        return new BigInt(new StringBuilder(result).reverse().toString());

    }

    public String toString() {
        return new StringBuilder(this.number).reverse().toString();
    }

    public void test() {
        BigInt num1 = new BigInt("123");
        BigInt num2 = new BigInt("123");
        BigInt num3 = num1.add(num2);
        BigInt num4 = num1.multiply(num2);
        System.out.println(num3.toString());
        System.out.println(num4.toString());
    }
}
