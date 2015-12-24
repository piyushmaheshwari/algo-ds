package random;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {

    public boolean isPresent (String input, List<String> words){
        Trie dictionary = new Trie(false);
        for (String word: words){
            dictionary.insert(word);
        }

        Map<String, Boolean> dp = new HashMap<>();
        return isPresentInner(input, dictionary, dp);
    }


    //expects a suffix, dict, dp
    private boolean isPresentInner (String remaining, Trie dictionary, Map<String, Boolean> dp ){

        if (remaining.length() == 0){
            return true;
        }

        if (dp.containsKey(remaining))
            return dp.get(remaining);

        boolean ans = false;

        for (int i = 0; i < remaining.length(); i++){
            String prefix = remaining.substring(0, i + 1);
            String suffix = remaining.substring(i+1);
            if (dictionary.search(prefix) && isPresentInner(suffix, dictionary, dp)){
                ans = true;
                break;
            }
        }
        dp.put(remaining, ans);
        return ans;
    }
}
