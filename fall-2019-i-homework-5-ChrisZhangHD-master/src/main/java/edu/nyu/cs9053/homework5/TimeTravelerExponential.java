package edu.nyu.cs9053.homework5;

public class TimeTravelerExponential extends GeneralTimeTraveler {

    private final Double constant;

    public TimeTravelerExponential(String name, Double remainingYearsOfTravel, Double constant) {
        super(name, remainingYearsOfTravel);
        this.constant = constant;
    }

    @Override
    public void adjust(Time unit, int amount, boolean ahead) {
        if (getRemainingYearsOfTravel() > 0.00001d){
            setRemainingYearsOfTravel(getRemainingYearsOfTravel() * Math.exp(- (constant * convertTime(unit, amount))));
        }

    }
}
