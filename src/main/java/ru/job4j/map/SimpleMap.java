package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
           table[index] = new MapEntry<>(key, value);
           count++;
           modCount++;
           rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tmpTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> mapEntry: tmpTable) {
            if (mapEntry != null) {
                put(mapEntry.key, mapEntry.value);
            }
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        return table[index] != null && table[index].key.equals(key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int point = -1;
            int index;

            @Override
            public boolean hasNext() {
                boolean rsl = false;
                for (int i = point + 1; i < table.length; i++) {
                    if (table[i] != null) {
                        index = i;
                        rsl = true;
                        break;
                    }
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                point = index;
                return table[index].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
