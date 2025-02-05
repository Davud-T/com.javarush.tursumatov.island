package model.animals.herbivores;

import model.Location;
import model.animals.Animal;
import model.animals.Herbivore;

public class Goat extends Herbivore {
    public Goat(Location location) {
        super(location);
        this.species = "Goat";
        this.weight = 60;
        this.maxCountPerCell = 140;
        this.speed = 3;
        this.requiredFood = 10;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Goat(location);
    }
}
