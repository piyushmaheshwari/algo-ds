package binarySearchTrees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AddingCredits {
    private Integer offset = 0;
    private Map<String, Integer> clientToCredits = new HashMap<>();
    private TreeMap<Integer, Set<String>> creditToClients = new TreeMap<>();

    public void insert(String client, Integer c) {
        remove(client);
        this.clientToCredits.put(client, c - this.offset);
        Set<String> clientSet = this.creditToClients.getOrDefault(c - this.offset, new HashSet<>());
        clientSet.add(client);
        this.creditToClients.put(c - this.offset, clientSet);
    }

    public boolean remove(String client) {
        Integer credit = this.clientToCredits.getOrDefault(client, null);
        if (credit != null) {
            Set<String> clientSet = this.creditToClients.remove(credit);
            clientSet.remove(client);
            if (clientSet.isEmpty() != true)
                this.creditToClients.put(credit, clientSet);
            this.clientToCredits.remove(client);
            return true;
        } else
            return false;
    }

    public Integer lookup(String client) {
        Integer val = this.clientToCredits.getOrDefault(client, null);
        return (val == null) ? -1 : (val + this.offset);
    }

    public void addAll(Integer C) {
        this.offset += C;
    }

    public String max() {
        Map.Entry<Integer, Set<String>> lastEntry = this.creditToClients.lastEntry();
        if (lastEntry != null)
            return lastEntry.getValue().iterator().next();
        else
            return null;
    }

    public void test() {
        assert (this.max() == null);
        String foo = "foo";
        String bar = "bar";
        String widget = "widget";
        String dothis = "dothis";
        String xyz = "xyz";
        String dd = "dd";
        assert (!this.remove(foo));
        this.insert(foo, 10);
        this.insert(foo, 1);
        this.insert(bar, 2);
        this.addAll(5);
        this.insert(widget, 3);
        this.addAll(5);
        this.insert(dothis, 4);
        assert (11 == this.lookup(foo));
        assert (12 == this.lookup(bar));
        assert (8 == this.lookup(widget));
        assert (4 == this.lookup(dothis));
        assert (this.remove(foo));
        assert (-1 == this.lookup(foo));
        assert (this.max().equals(bar));
        this.insert(xyz, 13);
        assert (this.max().equals(xyz));
        this.insert(dd, 15);
        assert (this.max().equals(dd));
    }
}
