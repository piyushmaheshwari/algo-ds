import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder2 {

    static class Tuple {
        public String word;
        public List<String> history;

        public Tuple(String word) {
            this.word = word;
            this.history = new ArrayList<>();
        }

        public Tuple(String word, List<String> history) {
            this.word = word;
            this.history = history;
        }
    }

    public List<String> changeCharacter(
            String arg, Character c,
            Set<String> wordList) {

        List<String> result = new ArrayList<>();
        char[] argArr = arg.toCharArray();
        for (int i = 0; i < arg.length(); i++) {
            if (!c.equals(argArr[i])) {
                char t = argArr[i];
                argArr[i] = c;
                String str = String.valueOf(argArr);
                argArr[i] = t;
                if (wordList.contains(str))
                    result.add(str);
            }
        }
        return result;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        int ans = -1;

        char[] alphabet = new char[26];
        for (int i = 0; i < alphabet.length; i++)
            alphabet[i] = (char) ((int) ('a' + i));

        wordList.add(endWord);

        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(beginWord));
        Map<String, Integer> visited = new HashMap<>();

        while (!q.isEmpty()) {
            Tuple current = q.poll();
            String currentWord = current.word;
            List<String> history = current.history;
            visited.put(currentWord, history.size());
            if (ans == -1 && !endWord.equals(currentWord)) {
                for (Character c : alphabet) {
                    List<String> changed = changeCharacter(currentWord, c, wordList);
                    for (String str : changed) {
                        if (!visited.containsKey(str) ||
                                visited.get(str).compareTo(history.size()) > 0) { //found new candidate
                            List<String> newHistory = new ArrayList<>(history);
                            newHistory.add(currentWord);
                            Tuple t = new Tuple(str, newHistory);
                            q.add(t);
                        }
                    }
                }
            } else if (ans == -1 && endWord.equals(currentWord)) {
                history.add(currentWord);
                result.add(history);
                ans = history.size();
            } else if (endWord.equals(currentWord) && history.size() + 1 == ans) {
                history.add(currentWord);
                result.add(history);
            }
        }
        return result;
    }
}
