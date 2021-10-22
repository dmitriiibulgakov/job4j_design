package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SimpleTreeTest {
    Tree<Integer> tree = new SimpleTree<>(1);

    @Before
    public void initData() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
    }

    @Test
    public void when6ElFindLastThen6() {
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        assertFalse(tree.add(2, 6));
    }

    @Test
    public void whenChildNotExistOnLeafThenAdd() {
        assertTrue(tree.add(4, 7));
    }

    @Test
    public void whenParentNotExistOnLeafThenNotAdd() {
        assertFalse(tree.add(7, 8));
    }

    @Test
    public void whenTreeHaveMoreThan2LeafThenFalse() {
        assertFalse(tree.isBinary());
    }

    @Test
    public void whenTreeDoNotHaveMoreThan2LeafThenTrue() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(4, 5);
        tree.add(5, 6);
        tree.add(5, null);
        assertTrue(tree.isBinary());
    }

    @Test
    public void whenTreeIsEmptyThenTrue() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertTrue(tree.isBinary());
    }
}
