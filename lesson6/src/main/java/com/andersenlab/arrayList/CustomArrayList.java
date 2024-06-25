package com.andersenlab.arrayList;

import java.util.Iterator;

public class CustomArrayList<E> implements CustomList<E> {
    private final double COEFFICIENT_FOR_EXTENDING_ARRAY = 0.7;
    private int size;
    private E[] elements;
    private int arrayLength = 10;


    public CustomArrayList() {
        this.elements = (E[]) new Object[arrayLength];
    }

    public void extendSize() {
        if (this.size > this.arrayLength * COEFFICIENT_FOR_EXTENDING_ARRAY) {
            this.arrayLength *= 2;
            E[] newElements = (E[]) new Object[arrayLength];
            System.arraycopy(this.elements, 0, newElements, 0, this.size);
            this.elements = newElements;
        }
    }

    @Override
    public boolean add(E value) {
        elements[size] = value;
        size++;
        extendSize();
        return true;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return elements[index];
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        E[] newElements = (E[]) new Object[this.elements.length];
        System.arraycopy(this.elements, 0, newElements, 0, index);
        System.arraycopy(this.elements, index + 1, newElements, index, elements.length - index - 1);
        this.elements = newElements;
        size--;
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<>();
    }

    public int getArrayLength() {
        return arrayLength;
    }

    private class CustomIterator<T> implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return this.current < size();
        }

        @Override
        public T next() {
            return (T) elements[current++];
        }
    }
}
