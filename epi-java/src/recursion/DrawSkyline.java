package recursion;

import java.util.*;

public class DrawSkyline {

    class Skyline implements Comparable<Skyline> {
        public int left;
        public int right;
        public int height;

        public Skyline(int left, int right, int height) {
            this.left = left;
            this.right = right;
            this.height = height;
        }

        public Skyline(Skyline that) {
            this.left = that.left;
            this.right = that.right;
            this.height = that.height;
        }

        @Override
        public int compareTo(Skyline o) {
            return this.right - o.right;
        }

        @Override
        public String toString() {
            return "Skyline{" +
                    "left=" + left +
                    ", right=" + right +
                    ", height=" + height +
                    '}';
        }
    }

    class Endpoint implements Comparable<Endpoint> {
        public int val;
        public boolean isLeft;
        public Skyline building;

        public Endpoint(int val, boolean isLeft, Skyline building) {
            this.val = val;
            this.isLeft = isLeft;
            this.building = building;
        }

        @Override
        public int compareTo(Endpoint o) {
            if (this.val == o.val) {
                if (this.isLeft)
                    return -1;
                else
                    return 1;
            } else
                return this.val - o.val;
        }

        @Override
        public String toString() {
            return "Endpoint{" +
                    "val=" + val +
                    ", isLeft=" + isLeft +
                    ", building=" + building +
                    '}';
        }
    }

    void putBST(TreeMap<Integer, TreeSet<Skyline>> bst, int key, Skyline value) {
        TreeSet<Skyline> t = bst.getOrDefault(key, new TreeSet<>());
        t.add(value);
        bst.put(key, t);
    }

    void removeBST(TreeMap<Integer, TreeSet<Skyline>> bst, int key, Skyline value) {
        TreeSet<Skyline> t = bst.get(key);
        t.remove(value);
        if (t.isEmpty())
            bst.remove(key);
    }

    Skyline getNext(TreeMap<Integer, TreeSet<Skyline>> bst) {
        Map.Entry<Integer, TreeSet<Skyline>> n = bst.lastEntry();
        if (n == null)
            return null;
        else {
            return n.getValue().first();
        }
    }

    List<Skyline> solve(List<Skyline> buildings) {
        List<Endpoint> endPoints = new ArrayList<>();
        for (Skyline building : buildings) {
            endPoints.add(new Endpoint(building.left, true, building));
            endPoints.add(new Endpoint(building.right, false, building));
        }

        Collections.sort(endPoints);
        TreeMap<Integer, TreeSet<Skyline>> bst = new TreeMap<>();
        int leftX = endPoints.get(0).val;
        int rightX = endPoints.get(0).building.right;
        int currentHeight = endPoints.get(0).building.height;
        putBST(bst, currentHeight, endPoints.get(0).building);
        List<Skyline> ans = new ArrayList<>();

        for (int i = 1; i < endPoints.size(); i++) {
            Endpoint e = endPoints.get(i);
            if (e.isLeft) {
                Skyline next = getNext(bst);
                if (next == null) {
                    leftX = e.val;
                    rightX = e.building.right;
                    currentHeight = e.building.height;
                } else if (e.building.height == currentHeight) {
                    if (e.building.right > rightX)
                        rightX = e.building.right;
                } else if (e.building.height > currentHeight) {
                    ans.add(new Skyline(leftX, e.val, currentHeight));
                    leftX = e.val;
                    rightX = e.building.right;
                    currentHeight = e.building.height;
                }
                putBST(bst, currentHeight, e.building);
            } else {
                removeBST(bst, e.building.height, e.building);
                Skyline next = getNext(bst);
                if (next != null) {
                    if (next.height == currentHeight) {
                        rightX = next.right;
                    } else {
                        ans.add(new Skyline(leftX, e.val, currentHeight));
                        leftX = e.val;
                        rightX = next.right;
                        currentHeight = next.height;
                    }
                } else {
                    ans.add(new Skyline(leftX, rightX, currentHeight));
                }
            }
        }
        return ans;
    }

    public void test() {
        List<Skyline> buildings = Arrays.asList(
                new Skyline(1, 3, 1), new Skyline(3, 6, 1), new Skyline(6, 7, 1), new Skyline(7, 8, 1));
        System.out.println(solve(buildings));
    }
}
