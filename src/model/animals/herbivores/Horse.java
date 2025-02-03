package model.animals.herbivores;

import model.Island;
import model.Location;
import model.Plant;
import model.animals.Animal;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Horse extends Animal implements Herbivore {

    public Horse() {
        super(400, 20, 4, 60);
    }

    @Override
    public void eat() {
        Location loc = getCurrentLocation();
        if (!loc.getPlants().isEmpty()) {
            Plant plant = loc.getPlants().get(0);
            loc.removePlant(plant);
            currentFoodLevel = foodRequired;
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
            if (a instanceof Horse) count++;
        }
        if (count >= 2 && count < maxPopulationPerCell) {
            Horse baby = new Horse();
            baby.setCurrentLocation(loc);
            loc.addAnimal(baby);
        }
    }

    @Override
    public String getEmoji() {
        return "🐎";
    }
}
