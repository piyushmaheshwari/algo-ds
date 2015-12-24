#include <vector>
#include <algorithm>

namespace RenderCalender {
    using namespace std;
    class Interval {
    public:
        int start;
        int end;
        Interval (int start, int end){
            this-> start = start;
            this->end = end;
        }
    };


    class EndPoint {
    public:
        int time;
        bool isStart;
        EndPoint (int time, int isStart){
            this->time = time;
            this->isStart = isStart;
        }
    };

    int solve (vector<Interval> intervalList) {
        vector<EndPoint> epList;
        for (auto interval: intervalList){
            epList.push_back(EndPoint(interval.start, true));
            epList.push_back(EndPoint(interval.end, false));
        }

        sort (epList.begin(), epList.end(), [](EndPoint a, EndPoint b){
            return (a.time < b.time);
        });

        int counter = 0;
        int ans = 0;
        for (auto ep: epList) {
            if (ep.isStart)
                counter++;
            else
                counter--;
            ans = max(ans, counter);
        }

        return ans;
    }

    void test () {
        vector<Interval> input = {
                Interval(1,5), Interval(6,10), Interval(11,13), Interval(14,15), Interval(2,7),
                Interval(8,9), Interval(12,15), Interval(4,5), Interval(9,17)
        };
        cout << solve (input);
        return;
    }
};
