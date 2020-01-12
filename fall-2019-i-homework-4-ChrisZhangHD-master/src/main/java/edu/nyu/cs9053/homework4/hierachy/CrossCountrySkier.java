package edu.nyu.cs9053.homework4.hierachy;

public class CrossCountrySkier extends WinterSportPlayer {

    private final int skiLength;

    public CrossCountrySkier(String name, int age, int skiLength) {
        super(name, age);
        this.skiLength = skiLength;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    public int getSkiLength(){
        return skiLength;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())){
            return false;
        }
        CrossCountrySkier that = (CrossCountrySkier) obj;
        return (name == null ? that.name == null : name.equals(that.name))
                && (age == that.age)
                && (skiLength == that.skiLength);

    }

    @Override
    public int hashCode(){
        int hash = (name == null ? 0 : name.hashCode());
        hash = 31 * hash + age;
        hash = 31 * hash + skiLength;
        return hash;
    }
}
