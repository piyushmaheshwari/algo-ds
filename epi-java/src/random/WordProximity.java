package random;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;


public class WordProximity {

    class Word {
        public String word;
        public int pos;

        Word(String word, int pos) {
            this.word = word;
            this.pos = pos;
        }
    }

    String readFile(String fileName) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("src/" + fileName));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = f.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }

    List<Word> processDocument(String document) {
        int counter = 0;
        char c;
        boolean isWord = false;
        String word = "";
        List<Word> wordList = new ArrayList<>();
        for (int i = 0; i < document.length(); i++) {
            c = document.charAt(i);
            if (c != ' ' && c != '\n') {
                if (!isWord) {
                    isWord = true;
                }
                word += c;
            } else if (isWord) { // end of word
                isWord = false;
                wordList.add(new Word(word, counter - word.length()));
                word = "";
            }
            counter++;
        }
        if (word.length() > 0) {
            wordList.add(new Word(word, counter - word.length()));
        }
        return wordList;
    }

    void proximityFirst(String w1, String w2) throws Exception {
        proximitySecond(Arrays.asList(w1, w2));
    }

    void proximitySecond(List<String> inputWords) throws Exception {
        String content = readFile("data.txt");
        Set<String> inputWordsSet = new HashSet<>(inputWords);
        List<Word> wordList = processDocument(content);
        List<Word> changed = wordList.stream()
                .filter(w -> (inputWordsSet.contains(w.word)))
                .sorted((Word a, Word b) -> (a.pos - b.pos))
                .collect(Collectors.toList());

        int min = Integer.MAX_VALUE;
        Word prev = null;
        Word t1 = null;
        Word t2 = null;
        for (Word w : changed) {
            if (prev == null) {
                prev = w;
            } else if (!prev.word.equals(w.word) && (min > (w.pos - prev.pos))) {
                min = w.pos - prev.pos;
                t2 = w;
                t1 = prev;
            }
        }
        if (min != Integer.MAX_VALUE) {
            System.out.println("Answer : " + min + " " +
                    t1.word + "(" + t1.pos + ") " + t2.word + " (" + t2.pos + ")\n");
        }
    }

    void removeWord(Map<String, Integer> bag, String key) {
        int count = bag.get(key);
        if (count == 1) {
            bag.remove(key);
        } else {
            bag.put(key, count - 1);
        }
    }

    void proximityK(List<String> inputWords, int k) throws Exception {
        String content = readFile("data.txt");
        List<Word> documentWords = processDocument(content);
        Set<String> inputWordSet = new HashSet<>(inputWords);
        Map<String, Integer> bag = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int lans = 0, rans = 0, left = 0, right = 0;
        while (left < documentWords.size()) {
            right = left + 1;
            Word w = documentWords.get(left);
            if (inputWordSet.contains(w.word)) {
                bag.put(w.word, 1);
                break;
            }
            left += 1;
        }
        while (right < documentWords.size()) {
            Word w = documentWords.get(right);
            if (inputWordSet.contains(w.word)) {
                bag.put(w.word, bag.getOrDefault(w.word, 0) + 1);
                while (left < right) {
                    Word leftWord = documentWords.get(left);
                    if (bag.containsKey(leftWord.word)) {
                        int count = bag.get(leftWord.word);
                        // there are 2 case where we want to remove the word from left side of window
                        // First when the word is being repeated, second when the bag is greater than k
                        if (count > 1 || bag.size() > k) {
                            removeWord(bag, leftWord.word);
                        } else {
                            break;
                        }
                    }
                    left += 1;
                }
                if (bag.size() == k) {
                    Word rightWord = documentWords.get(right);
                    Word leftWord = documentWords.get(left);
                    if (min > (rightWord.pos + rightWord.word.length() - leftWord.pos)) {//new min
                        rans = rightWord.word.length() + rightWord.pos;
                        lans = leftWord.pos;
                        min = rans - lans;
                    }
                }
            }
            right += 1;
        }
        if (min != Integer.MAX_VALUE) {
            System.out.println("Answer : " + min + " " + content.substring(lans, rans + 1));
        }
    }
}
