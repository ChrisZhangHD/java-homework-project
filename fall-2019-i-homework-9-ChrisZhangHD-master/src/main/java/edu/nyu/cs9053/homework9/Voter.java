package edu.nyu.cs9053.homework9;

/**
 * User: blangel
 */
public interface Voter {

    /**
     * Attempts to vote by checking {@code queue} and if there's an available position places vote  resulting in
     * an {@link QueueNumber}.
     *
     * @param queue to place a new QueueNumber and get a new {@link QueueNumber} if possible
     * @return the produced {@link QueueNumber} or null if the {@link Voter} must continue to wait before voting
     */
    QueueNumber vote(Queue queue);

}
