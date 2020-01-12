package edu.nyu.cs9053.homework9;

import java.util.Random;
import java.util.concurrent.Semaphore;

public final class VoterImpl implements Voter {

    private final Semaphore binarySemaphore;
    private final Random random;

    VoterImpl(Semaphore binarySemaphore) {
        this.binarySemaphore = binarySemaphore;
        this.random = new Random();
    }

    @Override
    public QueueNumber vote(Queue queue) {
        try {
            binarySemaphore.acquire();
        } catch (InterruptedException ie){
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
        try{
            if (queue.full()){
                return null;
            }
            return queue.addBallot(new Eminem(random.nextBoolean()));
        }
        finally {
            binarySemaphore.release();
        }
    }
}
