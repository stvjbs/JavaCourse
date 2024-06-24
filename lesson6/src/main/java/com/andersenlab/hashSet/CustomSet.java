package com.andersenlab.hashSet;

public interface CustomSet<E> extends Iterable<E> {
    boolean put(E obj);

    boolean contains(E obj);

    boolean delete(E obj);
}
