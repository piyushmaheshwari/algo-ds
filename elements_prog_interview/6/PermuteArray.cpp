#include <vector>

namespace PermuteArray {
    using namespace std;
    
    void swap (int& a, int &b){
        int t = a;
        a = b;
        b = t;
    }
    void applyPermute(vector<int> permute, vector<int>& content) {
        int n = permute.size();
        int counter = 0;
        while (n){
            if (counter == permute[counter]){
                counter ++;
            } else {
                int other = permute[counter];
                swap (content[other], content[counter]);
                swap (permute[other], permute[counter]);
            }
            n--;
        }
    }

    void test() {
        vector<int> init = {4,5,0,1,2,3};
        vector<int> content = {4,5,0,1,2,3};
        applyPermute(init, content);
        for (auto i: content){
            cout << i << " ";
        }
    }
}