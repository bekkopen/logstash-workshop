package no.bekk.logstash.workshop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCollection<E> {

    private final ArrayList<E> list = new ArrayList<E>();

    public RandomCollection(Collection<? extends E> input) {
        list.addAll(input);
    }

    public E next() {
        int value = ThreadLocalRandom.current().nextInt(0, list.size());
        return list.get(value);
    }
}
