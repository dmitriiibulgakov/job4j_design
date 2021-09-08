package ru.job4j.collection.set;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAdd3Words() {
        Set<String> set = new SimpleSet<>();
        assertTrue(set.add("First"));
        assertTrue(set.add("Second"));
        assertTrue(set.add("Third"));
        assertTrue(set.contains("First"));
        assertTrue(set.contains("Second"));
        assertTrue(set.contains("Third"));
        assertFalse(set.add("First"));
        assertFalse(set.add("Second"));
        assertFalse(set.add("Third"));
        assertTrue(set.add("Fourth"));
        assertFalse(set.add("Fourth"));
        assertTrue(set.contains("Fourth"));
    }
}
