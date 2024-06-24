package com.andersenlab.arrayList;

import java.util.Arrays;
import java.util.Iterator;

public class CustomArrayList<E> implements CustomList<E> {
    private int size;
    private E[] elements;
    public CustomArrayList() {
        this.elements = (E[]) new Object[10];
    }

    public void extendSize(){
        if (this.size > this.elements.length* 0.8){
            int newArrayLength = this.elements.length * 2;
            E[] newElements = (E[]) new Object[newArrayLength];
            System.arraycopy(this.elements, 0, newElements, 0, this.size);
            this.elements = newElements;
        }
    }

    @Override
    public void add(E value) {
        extendSize();
        elements[size] = value;
        size++;
    }

    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public void remove(int index) {
        E[] newElements = (E[]) new Object[this.elements.length];
        System.arraycopy(this.elements, 0,newElements, 0, index);
        System.arraycopy(this.elements, index+1,newElements, index, elements.length-index-1);
        this.elements = newElements;
        size--;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<>();
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "size=" + size +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }

    private class CustomIterator<E> implements Iterator<E> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return this.current < size();
        }

        @Override
        public E next() {
            return (E) elements[current++];
        }
    }
}
