#include <iostream>
#include <vector>

namespace DutchFlag {
    using namespace std;

    vector<int> DutchFlagPartition(vector<int> array, int index) {
        int val = array[index];
        int size = array.size();
        int begin = 0;
        int middle = 0;
        int end = size;
        // 0 to begin - 1 : less
        // begin to middle -1 : equal
        // middle to end - 1 : unprocessed
        // end to n : greater

        while (middle < end) {
            if (array[middle] < val) {
                int t = array[begin];
                array[begin] = array[middle];
                array[middle] = t;
                begin++;
                middle++;
            } else if (array[middle] > val) {
                int t = array[end - 1];
                array[end - 1] = array[middle];
                array[middle] = t;
                end--;
            } else {
                middle++;
            }
        }
        return array;
    }

    void printArray(vector<int> array) {
        for (auto i: array)
            cout << i;
        cout << endl;
    }

    int test() {
        vector<int> array = {2, 1};
        printArray(array);
        vector<int> result = DutchFlagPartition(array, 1);
        printArray(result);
        return 0;
    }
}