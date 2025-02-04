package model;

public class Plant {
    private String type;

    public Plant(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return type;
    }
}
