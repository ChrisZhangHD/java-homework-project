package edu.nyu.cs9053.homework5;

public class TimeTravelerDouble extends GeneralTimeTraveler  {

    public TimeTravelerDouble(String name, Double remainingYearsOfTravel) {
        super(name, remainingYearsOfTravel);
    }

    @Override
    public void adjust(Time unit, int amount, boolean ahead) {
        if (getRemainingYearsOfTravel() > 0d) {
            setRemainingYearsOfTravel(getRemainingYearsOfTravel() - convertTime(unit, amount));
        }
    }
}
