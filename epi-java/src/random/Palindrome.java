package random;

import java.util.HashSet;


public class Palindrome {
    static class Pair<T, V> {
        private T first;
        private V second;

        Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T first() {
            return this.first;
        }

        public V second() {
            return this.second;
        }
    }

    public int numberPalinSubString(String input) {
        HashSet<String> distinct = new HashSet<>();
        input = input.toLowerCase();
        //find all even length palindromes
        for (int index = 1; index < input.length(); index++) {
            int len = 1;
            while ((index - len) >= 0 && (index + len - 1) < input.length()) {
                if (input.charAt(index + len - 1) == input.charAt(index - len)) {
                    distinct.add(input.substring(index - len, index + len));
                } else {
                    break;
                }
                len++;
            }
        }
        //find all odd length palindromes
        for (int i = 0; i < input.length(); i++) {
            distinct.add(input.substring(i, i + 1));
            int len = 1;
            while ((i - len) >= 0 && (i + len) < input.length()) {
                if (input.charAt(i + len) == input.charAt(i - len)) {
                    distinct.add(input.substring(i - len, i + len + 1));
                } else {
                    break;
                }
                len++;
            }
        }
        return distinct.size();
    }

}


