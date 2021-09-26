package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {

    Map<String, String> map;

    @Before
    public void initData() {
        map = new SimpleMap<>();
        map.put("One", "Lesson");
        map.put("Two", "Lessons");
    }

    @Test
    public void whenPutThenHaveTwoElements() {
        Assert.assertFalse(map.put("One", "Lesson"));
        Assert.assertFalse(map.put("Two", "Lessons"));
        Assert.assertTrue(map.put("Three", "Lessons"));
        Assert.assertFalse(map.put("One", "Lesson"));
    }

    @Test
    public void whenGetOneThenLesson() {
        Assert.assertEquals("Lesson", map.get("One"));
    }

    @Test
    public void whenGetThreeThenNull() {
        Assert.assertNull(map.get("Three"));
    }

    @Test
    public void whenRemoveFreeThenFalse() {
        Assert.assertFalse(map.remove("Three"));
    }

    @Test
    public void whenRemoveOneThenTrue() {
        Assert.assertTrue(map.remove("One"));
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        map = new SimpleMap<>();
        Assert.assertFalse(map.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        map = new SimpleMap<>();
        map.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        Assert.assertEquals("Two", map.iterator().next());
        Assert.assertEquals("Two", map.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("Two", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("One", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAfterGetIteratorThenMustBeException() {
        Iterator<String> iterator = map.iterator();
        map.put("Many", "Lessons");
        iterator.next();
    }
}
