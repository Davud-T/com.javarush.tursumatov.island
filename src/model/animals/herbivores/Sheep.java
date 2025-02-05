package model.animals.herbivores;

import model.Location;
import model.animals.Animal;
import model.animals.Herbivore;

public class Sheep extends Herbivore {
    public Sheep(Location location) {
        super(location);
        this.species = "Sheep";
        this.weight = 70;
        this.maxCountPerCell = 140;
        this.speed = 3;
        this.requiredFood = 15;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Sheep(location);
    }
}
