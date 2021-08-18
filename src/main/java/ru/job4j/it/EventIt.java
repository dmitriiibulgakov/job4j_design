package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EventIt implements Iterator<Integer> {
    private final int[] numbers;
    private int numberIndex = 0;

    public EventIt(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        while (numberIndex < numbers.length && numbers[numberIndex] % 2 != 0) {
            numberIndex++;
        }
        return numberIndex < numbers.length && numbers[numberIndex] % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[numberIndex++];
    }
}
