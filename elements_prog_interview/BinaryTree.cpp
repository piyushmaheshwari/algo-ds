#include <vector>
#include <map>
#include <algorithm>

namespace BinaryTree {
    using namespace std;

    class Interval {
    public:
        int left;
        int right;
        int color;
        int height;

        Interval(int left, int right, int color, int height) {
            this->left = left;
            this->right = right;
            this->color = color;
            this->height = height;
        }
    };

    class EndPoint {
    public:
        bool isLeft = false;
        Interval *_i = nullptr;

        int value() {
            return (isLeft) ? (_i->left) : (_i->right);
        }

        EndPoint(bool isLeft, Interval *i) {
            this->isLeft = isLeft;
            this->_i = i;
        }

        Interval *getInterval() {
            return this->_i;
        }
    };


    //optimize this later
    class BST {
        map<int, Interval *> store;

    public:
        bool isEmpty() {
            return store.empty();
        }

        Interval *maxHeight() {
            int height = -1;
            Interval *retVal = nullptr;
            for (auto it = store.begin(); it != store.end(); it++) {
                if (it->second->height > height) {
                    height = it->second->height;
                    retVal = it->second;
                }
            }
            return retVal;
        }

        void remove(int val) {
            auto it = store.find(val);
            store.erase(it);
        }

        void add(Interval *interval) {
            store[interval->height] = interval;
        }
    };


    void printInterval(int left, int right, int color, int height) {
        cout << left << " " << right << " " << color << " " << height << endl;
    }

    bool compare(EndPoint first, EndPoint second) {
        return first.value() < second.value();
    }

    void test() {
        vector < Interval * > intervalList =
                {
                        new Interval(1, 6, -1, 1),
                        new Interval(2, 5, -2, 2),
                        new Interval(5, 9, -3, 0)
                };

        vector <EndPoint> pointList;
        for (auto i : intervalList) {
            pointList.push_back(EndPoint(true, i));
            pointList.push_back(EndPoint(false, i));
        }
        EndPoint *current = nullptr;
        sort(pointList.begin(), pointList.end(), compare);
        BST store;
        bool empty = true;
        int prev_x = -1;
        for (EndPoint point: pointList) {
            if (point.isLeft == false) { //right point
                if (point.getInterval() == current->getInterval()) { //current interval ended
                    printInterval(prev_x, point.value(), point.getInterval()->color, point.getInterval()->height);
                    store.remove(point.getInterval()->height);
                    current = new EndPoint(true, store.maxHeight());
                    prev_x = point.value();
                } else {
                    store.remove(point.getInterval()->height);
                }
            } else { //left point
                if (current != nullptr) {
                    if (current->getInterval()->height < point.getInterval()->height) {
                        printInterval(prev_x, point.value(), current->getInterval()->color,
                                      current->getInterval()->height);
                    }
                }
                current = &point;
                prev_x = current->value();
                store.add(point._i);
            }
        }
    }
};