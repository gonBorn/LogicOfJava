package list;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class OrderedList<Key extends Comparable<Key>> {
    private List<Key> orderedList;

    public OrderedList(Key[] keys) {
        List<Key> collect = Arrays.stream(keys).collect(Collectors.toList());
        Collections.sort(collect);
        this.orderedList = collect;
    }

    public void add(Key key) {
        int low = 0;
        int high = orderedList.size() - 1;
        int middle = 0;
        while (low <= high) {
            middle = (low + high)/2;
            int compare = key.compareTo(orderedList.get(middle));
            if (compare > 0) {
                low = middle + 1;
            } else if (compare < 0) {
                high = middle - 1;
            } else {
                break;
            }
        }
        orderedList.add(orderedList.get(high));
        for (int i = orderedList.size() - 1; i > middle; i--) {
            orderedList.set(i, orderedList.get(i-1));
        }
    }

    public Key get(int i) {
        return orderedList.get(i);
    }

    public <Key> Key[] toArray(Key[] a) {
        return orderedList.toArray(a);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Key key: orderedList) {
            str.append(key.toString() + " ") ;
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Integer[] ints = {1, 4, 3, 2, 5, 9, 7, 8};
        OrderedList<Integer> orderedList = new OrderedList<>(ints);
        System.out.println(orderedList.toString());

        orderedList.add(3);
        System.out.println(orderedList.toString());

        orderedList.add(1);
        System.out.println(orderedList.toString());

        orderedList.add(8);
        System.out.println(orderedList.toString());

        final PriorityQueue<Integer> integers = new PriorityQueue<>();
    }
}
