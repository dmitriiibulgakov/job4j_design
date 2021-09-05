package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inSize = 0;
    private int outSize = 0;

    public T poll() {
        if (inSize == 0 && outSize == 0) {
            throw new NoSuchElementException();
        } else if (outSize == 0) {
            for (int i = 0; i < inSize; i++) {
                out.push(in.pop());
                outSize++;
            }
            inSize = 0;
        }
        outSize--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
