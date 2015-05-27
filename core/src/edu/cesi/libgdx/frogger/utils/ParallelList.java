package edu.cesi.libgdx.frogger.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ParallelList<T> implements Iterable<List<T>> {

    private final List<List<T>> lists;

    public ParallelList(List<T>... lists) {
        this.lists = new ArrayList<List<T>>(lists.length);
        this.lists.addAll(Arrays.asList(lists));
    }

    public Iterator<List<T>> iterator() {
        return new Iterator<List<T>>() {
            private int loc = 0;

            public boolean hasNext() {
                boolean hasNext = false;
                for (List<T> list : lists) {
                    hasNext |= (loc < list.size());
                }
                return hasNext;
            }

            public List<T> next() {
                List<T> vals = new ArrayList<T>(lists.size());
                for (int i=0; i<lists.size(); i++) {
                    vals.add(loc < lists.get(i).size() ? lists.get(i).get(loc) : null);
                }
                loc++;
                return vals;
            }

            public void remove() {
                for (List<T> list : lists) {
                    if (loc < list.size()) {
                        list.remove(loc);
                    }
                }
            }
        };
    }
}
