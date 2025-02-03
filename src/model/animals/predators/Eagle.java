package model.animals.predators;

import model.Island;
import model.Location;
import model.animals.Animal;
import model.animals.herbivores.Herbivore;
import model.animals.herbivores.Rabbit;
import util.FoodChain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Animal {
    public Eagle() {
        super(6, 20, 3, 1);
    }

    @Override
    public void eat() {
        Location loc = getCurrentLocation();
        boolean ate = false;
        for (Animal a : loc.getAnimals()) {
            if (a instanceof Herbivore) {
                int chance = FoodChain.getProbability(Boa.class, a.getClass());
                int roll = ThreadLocalRandom.current().nextInt(100);
                if (roll < chance) {
                    loc.removeAnimal(a);
                    currentFoodLevel = foodRequired;
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
            if (a instanceof Eagle) count++;
        }
        if (count >= 2 && count < maxPopulationPerCell) {
            Eagle baby = new Eagle();
            baby.setCurrentLocation(loc);
            loc.addAnimal(baby);
        }
    }

    @Override
    public String getEmoji() {
        return "🦅";
    }
}
