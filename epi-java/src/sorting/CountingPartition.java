package sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountingPartition {
    class Person implements Comparable<Person> {
        public Integer key;
        public String name;

        public Person(Integer key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            return this.key.compareTo(o.key);
        }
    }

    private void swap(List<Person> input, int from, int to) {
        Person p1 = input.get(from);
        Person p2 = input.get(to);
        input.set(from, p2);
        input.set(to, p1);
        return;
    }

    List<Person> solve(List<Person> input) {
        Map<Integer, Integer> count = new HashMap<>();
        for (Person p : input) {
            Integer oldCount = count.getOrDefault(p.key, 0);
            count.put(p.key, oldCount + 1);
        }

        Map<Integer, Integer> offSet = new HashMap<>();
        int sum = 0;
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            offSet.put(e.getKey(), sum);
            sum += e.getValue();
        }

        while (offSet.size() > 0) {
            Map.Entry<Integer, Integer> e = offSet.entrySet().iterator().next();
            Integer key = e.getKey();
            Integer index = e.getValue();

            do {
                Integer toKey = input.get(index).key;
                Integer toOffset = offSet.get(toKey);
                int remCount = count.get(toKey);
                swap(input, index, toOffset);

                if (remCount == 1) {
                    offSet.remove(toKey);
                } else {
                    offSet.put(toKey, toOffset + 1);
                }
                count.put(toKey, remCount - 1);
            } while (input.get(index).key != key);
        }

        return input;
    }

    public void test() {
        List<Person> people
                = Arrays.asList(new Person(20, "foo"), new Person(10, "bar"),
                new Person(20, "widget"), new Person(20, "something"));

        List<Person> sorted = solve(people);
        List<Integer> keys = sorted.stream().map(p -> p.key).collect(Collectors.toList());
        List<String> names = sorted.stream().map(p -> p.name).collect(Collectors.toList());
        System.out.println(keys);
        System.out.println(names);
    }
}
