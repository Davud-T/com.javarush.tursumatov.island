package model.animals.predators;

import model.Location;
import model.animals.Animal;
import model.animals.Predator;

public class Eagle extends Predator {
    public Eagle(Location location) {
        super(location);
        this.species = "Eagle";
        this.weight = 6;
        this.maxCountPerCell = 20;
        this.speed = 3;
        this.requiredFood = 1;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Eagle(location);
    }
}
