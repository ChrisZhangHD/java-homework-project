package edu.nyu.cs9053.homework7;

public class AltcionArrayCreator implements ArrayCreator<Altcoin> {
    @Override
    public Altcoin[] create(int size) {
        return new Altcoin[size];
    }
}
