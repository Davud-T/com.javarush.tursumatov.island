package model.animals.predators;

import model.Island;
import model.Location;
import model.animals.Animal;
import model.animals.herbivores.Herbivore;
import model.animals.herbivores.Rabbit;
import util.Direction;
import util.FoodChain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Animal {
    public Wolf() {
        super(50, 30, 3, 8);
    }

    @Override
    public void eat() {
        Location loc = getCurrentLocation();
        boolean ate = false;
        // Перебираем все объекты в локации
        for (Animal a : loc.getAnimals()) {
            // Если добыча является травоядной
            if (a instanceof Herbivore) {
                int chance = FoodChain.getProbability(Wolf.class, a.getClass());
                int roll = ThreadLocalRandom.current().nextInt(100);
                if (roll < chance) {
                    loc.removeAnimal(a);
                    currentFoodLevel = foodRequired; // насыщаемся полностью
                    ate = true;
                    break;
                }
            }
        }
    }

    @Override
    public void move(Island island) {
        List<Location> adjacent = island.getAdjacentLocations(getCurrentLocation());
        Location randomLoc = adjacent.get(ThreadLocalRandom.current().nextInt(adjacent.size()));
        getCurrentLocation().removeAnimal(this);
        randomLoc.addAnimal(this);
        setCurrentLocation(randomLoc);
    }

    @Override
    public void reproduce() {
        Location loc = getCurrentLocation();
        int count = 0;
        for (Animal a : loc.getAnimals()) {
            if (a instanceof Wolf) count++;
        }
        if (count >= 2 && count < maxPopulationPerCell) {
            Wolf baby = new Wolf();
            baby.setCurrentLocation(loc);
            loc.addAnimal(baby);
        }
    }

    @Override
    public String getEmoji() {
        return "🐺";
    }
}
