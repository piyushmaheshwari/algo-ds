#include <iostream>
#include <vector>
#include <string>
#include "DutchFlagPartition.cpp"
#include "LinkedList.cpp"
#include "BinaryTree.cpp"
#include "14/RenderCalender.cpp"
#include "6/MultBigInt.cpp"
#include <algorithm>
#include "6/MaxDifference.cpp"


using namespace std;


void swap(int &a, int &b) {
    int t = a;
    a = b;
    b = t;
}

void merge_sort(int *array, const int size, const int low, const int high) {

    if (low >= high) // size 0,1
        return;

    if ((low + 1 == high) && (array[low] > array[high])) {  //size 2
        swap(array[low], array[high]);
        return;
    }

    int mid = (low + high) / 2;
    vector<int> temp;
    int leftPtr = low;
    int rightPtr = mid + 1;

    merge_sort(array, size, low, mid);
    merge_sort(array, size, mid + 1, high);


    while (leftPtr <= mid && rightPtr <= high)
        if (array[leftPtr] <= array[rightPtr])
            temp.push_back(array[leftPtr++]);
        else
            temp.push_back(array[rightPtr++]);

    while (leftPtr <= mid)
        temp.push_back(array[leftPtr++]);

    while (rightPtr <= high)
        temp.push_back(array[rightPtr++]);

    for (int i = 0; i < temp.size(); i++)
        array[low + i] = temp[i];
}


void printArray(int *a, int size) {
    for (int i = 0; i < size; i++) {
        cout << a[i] << " ";
    }
    cout << endl;
}


class Interval {
public:
    int left;
    int right;

    Interval(int left, int right) {
        this->left = left;
        this->right = right;
    }

    bool intersect(Interval other) {
        if ((other.right < this->left) || (other.left > this->right))
            return false;
        else
            return true;
    }
    Interval merge (Interval other){
        return Interval (min(this->left, other.left),
                         max(this->right, other.right));
    }
};


void printIntervals (vector<Interval> list) {
    for (auto interval: list){
        cout<< interval.left << " " << interval.right<< endl;
    }
    cout << endl;
}

vector<Interval> mergeIntervals (const vector<Interval> intervals) {
    if (intervals.size() == 0)
        return vector<Interval>();

    auto current = intervals[0];
    auto retVal = vector<Interval> ();
    auto remaining = vector<Interval>(intervals.begin()+1, intervals.end());
    for (auto interval : remaining){
        if (current.intersect(interval)){
            current = current.merge(interval);
        }else {
            retVal.push_back(current);
            current = interval;
        }
    }
    retVal.push_back(current);
    return retVal;
}




int main() {
//    //RenderCalender::test();
//    vector<Interval> list = {Interval(2,3), Interval(3,5), Interval (3,4), Interval(1,9)};
//    printIntervals(list);
//    auto merged = mergeIntervals(list);
//    printIntervals(merged);
      MaxDifference::test();

    return 0;
}
