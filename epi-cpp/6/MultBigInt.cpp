#include <string>
#include <algorithm>

namespace BigInt {
    using namespace std;

    class BigInt {
        BigInt multiplySingle(int single) {
            string ans = "";
            int carry = 0;
            for (auto digit: this->number) {
                int cal = ((digit - '0') * single) + carry;
                char t = '0' + (int) (cal % 10);
                ans = ans + t;
                carry = cal / 10;
            }
            if (carry) {
                char t = (carry + '0');
                ans = ans + t;
            }

            reverse(ans.begin(), ans.end());
            return BigInt(ans, this->isPos);
        }

    public:

        bool isPos = true;
        string number = "";

        BigInt(string number, bool isPos) {
            this->isPos = isPos;
            reverse(number.begin(), number.end());
            this->number = number;
        }

        string print() {
            string t = number;
            reverse(t.begin(), t.end());
            string sign = "";
            if (!this->isPos) {
                sign = '-';
            }
            return sign + t;
        }

        BigInt add (BigInt that){
            string first = (this->number.size() >= that.number.size())? this->number : that.number;
            string second = (this->number.size() < that.number.size())? this->number : that.number;
            string ans = "";
            int counter = 0;
            int carry = 0;
            while (counter < second.size()){
                int add = (first[counter] - '0') + (second[counter] - '0') + carry;
                char t = '0' + add % 10;
                carry = add / 10;
                ans = ans + t;
                counter++;
            }

            while (counter < first.size()){
                int add = (first[counter] - '0') + carry;
                char t = '0' + add % 10;
                carry = add / 10;
                ans = ans + t;
                counter++;
            }

            if (carry){
                char t = carry + '0';
                ans = ans + t;
            }
            reverse(ans.begin(), ans.end());
            return BigInt (ans, true);
        }

        BigInt multiply(BigInt that) {
            if (that.number.size() == 1) {
                return multiplySingle(that.number[0] - '0');
            }

            char first = that.number[0];
            BigInt firstNum = this->multiplySingle(first - '0');

            string remaining = that.number.substr(1);
            reverse(remaining.begin(), remaining.end());
            BigInt remainMult = multiply(BigInt(remaining, true));

            string tt = "0" + remainMult.number;
            BigInt remainMult10 = BigInt (string(tt.rbegin(), tt.rend()), true);
            BigInt result =  remainMult10.add(firstNum);
            return BigInt(result.number, this->isPos && that.isPos);
        }

    };


    void test() {
        BigInt num("10000", true);
        BigInt num2 ("10000", true);
        cout << num2.multiply(num).print();
    };


}
