package random;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    public boolean isLeaf;
    public Map<Character, Trie> children;

    public Trie(boolean isLeaf) {
        this.isLeaf = isLeaf;
        children = new HashMap<>();
    }

    public boolean insert(String input) {
        if (input.length() == 0) {
            if (isLeaf)
                return false;
            else {
                return (isLeaf = true);
            }
        } else {
            char head = input.charAt(0);
            String rest = input.substring(1);
            if (!this.children.containsKey(head)) {
                Trie child = new Trie(false);
                this.children.put(head, child);
            }
            return this.children.get(head).insert(rest);
        }
    }

    public boolean search(String input) {
        if (input.length() == 0)
            return isLeaf;
        else {
            char head = input.charAt(0);
            if (!this.children.containsKey(head)) {
                return false;
            } else {
                return this.children.get(head).search(input.substring(1));
            }
        }
    }

    public boolean delete (String input){
        if (!search(input))
            return false;
        else {
            deleteInner (input);
            return true;
        }
    }

    private boolean deleteInner (String input){
        if (input.length() == 0){
            assert (isLeaf == true);
            isLeaf = false;
            if (this.children.size() == 0)
                return true;
            else
                return false;
        } else {
            char head  = input.charAt(0);
            String rest = input.substring(1);
            boolean del = this.children.get(head).deleteInner(rest);
            if (del){
                this.children.remove(head);
                if (this.children.size() == 0 && !isLeaf)
                    return true;
                else
                    return false;
            } else {
                return false;
            }
        }
    }
}
