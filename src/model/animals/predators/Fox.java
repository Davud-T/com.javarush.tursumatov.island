package model.animals.predators;

import model.Island;
import model.Location;
import model.animals.Animal;
import model.animals.herbivores.Herbivore;
import model.animals.herbivores.Rabbit;
import util.FoodChain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Animal {

    public Fox() {
        super(8, 30, 2, 2);
    }

    @Override
    public void eat() {
        Location loc = getCurrentLocation();
        boolean ate = false;

        for (Animal a : loc.getAnimals()) {
            if (a instanceof Herbivore) {
                int chance = FoodChain.getProbability(Fox.class, a.getClass());
                int roll = ThreadLocalRandom.current().nextInt(100);

                if (roll < chance) {
                    loc.removeAnimal(a);
                    currentFoodLevel = Math.min(currentFoodLevel + a.getWeight(), foodRequired);
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
            if (a instanceof Fox) count++;
        }
        if (count >= 2 && count < maxPopulationPerCell) {
            Fox baby = new Fox();
            baby.setCurrentLocation(loc);
            loc.addAnimal(baby);
        }
    }

    @Override
    public String getEmoji() {
        return "🦊";
    }
}
