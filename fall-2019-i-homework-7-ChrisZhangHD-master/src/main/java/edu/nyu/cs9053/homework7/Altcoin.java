package edu.nyu.cs9053.homework7;

public class Altcoin implements Cryptocurrency{

    private final double amount;

    public Altcoin(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        Altcoin that = (Altcoin) obj;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(amount).hashCode();
    }
}
