package model.animals.predators;

import model.Location;
import model.animals.Animal;
import model.animals.Predator;

public class Fox extends Predator {
    public Fox(Location location) {
        super(location);
        this.species = "Fox";
        this.weight = 8;
        this.maxCountPerCell = 30;
        this.speed = 2;
        this.requiredFood = 2;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Fox(location);
    }
}
