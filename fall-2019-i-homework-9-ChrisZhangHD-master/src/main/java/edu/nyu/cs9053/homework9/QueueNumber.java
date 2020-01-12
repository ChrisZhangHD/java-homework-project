package edu.nyu.cs9053.homework9;

/**
 * User: blangel
 */
public class QueueNumber {

    private final int queueNumber;

    private final Ballot ballot;

    public QueueNumber(int queueNumber, Ballot ballot) {
        this.queueNumber = queueNumber;
        this.ballot = ballot;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public Ballot getBallot() {
        return ballot;
    }
}
