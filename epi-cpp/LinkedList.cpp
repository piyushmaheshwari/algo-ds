namespace LinkedList {

    using namespace std;

    class node {
    public:
        node *next;
        int val;
    };

    using Node = node *;

    Node makeNode(int val) {
        Node t = new node();
        t->val = val;
        t->next = nullptr;
        return t;
    }

    Node constructLL(vector<int> arr) {
        Node head = nullptr;
        Node current = head;
        for (int i = 0; i < arr.size(); i++) {
            if (i == 0) {
                head = makeNode(arr[i]);
                current = head;
            } else {
                current->next = makeNode(arr[i]);
                current = current->next;
            }
        }
        return head;
    }

    void printList(Node head) {
        while (head != nullptr) {
            cout << head->val;
            head = head->next;
        }
        cout << endl;
    }


    Node reverseLL(Node head) {
        if (head == nullptr)
            return nullptr;

        Node f = head;
        Node s = head->next;
        Node l = s;
        while (s != nullptr) {
            if (l != nullptr)
                l = l->next;
            s->next = f;
            f = s;
            s = l;
        }
        head->next = nullptr;
        return f;
    }

    Node recurReverseLL(Node head) {
        if (head == nullptr || head->next == nullptr)
            return head;

        Node temp = head->next;
        Node remain = recurReverseLL(temp);
        temp->next = head;
        head->next = nullptr;
        return remain;
    }

    int findLengthLL(Node head) {
        int i = 0;
        while (head != nullptr) {
            ++i;
            head = head->next;
        }
        return i;
    }

    Node zip(Node head) {
        int len = findLengthLL(head);
        Node split = head;
        Node firstHalf = head;
        int secondVal = len / 2 + (len % 2);
        int i = 1;
        while (i < secondVal) {
            split = split->next;
            ++i;
        }
        Node secondHalf = reverseLL(split->next);
        split->next = nullptr;

        Node x = nullptr;
        Node y = nullptr;
        while (secondHalf != nullptr) {
            x = firstHalf->next;
            y = secondHalf->next;
            firstHalf->next = secondHalf;
            secondHalf->next = x;
            firstHalf = x;
            secondHalf = y;
        }
        return head;
    }


    int test() {
        vector<int> arr = {1, 2, 3, 4, 5};
        Node head = constructLL(arr);
        printList(head);
        printList(zip(head));
        return 0;
    }
}
