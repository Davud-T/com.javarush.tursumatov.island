package model.animals.predators;

import model.Location;
import model.animals.Animal;
import model.animals.Predator;

public class Wolf extends Predator {
    public Wolf(Location location) {
        super(location);
        this.species = "Wolf";
        this.weight = 50;
        this.maxCountPerCell = 30;
        this.speed = 3;
        this.requiredFood = 8;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Wolf(location);
    }
}
