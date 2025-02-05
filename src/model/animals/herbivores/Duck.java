package model.animals.herbivores;

import model.Location;
import model.animals.Animal;
import model.animals.Herbivore;

public class Duck extends Herbivore {
    public Duck(Location location) {
        super(location);
        this.species = "Duck";
        this.weight = 1;
        this.maxCountPerCell = 200;
        this.speed = 4;
        this.requiredFood = 0.15;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Duck(location);
    }
}
