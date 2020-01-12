package edu.nyu.cs9053.homework9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: blangel
 */
public class Queue {

    private static final int DEFAULT_CAPACITY = 100;

    private final Collection<QueueNumber> queue;

    // the amount of orders which can be queued
    private final int capacity;

    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    public Queue(int capacity) {
        this.queue = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public QueueNumber getQueueNumber() {
        Iterator<QueueNumber> iterator = queue.iterator();
        if (!iterator.hasNext()) {
            throw new UnsupportedOperationException();
        }
        QueueNumber queueNumber = iterator.next();
        iterator.remove();
        return queueNumber;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean full() {
        return (queue.size() == capacity);
    }

    public QueueNumber addBallot(Ballot ballot) {
        if (full()) {
            throw new UnsupportedOperationException();
        }
        QueueNumber queueNumber = new QueueNumber(queue.size(), ballot); 
        queue.add(queueNumber);
        return queueNumber;
    }

}
