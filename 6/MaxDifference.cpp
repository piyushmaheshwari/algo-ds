#include <algorithm>
#include <assert.h>

#define INF 1<<30
#define NEG (-1 * INF)

namespace MaxDifference {
    using namespace std;

    int maxK(vector<int> price, int k) { //O(n*n*k)
        int n = price.size();
        vector <vector<int>> dp(k + 1, vector<int>(n, NEG));

        int min_tt = price[0];
        dp[0][0] = 0;
        for (int i = 0; i < n; i++){
            dp[0][i] = price[i] - min_tt;
            min_tt = min (min_tt, price[i]);
        }

        for (int i = 1; i < k; i++) {
            for (int j = n - 1; j > 2; j--) {
                int min_t = INF;
                for (int q = j - 1; q > 1; q--) {
                    min_t = min(min_t, price[q]);
                    dp[i][j] = max(dp[i][j], dp[i - 1][q - 1] + (price[j] - min_t));
                }
            }
        }

        int m = NEG;
        for (auto i: dp[k-1]) {
            m = max(m, i);
        }
        return m;
    }


    int maxAny(vector<int> price) {
       int sum = 0;
        for (int i = 1; i < price.size(); i++){
            if ((price[i] - price[i-1]) > 0)
                sum += price[i] - price[i-1];
        }
        return sum;
    }

    int max2(vector<int> price) { //O(n)
        int n = price.size();
        vector<int> dp(n, 0);
        int ans = 0;
        int min_t = price[0];
        int max_t = -10e8;
        for (int i = 1; i < n - 1; i++) {
            max_t = max(max_t, price[i] - min_t);
            min_t = min(min_t, price[i]);
            dp[i + 1] = max_t - price[i + 1];
        }

        max_t = -10e8;
        for (int i = n - 1; i >= 2; i--) {
            max_t = max(max_t, price[i]);
            ans = max(ans, dp[i - 1] + max_t);
        }
        return ans;
    }


    void test() {
        vector<int> input = {3, 7, 5, 7, 4, 10};
        cout << maxK(input, 2) << endl;
        cout << maxAny(input) << endl;
    }
}
