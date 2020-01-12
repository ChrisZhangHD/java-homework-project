package edu.nyu.cs9053.homework9;

public class Eminem implements Ballot{

    private final boolean isAgree;

    public Eminem(boolean isAgree) {
        this.isAgree = isAgree;
    }

    @Override
    public boolean isYes() {
        return isAgree;
    }
}
