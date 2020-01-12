package edu.nyu.cs9053.homework9;

import java.util.concurrent.Semaphore;

/**
 * User: blangel
 */
public class Factory {

    private final static Semaphore BINARY_SEMAPHORE = new Semaphore(1);

    public static Voter createVoter() {
        return new VoterImpl(BINARY_SEMAPHORE);
    }

    public static VoteCounter createVoteCounter() {
        return new VoterCounterImpl(BINARY_SEMAPHORE);
    }
}
