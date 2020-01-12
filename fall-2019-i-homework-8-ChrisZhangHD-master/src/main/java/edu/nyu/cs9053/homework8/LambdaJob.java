package edu.nyu.cs9053.homework8;

public class LambdaJob {

    private final int id;
    private final int startTime;
    private final int finalTime;

    protected LambdaJob(int id, int startTime, int endTime) {
        this.id = id;
        this.startTime = startTime;
        this.finalTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinalTime() {
        return finalTime;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        LambdaJob that = (LambdaJob) obj;
        return id == that.id && startTime == that.startTime && finalTime == that.finalTime;
    }

    @Override
    public int hashCode() {
        int hash = id;
        hash = 31 * hash + startTime;
        hash = 31 * hash + finalTime;
        return hash;
    }
}
