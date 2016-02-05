package sorting;

public class NonContructibleChange {
    int solve(int[] input) {
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            if (sum + 1 >= input[i]) {
                sum += input[i];
            } else {
                break;
            }
        }
        return sum + 1;
    }

    public void test() {
        int[] input = {1, 1, 4, 10};
        assert (solve(input) == 3);
        int [] input1 = {1,2,3,4};
        assert solve(input1) == 11;
        int [] input2 = {2,3,4};
        assert  solve(input2) == 1;
    }
}
