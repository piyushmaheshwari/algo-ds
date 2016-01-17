package searching;

public class FirstOccurGreaterK {
    private int bsHelper(int[] input, int k, int start, int end) {
        if (start > end)
            return -1;
        else {
            int mid = start + (end - start) / 2;
            if (input[mid] > k && ((mid - 1 < 0) || input[mid - 1] <= k))
                return mid;
            else if (input[mid] > k)
                return bsHelper(input, k, start, mid - 1);
            else
                return bsHelper(input, k, mid + 1, end);
        }
    }

    public int firstOccurGreaterK(int[] input, int k) {
        return bsHelper(input, k, 0, input.length - 1);
    }


    public int firstOccurGreaterKIterative(int[] input, int k) {
        int low = 0, high = input.length - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (input[mid] == k) {
                ans = mid;
                low = mid + 1;
            } else if (input[mid] < k)
                low = mid + 1;
            else
                high = mid - 1;
        }
        if (ans == -1 && k < input[0])
            return 0;
        else if (ans == -1 || ans == input.length - 1)
            return -1;
        else
            return ans + 1;
    }

    public void test() {
        int[] input = {-14, -10, 2, 108, 108, 243, 285, 285, 285, 401};
        System.out.println(firstOccurGreaterK(input, 285));
        System.out.println(firstOccurGreaterKIterative(input, 285));
    }
}
