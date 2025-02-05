package model.animals.herbivores;

import model.Location;
import model.animals.Animal;
import model.animals.Herbivore;

public class Horse extends Herbivore {
    public Horse(Location location) {
        super(location);
        this.species = "Horse";
        this.weight = 400;
        this.maxCountPerCell = 20;
        this.speed = 4;
        this.requiredFood = 60;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Horse(location);
    }
}
