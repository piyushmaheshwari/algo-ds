package greedy;

import util.TupleInt;

public class SearchPairAbsSorted {

    TupleInt solve(int[] input, int k) {
        TupleInt ans = TupleInt.make(-1, -1);
        int n = input.length;
        int left = n - 1;
        int right = n - 1;
        int leftInc = -1;
        int rightInc = -1;

        while (right >= 0 && input[right] < 0)
            right += rightInc;
        if (right < 0) {
            right = 0;
            rightInc = 1;
        }

        while (left >= 0 && input[left] >= 0) {
            left += leftInc;
        }
        if (left < 0) {
            left = 0;
            leftInc = 1;
        }

        while (input[left] <= input[right]) {
            if (input[left] + input[right] == k) {
                ans = TupleInt.make(left, right);
                break;
            } else if (input[left] + input[right] < k) { //move left
                if (leftInc < 0) {
                    left = left + leftInc;
                    while (left >= 0 && input[left] >= 0)
                        left += leftInc;
                    if (left < 0) {
                        left = 1;
                        leftInc = 1;
                        while (input[left] < 0)
                            left += leftInc;
                    }
                } else {
                    left += leftInc;
                    while (input[left] < 0)
                        left += leftInc;
                }

            } else { //move right
                if (rightInc < 0) { //move right to left
                    right = right + rightInc;
                    while (right >= 0 && input[right] < 0)
                        right += rightInc;
                    if (right < 0) {
                        right = 1;
                        rightInc = 1;
                        while (input[right] >= 0)
                            right = right + rightInc;
                    }
                } else {
                    right = right + rightInc;
                    while (input[right] >= 0)
                        right = right + rightInc;
                }

            }
        }
        return ans;
    }


    public void test() {
        int[] input = {-49, 75, 103, -147, 164, -197, -238, 314, 348, -422};
        System.out.println(solve(input, -619));
        int [] input2 = {0, 0, -1, 2, -3, -3};
        System.out.println(solve(input2, 2));
    }
}
