package edu.touro.mco364;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MySetTest {
    MySet<String> mySet = new MySet<String>();
    Collection<String> coll = new ArrayList<String>();

    @Test
    void size() {   //also tests add()
        assertEquals(0, mySet.size());
        mySet.add("A");
        assertEquals(1, mySet.size());
    }

    @Test
    void isEmpty() {
        assertTrue(mySet.isEmpty());
        mySet.add("A");
        assertTrue(!mySet.isEmpty());
    }

    @Test
    void contains() {
        assertTrue(!mySet.contains("A"));
        mySet.add("A");
        assertTrue(mySet.contains("A"));
        assertTrue(!mySet.contains("a"));
    }

    @Test
    void remove() {
        mySet.add("A");
        mySet.add("B");
        mySet.remove("B");
        assertTrue(!mySet.contains("B"));
        assertEquals(1, mySet.size());
    }

    @Test
    void containsAll() {
        mySet.add("A");
        mySet.add("B");
        mySet.add("C");

        coll.add("A");
        coll.add("B");

        assertTrue(mySet.containsAll(coll));

        coll.add("D");

        assertTrue(!mySet.containsAll(coll));
    }

    @Test
    void addAll() {
        coll.add("A");
        coll.add("B");

        mySet.addAll(coll);

        assertTrue(mySet.containsAll(coll));
        assertEquals(2, mySet.size());
    }

    @Test
    void retainAll() {
        mySet.add("A");
        mySet.add("B");
        mySet.add("C");

        coll.add("A");
        coll.add("B");

        assertTrue(mySet.retainAll(coll));
        assertEquals(2, mySet.size());
    }

    @Test
    void removeAll() {
        mySet.add("A");
        mySet.add("B");
        mySet.add("C");

        coll.add("A");
        coll.add("B");

        assertTrue(mySet.removeAll(coll));
        assertEquals(1, mySet.size());
    }

    @Test
    void clear() {
        mySet.add("A");
        mySet.add("B");
        mySet.add("C");
        mySet.clear();

        assertTrue(mySet.isEmpty());
    }
}