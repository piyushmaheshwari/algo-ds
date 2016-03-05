import java.util.*;

public class ReconstructItenary {
    public List<String> findItinerary(String[][] tickets) {
        TreeMap<String, PriorityQueue<String>> ordering = new TreeMap<>();
        for (int i = 0; i < tickets.length; i++) {
            String from = tickets [i] [0];
            String to = tickets [i] [1];
            PriorityQueue<String> s = ordering.getOrDefault(from, new PriorityQueue<>());
            s.add(to);
            ordering.put(from, s);
        }

        Stack<String> intermediate = new Stack<>();
        List<String> ans = new ArrayList<>();
        String begin = "JFK";
        while (!ordering.isEmpty()){
            PriorityQueue<String> s = ordering.getOrDefault(begin, null);
            if (s == null){
                ans.add(begin);
                begin = intermediate.pop();
                continue;
            } else {
                intermediate.push(begin);
                String to = s.poll();
                if (s.isEmpty()){
                    ordering.remove(begin);
                }
                begin = to;
            }
        }
        intermediate.push(begin);
        while (!intermediate.empty())
            ans.add(intermediate.pop());
        Collections.reverse(ans);
        return ans;
    }
}


