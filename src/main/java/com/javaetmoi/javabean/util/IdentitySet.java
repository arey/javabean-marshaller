package com.javaetmoi.javabean.util;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

public class IdentitySet<E> implements Set<E> {
    private static final Object VALUE = new Object();

    private final IdentityHashMap<E, Object> map = new IdentityHashMap<E, Object>();

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean add(E o) {
        return map.put(o, VALUE) == null;
    }

    public boolean contains(Object o) {
        return map.get(o) == VALUE;
    }

    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    public void clear() {
        map.clear();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }
}
