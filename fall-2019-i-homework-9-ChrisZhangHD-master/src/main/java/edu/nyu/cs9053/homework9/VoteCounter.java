package edu.nyu.cs9053.homework9;

/**
 * User: blangel
 */
public interface VoteCounter {

    /**
     * Processes an {@linkplain QueueNumber} within {@link Queue} from {@code from} only if there's values within it.
     *
     * @param from to extract a {@link QueueNumber} if any
     * @return the extracted {@link QueueNumber} or null if none available to be counted
     */
    QueueNumber count(Queue from);

}
