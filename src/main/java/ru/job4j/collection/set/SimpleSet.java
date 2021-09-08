package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private final SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        } else {
            set.add(value);
            return true;
        }
    }

    @Override
    public boolean contains(T value) {
        for (T t : set) {
            if (Objects.equals(t, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
