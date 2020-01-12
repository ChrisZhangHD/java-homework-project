package edu.nyu.cs9053.homework8;

public class WeightedLambdaJob extends LambdaJob{

    private final int weightValue;

    public WeightedLambdaJob(int id, int startTime, int endTime, int weightValue) {
        super(id, startTime, endTime);
        this.weightValue = weightValue;
    }

    public int getWeightValue() {
        return weightValue;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        WeightedLambdaJob that = (WeightedLambdaJob) obj;
        return getId() == that.getId()
                && getWeightValue() == that.getWeightValue()
                && getStartTime() == that.getStartTime()
                && getFinalTime() == that.getFinalTime();
    }

    @Override
    public int hashCode() {
        int hash = getId();
        hash = 31 * hash + getWeightValue();
        hash = 31 * hash + getStartTime();
        hash = 31 * hash + getFinalTime();
        return hash;
    }
}
