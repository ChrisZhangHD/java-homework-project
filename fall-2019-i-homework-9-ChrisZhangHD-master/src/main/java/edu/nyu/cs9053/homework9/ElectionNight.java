package edu.nyu.cs9053.homework9;

import java.util.ArrayList;
import java.util.List;

/**
 * User: blangel
 */
public class ElectionNight {

    public static void main(String[] args) {
        Queue queue = new Queue();
        Voter voterOne = Factory.createVoter();
        Voter voterTwo = Factory.createVoter();
        Voter voterThree = Factory.createVoter();
        VoteCounter counter = Factory.createVoteCounter();
        @SuppressWarnings("serial")
	ElectionNight electionNight = new ElectionNight(queue, new ArrayList<Voter>(3) {{ add(voterOne); add(voterTwo); add(voterThree); }},
                                                               new ArrayList<VoteCounter>(1) {{ add(counter); }});
        electionNight.start();
    }

    private final Queue queue;

    private final List<Voter> voters;

    private final List<VoteCounter> voteCounters;

    public ElectionNight(Queue queue, List<Voter> voters, List<VoteCounter> voteCounters) {
        this.queue = queue;
        this.voters = voters;
        this.voteCounters = voteCounters;
    }

    private void start() {
        List<Thread> threads = new ArrayList<>(voters.size() + voteCounters.size());
        setupVoters(threads);
        setupVoteCounters(threads);
        for (Thread thread : threads) {
            thread.start();
        }
        waitForThreads(threads);
        // failure, orderly shutdown and error message print
        orderlyShutdown(threads);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
        System.err.printf("Fail! Voter and/or VoteCounter not implemented properly%n");
        System.exit(1);
    }

    private void setupVoters(List<Thread> threads) {
        for (Voter voter : voters) {
            Thread voterThread = new Thread(new Runnable() {
                @Override public void run() {
                    while (!Thread.currentThread().isInterrupted()) {
                        QueueNumber queueNumber = voter.vote(queue);
                        if (queueNumber != null) {
                            System.out.printf("Made vote %d, %s to %s%n", queueNumber.getQueueNumber(), queueNumber.getBallot().isYes() ? "yes" : "no", queueNumber.getBallot().getClass().getSimpleName());
                        }
                    }
                }
            });
            threads.add(voterThread);
        }
    }

    private void setupVoteCounters(List<Thread> threads) {
        for (VoteCounter voteCounter : voteCounters) {
            Thread voteCounterThread = new Thread(new Runnable() {
                @Override public void run() {
                    while (!Thread.currentThread().isInterrupted()) {
                        QueueNumber queueNumber = voteCounter.count(queue);
                        if (queueNumber != null) {
                            System.out.printf("Counted %s as %s with queue number %d%n", queueNumber.getBallot().getClass().getSimpleName(), queueNumber.getBallot().isYes() ? "yes" : "no", queueNumber.getQueueNumber());
                        }
                    }
                }
            });
            threads.add(voteCounterThread);
        }
    }

    private void waitForThreads(List<Thread> threads) {
        while (allAlive(threads)) {
            Thread.yield();
        }
    }

    private boolean allAlive(List<Thread> threads) {
        for (Thread thread : threads) {
            if (!thread.isAlive()) {
                return false;
            }
        }
        return true;
    }

    private void orderlyShutdown(List<Thread> threads) {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

}
