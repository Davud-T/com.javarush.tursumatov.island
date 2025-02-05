package model.animals.herbivores;

import model.Location;
import model.animals.Animal;
import model.animals.Herbivore;

public class Buffalo extends Herbivore {
    public Buffalo(Location location) {
        super(location);
        this.species = "Buffalo";
        this.weight = 700;
        this.maxCountPerCell = 10;
        this.speed = 3;
        this.requiredFood = 100;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Buffalo(location);
    }
}
