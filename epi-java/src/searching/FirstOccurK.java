package searching;

public class FirstOccurK {
    private int bsHelper (int [] input, int k, int start, int end){
        if (start > end)
            return -1;
        else {
            int mid = start + (end - start) / 2;
            if (input [mid] == k){
                while ((mid - 1 >=0) && input[mid -1] == input [mid])
                    mid--;
                return mid;
            } else if (input [mid] > k)
                return bsHelper(input, k, start, mid - 1);
            else
                return bsHelper(input, k, mid + 1, end);
        }
    }
    public  int firstOccurK (int [] input, int k) {
        return bsHelper(input, k, 0, input.length - 1);
    }

    public void test (){
        int [] input = {-14, -10, 2 , 108, 108, 243, 285, 285, 285, 401};
        System.out.println(firstOccurK(input, 401));
    }
}
