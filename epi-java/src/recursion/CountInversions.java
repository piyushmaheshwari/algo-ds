package recursion;


public class CountInversions {

    private int solve(int[] A, final int start, final int end) {

        if (start >= end)
            return 0;

        int mid = start + (end - start) / 2;
        int leftInversions = solve(A, start, mid);
        int rightInversions = solve(A, mid + 1, end);
        int totalInversions = leftInversions + rightInversions;
        int left = start;
        int right = mid + 1;
        int[] temp = new int[end - start + 1];
        int t = 0;

        while (left <= mid && right <= end) {
            if (A[left] >= A[right]) {
                totalInversions += (mid - left + 1);
                temp[t] = A[right];
                right += 1;
            } else {
                temp[t] = A[left];
                left += 1;
            }
            t += 1;
        }
        while (left <= mid) {
            temp[t] = A[left];
            t += 1;
            left += 1;
        }
        while (right <= end) {
            temp[t] = A[right];
            t += 1;
            right += 1;
        }

        for (int i = 0; i < t; i++){
            A[i + start] = temp [i];
        }
        return totalInversions;
    }

    public int solve(int[] A) {
        return solve (A, 0, A.length - 1);
    }

    public void test() {
        int [] input = {3,2,1,0};
        System.out.println(solve(input));
        int [] input1 = {1,2,3,4};
        System.out.println(solve(input));
    }
}
