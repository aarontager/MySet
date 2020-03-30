package edu.touro.mco364;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MySet<T> implements Serializable, Set<T> {
    T[] backingStore = (T[]) new Object[10];
    int insertionPoint = 0;

    @Override
    public int size() {
        return insertionPoint + 1;
    }

    @Override
    public boolean isEmpty() {
        return insertionPoint == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < insertionPoint; i++) {
            if (o.equals(backingStore[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if(contains(t))
            return false;

        ensureCapacity();
        backingStore[insertionPoint++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        backingStore = (T[]) new Object[10];
        insertionPoint = 0;
    }

    private void ensureCapacity() {
        if(insertionPoint == backingStore.length - 1) {
            T[] tempArray = (T[]) new Object[backingStore.length * 2];
            System.arraycopy(backingStore, 0, tempArray, 0, backingStore.length);
            backingStore = tempArray;
        }
    }
}
