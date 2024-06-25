package com.andersenlab.arrayList;

public interface CustomList<E> extends Iterable<E> {
    boolean add(E value);

    E get(int index);

    boolean remove(int index);

    int size();
}
