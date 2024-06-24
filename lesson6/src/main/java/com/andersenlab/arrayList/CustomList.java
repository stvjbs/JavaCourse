package com.andersenlab.arrayList;

public interface CustomList<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
    void remove(int index);
    int size();
}
