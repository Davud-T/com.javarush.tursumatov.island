package model.animals.herbivores;

import model.Island;
import model.Location;
import model.animals.Animal;
import model.animals.Herbivore;

public class Caterpillar extends Herbivore {
    public Caterpillar(Location location) {
        super(location);
        this.species = "Caterpillar";
        this.weight = 0.01;
        this.maxCountPerCell = 1000;
        this.speed = 0;
        this.requiredFood = 0;
        this.energy = getMaxEnergy();
    }

    @Override
    public void act(Island island) {
        eat(island);
    }

    @Override
    public void eat(Island island) {
        location.consumePlant();
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Caterpillar(location);
    }
}
