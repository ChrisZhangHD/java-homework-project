package edu.nyu.cs9053.homework5;

abstract class GeneralTimeTraveler implements TimeTraveler{

    private final String name;

    private Double remainingYearsOfTravel;

    protected GeneralTimeTraveler(String name, Double remainingYearsOfTravel){
        this.name = name;
        this.remainingYearsOfTravel = remainingYearsOfTravel;
    }

    public String getName(){
        return name;
    }

    public Double getRemainingYearsOfTravel(){
        return remainingYearsOfTravel;
    }

    public void setRemainingYearsOfTravel(Double remainingYearsOfTravel) {
        this.remainingYearsOfTravel = remainingYearsOfTravel;
    }

    public Double convertTime(Time unit, int amount){
        Double convertResult = 0d;
        if(unit == Time.Days){
            convertResult =  amount / 365.0;
        }else if(unit == Time.Hours){
            convertResult = amount / (365.0 * 24.0);
        }
        return convertResult;
    }
}
