package com.andersenlab.hashSet;

import java.util.HashMap;
import java.util.Iterator;

public class CustomHashSet<E> implements CustomSet<E> {
    private final Object VALUE = new Object();
    private final HashMap<E, Object> map;

    public CustomHashSet() {
        this.map = new HashMap<>();
    }

    @Override
    public boolean put(E obj) {
        map.put(obj, VALUE);
        return true;
    }

    @Override
    public boolean contains(E obj) {
        return map.containsKey(obj);
    }

    @Override
    public boolean delete(E obj) {
        map.remove(obj);
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }
}
