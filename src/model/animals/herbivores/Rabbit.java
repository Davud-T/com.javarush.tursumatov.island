package model.animals.herbivores;

import model.Location;
import model.animals.Animal;
import model.animals.Herbivore;

public class Rabbit extends Herbivore {
    public Rabbit(Location location) {
        super(location);
        this.species = "Rabbit";
        this.weight = 2;
        this.maxCountPerCell = 150;
        this.speed = 2;
        this.requiredFood = 0.45;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Rabbit(location);
    }
}
