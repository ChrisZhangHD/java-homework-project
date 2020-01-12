package edu.nyu.cs9053.homework4.hierachy;

public class Curler extends WinterSportPlayer {
    public Curler(String name, int age) {
        super(name, age);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
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
                && (age == that.age);

    }

    @Override
    public int hashCode(){
        int hash = (name == null ? 0 : name.hashCode());
        hash = 31 * hash + age;
        return hash;
    }
}
