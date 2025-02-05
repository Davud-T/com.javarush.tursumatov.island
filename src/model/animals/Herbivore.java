package model.animals;

import model.Island;
import model.Location;
import model.animals.herbivores.Caterpillar;
import model.animals.herbivores.Duck;
import utils.FoodChain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Herbivore extends Animal {

    public Herbivore(Location location) {
        super(location);
    }

    @Override
    public void eat(Island island) {
        // Пробуем съесть растение
        boolean atePlant = location.consumePlant();
        if (atePlant) {
            // За растение получаем +1 единицу энергии
            energy = Math.min(energy + 1, getMaxEnergy());
        }
        // Если это утка, дополнительно пробуем съесть гусеницу
        if (this instanceof Duck) {
            List<Animal> animals = location.getAnimalsSnapshot();
            for (Animal candidate : animals) {
                if (candidate == this || !candidate.isAlive()) continue;
                if (candidate instanceof Caterpillar) {
                    int prob = FoodChain.getProbability(this.species, candidate.getSpecies());
                    if (prob > 0 && ThreadLocalRandom.current().nextInt(100) < prob) {
                        candidate.die();
                        location.removeAnimal(candidate);
                        energy = Math.min(energy + candidate.weight, getMaxEnergy());
                        break;
                    }
                }
            }
        }
    }
}
