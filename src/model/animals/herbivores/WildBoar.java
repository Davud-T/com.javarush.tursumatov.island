package model.animals.herbivores;

import model.Location;
import model.animals.Animal;
import model.animals.Herbivore;

public class WildBoar extends Herbivore {
    public WildBoar(Location location) {
        super(location);
        this.species = "WildBoar";
        this.weight = 400;
        this.maxCountPerCell = 50;
        this.speed = 2;
        this.requiredFood = 50;
        this.energy = getMaxEnergy();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new WildBoar(location);
    }
}
