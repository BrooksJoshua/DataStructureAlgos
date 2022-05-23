import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-05-23 17:30
 */
public class Leet705 {
    public static void main(String[] args) {
        MyHashSet obj = new MyHashSet();
        obj.add(3);
        obj.remove(5);
        boolean param_3 = obj.contains(3);
    }
}

class MyHashSet {
    private static final int BASE = 769;
    private LinkedList[] data;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return;
            }
        }
        data[h].offerLast(key);
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }
}

class MyHashSet2 {
    int[] bs = new int[40000];
    public void add(int key) {
        int bucketIdx = key / 32;
        int bitIdx = key % 32;
        setVal(bucketIdx, bitIdx, true);
    }

    public void remove(int key) {
        int bucketIdx = key / 32;
        int bitIdx = key % 32;
        setVal(bucketIdx, bitIdx, false);
    }

    public boolean contains(int key) {
        int bucketIdx = key / 32;
        int bitIdx = key % 32;
        return getVal(bucketIdx, bitIdx);
    }

    void setVal(int bucket, int loc, boolean val) {
        if (val) {
            int u = bs[bucket] | (1 << loc);
            bs[bucket] = u;
        } else {
            int u = bs[bucket] & ~(1 << loc);
            bs[bucket] = u;
        }
    }

    boolean getVal(int bucket, int loc) {
        int u = (bs[bucket] >> loc) & 1;
        return u == 1;
    }
}