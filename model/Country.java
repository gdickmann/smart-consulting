package model;

public class Country {

    public Country(String name, int timesInserted) {
        this.name = name;
        this.timesInserted = timesInserted;
    }
    
    private String name;
    private int timesInserted;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getTimesInserted() {
        return timesInserted;
    }
    public void setTimesInserted(int timesInserted) {
        this.timesInserted = timesInserted;
    }
}
