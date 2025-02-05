package model.animals;

import model.Island;
import model.Location;
import utils.FoodChain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {

    public Predator(Location location) {
        super(location);
    }

    @Override
    public void eat(Island island) {
        // Перебираем объекты в клетке
        List<Animal> animals = location.getAnimalsSnapshot();
        for (Animal prey : animals) {
            if (prey == this || !prey.isAlive()) continue;
            // Хищник ест только травоядных (в данной модели)
            if (prey instanceof Predator) continue;
            int prob = FoodChain.getProbability(this.species, prey.getSpecies());
            if (prob > 0) {
                int rnd = ThreadLocalRandom.current().nextInt(100);
                if (rnd < prob) {
                    // Успешная охота
                    prey.die();
                    location.removeAnimal(prey);
                    // Прибавляем энергию – вес добычи (но не больше максимума)
                    energy = Math.min(energy + prey.weight, getMaxEnergy());
                    break; // съедаем только одного prey за такт
                }
            }
        }
    }
}
