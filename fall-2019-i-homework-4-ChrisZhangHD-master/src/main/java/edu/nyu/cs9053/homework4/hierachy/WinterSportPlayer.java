package edu.nyu.cs9053.homework4.hierachy;

public abstract class WinterSportPlayer {

    protected final String name;

    protected final int age;

    public WinterSportPlayer(String name, int age){
        this.name = name;
        this.age = age;
    }

    public abstract String getName();

    public abstract int getAge();
}
