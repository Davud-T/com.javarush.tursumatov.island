package model.animals.predators;

import model.Location;
import model.animals.Animal;
import model.animals.Predator;

public class Boa extends Predator {
    public Boa(Location location) {
        super(location);
        this.species = "Boa";
        this.weight = 15;
        this.maxCountPerCell = 30;
        this.speed = 1;
        this.requiredFood = 3;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Boa(location);
    }
}
