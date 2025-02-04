package model.animals;

import model.Island;
import model.Location;

public abstract class Animal {
    protected double weight;
    protected int maxPopulationPerCell;
    protected int maxSpeed;
    protected double foodRequired;
    protected double currentFoodLevel;
    protected Location currentLocation;  // Текущая локация животного

    public Animal(double weight, int maxPopulationPerCell, int maxSpeed, double foodRequired) {
        this.weight = weight;
        this.maxPopulationPerCell = maxPopulationPerCell;
        this.maxSpeed = maxSpeed;
        this.foodRequired = foodRequired;
        this.currentFoodLevel = foodRequired / 3;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public abstract void eat();

    public abstract void move(Island island);

    public abstract void reproduce();

    public boolean updateHunger() {
        currentFoodLevel -= (weight / 20);
        return currentFoodLevel > 0;
    }

    public double getWeight() {
        return weight;
    }

    public String getEmoji() {
        return null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
