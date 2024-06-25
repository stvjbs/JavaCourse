package com.andersenlab.hashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CustomHashSetTest {
    CustomHashSet<Integer> customHashSet;

    @BeforeEach
    void setUp() {
        customHashSet = new CustomHashSet<>();
    }

    @Test
    void putElementTest() {
        assertTrue(customHashSet.put(1));
    }

    @Test
    void containsElementTest() {
        customHashSet.put(1);
        assertTrue(customHashSet.contains(1));
    }

    @Test
    void deleteElementTest() {
        customHashSet.put(1);
        assertTrue(customHashSet.delete(1));
    }

    @Test
    void deletedElementNotContainsTest() {
        assertFalse(customHashSet.contains(1));
    }

    @Test
    void iteratorFalseHasNextTest() {
        assertFalse(customHashSet.iterator().hasNext());
    }

    @Test
    void iteratorTrueHasNextTest() {
        customHashSet.put(1);
        assertTrue(customHashSet.iterator().hasNext());
    }

    @Test
    void iteratorNextTest() {
        customHashSet.put(1);
        customHashSet.put(2);
        Iterator<Integer> e = customHashSet.iterator();
        assertEquals(e.next(), 1);
        assertEquals(e.next(), 2);
    }
}