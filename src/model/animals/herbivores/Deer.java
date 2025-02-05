package model.animals.herbivores;

import model.Location;
import model.animals.Animal;
import model.animals.Herbivore;

public class Deer extends Herbivore {
    public Deer(Location location) {
        super(location);
        this.species = "Deer";
        this.weight = 300;
        this.maxCountPerCell = 20;
        this.speed = 4;
        this.requiredFood = 50;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Deer(location);
    }
}
