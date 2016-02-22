package stackQueue;

import util.Tuple2;

import java.util.*;

public class MinMultiplications {
    static class State {
        public Integer exp;
        List<Integer> prevExp;

        public State(Integer exp, List<Integer> prevExp) {
            this.exp = exp;
            this.prevExp = prevExp;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (exp != null ? !exp.equals(state.exp) : state.exp != null) return false;
            return prevExp != null ? prevExp.equals(state.prevExp) : state.prevExp == null;

        }

        @Override
        public int hashCode() {
            int result = exp != null ? exp.hashCode() : 0;
            result = 31 * result + (prevExp != null ? prevExp.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "State{" +
                    "exp=" + exp +
                    ", prevExp=" + prevExp +
                    '}';
        }
    }

    int solve(int exponent) {
        if (exponent == 1)
            return 0;
        else if (exponent == 2)
            return 1;
        else {
            int ans = -1;
            int maxSteps = Integer.MAX_VALUE;
            Set<State> cache = new HashSet<>();
            cache.add(new State(1, new ArrayList<Integer>()));
            State init = new State(2, Arrays.asList(1));
            Queue<Tuple2<State, Integer>> q = new LinkedList<>();
            q.add(Tuple2.make(init, 1));
            while (!q.isEmpty()) {
                Tuple2<State, Integer> tuple = q.poll();
                State s = tuple.first;
                Integer steps = tuple.second;
                if (s.exp == exponent) {
                    ans = steps;
                    break;
                }
                cache.add(s);

                for (Integer prev : s.prevExp) {
                    List<Integer> newList = new ArrayList<>(s.prevExp);
                    newList.add(s.exp);
                    Integer newExp = s.exp + prev;
                    State newState = new State(newExp, newList);
                    if (!cache.contains(newState) && (steps + 1 < maxSteps))
                        q.add(Tuple2.make(newState, steps + 1));
                }

                List<Integer> squareList = new ArrayList<>(s.prevExp);
                squareList.add(s.exp);
                State square = new State(s.exp * 2, squareList);
                if (!cache.contains(square) && (steps + 1 < maxSteps))
                    q.add(Tuple2.make(square, steps + 1));
            }
            return ans;
        }
    }

    public void test() {
        System.out.println(solve(30));
    }
}
