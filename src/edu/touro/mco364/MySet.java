package edu.touro.mco364;

import javafx.beans.binding.ObjectExpression;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MySet<T> implements Serializable, Set<T> {
    private T[] backingStore = (T[]) new Object[10];
    private int insertionPoint = 0;

    @Override
    public int size() {
        return insertionPoint;
    }

    @Override
    public boolean isEmpty() {
        return insertionPoint == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(T t: backingStore) {
            if (o.equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MySetIterator();
    }

    private class MySetIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            return backingStore[index++];
        }
    }

    @Override
    public Object[] toArray() {
        T[] returnArray = (T[]) new Object[size()];
        System.arraycopy(backingStore, 0, returnArray, 0, size());
        return returnArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        if(t1s.length < size()) {   //Doesn't fit
            t1s = (T1[]) new Object[size()];
        }
        else if(t1s.length > size()) {
            t1s[size()] = null;
        }
        System.arraycopy(backingStore, 0, t1s, 0, size());
        return t1s;
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
        for (int i = 0; i < size(); i++) {
            if(backingStore[i].equals(o)) {
                System.arraycopy(backingStore, i + 1, backingStore, i, size() - i);
                insertionPoint--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for(Object element : collection) {
            if(!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean changed = false;

        for(T element : collection) {
            if(add(element)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        T[] newBS = (T[]) new Object[insertionPoint];
        int tempIP = 0;

        for (int i = 0; i < insertionPoint; i++) {
            if(collection.contains(backingStore[i])) {
                newBS[tempIP++] = backingStore[i];
            }
        }
        backingStore = newBS;
        insertionPoint = tempIP;
        return insertionPoint == backingStore.length - 1;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean changed = false;

        for(Object element : collection) {
            if(remove(element)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        backingStore = (T[]) new Object[10];
        insertionPoint = 0;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(backingStore);
        out.writeInt(insertionPoint);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        backingStore = (T[]) in.readObject();
        insertionPoint = in.readInt();
    }

    private void ensureCapacity() {
        if(insertionPoint == backingStore.length - 1) {
            T[] tempArray = (T[]) new Object[backingStore.length * 2];
            System.arraycopy(backingStore, 0, tempArray, 0, backingStore.length);
            backingStore = tempArray;
        }
    }
}
