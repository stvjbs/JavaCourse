package com.andersenlab.arrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {
    CustomArrayList<Integer> customArrayList;
    @BeforeEach
    void setUp() {
        this.customArrayList = new CustomArrayList<>();
    }
    @Test
    void extendSizeTest() {
        customArrayList.add(1);
        customArrayList.add(2);
        customArrayList.add(3);
        customArrayList.add(4);
        customArrayList.add(5);
        customArrayList.add(6);
        customArrayList.add(7);
        int lengthBeforeExtending = customArrayList.getArrayLength();
        customArrayList.add(8);
        int lengthAfterExtending = customArrayList.getArrayLength();
        assertEquals(lengthBeforeExtending * 2, lengthAfterExtending);
    }

    @Test
    void addIntegerTest() {
        assertTrue(customArrayList.add(1));
    }

    @Test
    void addStringTest() {
        CustomArrayList<String> customStringArrayList = new CustomArrayList<>();
        assertTrue(customStringArrayList.add("1m"));
    }

    @Test
    void getElementByIndexTest() {
        customArrayList.add(1);
        assertEquals(1, customArrayList.get(0));
    }

    @Test
    void removeElementByIndexTest() {
        customArrayList.add(1);
        customArrayList.add(2);
        customArrayList.remove(0);
        assertEquals(2, customArrayList.get(0));
    }

    @Test
    void sizeTest() {
        customArrayList.add(1);
        customArrayList.add(2);
        assertEquals(2, customArrayList.size());
    }

    @Test
    void iteratorFalseHasNextTest() {
        assertFalse(customArrayList.iterator().hasNext());
    }

    @Test
    void iteratorTrueHasNextTest() {
        customArrayList.add(1);
        assertTrue(customArrayList.iterator().hasNext());
    }

    @Test
    void iteratorNextTest() {
        customArrayList.add(1);
        assertEquals(1, customArrayList.iterator().next());
    }
}