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
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
           return true;
        }
        return false;
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
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int point = -1;

            @Override
            public boolean hasNext() {
                for (int i = point + 1; i < table.length; i++) {
                    if (table[i] != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = point + 1; i < table.length; i++) {
                    if (table[i] != null) {
                        point = i;
                        break;
                    }
                }
                return table[point].key;
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
