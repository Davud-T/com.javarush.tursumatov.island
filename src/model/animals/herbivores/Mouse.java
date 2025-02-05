package model.animals.herbivores;

import model.Location;
import model.animals.Animal;
import model.animals.Herbivore;

public class Mouse extends Herbivore {
    public Mouse(Location location) {
        super(location);
        this.species = "Mouse";
        this.weight = 0.05;
        this.maxCountPerCell = 500;
        this.speed = 1;
        this.requiredFood = 0.01;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Mouse(location);
    }
}
