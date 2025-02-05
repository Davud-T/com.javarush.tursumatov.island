package model.animals.predators;

import model.Location;
import model.animals.Animal;
import model.animals.Predator;

public class Bear extends Predator {
    public Bear(Location location) {
        super(location);
        this.species = "Bear";
        this.weight = 500;
        this.maxCountPerCell = 5;
        this.speed = 2;
        this.requiredFood = 80;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Bear(location);
    }
}
