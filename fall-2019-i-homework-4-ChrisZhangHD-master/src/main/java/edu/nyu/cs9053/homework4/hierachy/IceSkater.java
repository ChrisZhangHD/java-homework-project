package edu.nyu.cs9053.homework4.hierachy;

public class IceSkater extends WinterSportPlayer {

    private final int skateSize;

    public IceSkater(String name, int age, int skateSize) {
        super(name, age);
        this.skateSize = skateSize;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    public int getSkateSize() {
        return skateSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        IceSkater that = (IceSkater) obj;
        return (name == null ? that.name == null : name.equals(that.name))
                && age == that.age
                && skateSize == that.skateSize;
    }

    @Override
    public int hashCode() {
        int hash = name == null ? 0 : name.hashCode();
        hash = 31 * hash + age;
        hash = 31 * hash + skateSize;
        return hash;
    }
}
