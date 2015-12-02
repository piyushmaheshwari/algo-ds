package random;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;


class Word {
    public String word;
    public int pos;

    Word(String word, int pos) {
        this.word = word;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", pos=" + pos +
                '}';
    }
}

public class WordProximity {

    String readFile(String fileName) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("src/" + fileName));
        String line = null;
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

    public static void main(String[] args) throws Exception {

        WordProximity prox = new WordProximity();
        prox.proximityFirst("this", "is");
        prox.proximitySecond(Arrays.asList("this", "everyone's", "that"));

    }
}
