package edu.nyu.cs9053.homework4.hierachy;

public class Luger extends WinterSportPlayer {

    private final String sledColor;

    public Luger(String name, int age, String sledColor) {
        super(name, age);
        this.sledColor = sledColor;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    public String getSledColor() {
        return sledColor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Luger that = (Luger) obj;
        return (name == null ? that.name == null : name.equals(that.name))
                && age == that.age
                && (sledColor == null ? that.sledColor == null : sledColor.equals(that.sledColor));
    }

    @Override
    public int hashCode() {
        int hash = name == null ? 0 : name.hashCode();
        hash = 31 * hash + age;
        hash = 31 * hash + sledColor == null ? 0 : sledColor.hashCode();
        return hash;
    }
}
