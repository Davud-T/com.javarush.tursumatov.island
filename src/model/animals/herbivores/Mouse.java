package model.animals.herbivores;

import model.Island;
import model.Location;
import model.Plant;
import model.animals.Animal;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Animal implements Herbivore {

    public Mouse() {
        super(0.05, 500, 1, 0.01);
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
            if (a instanceof Mouse) count++;
        }
        if (count >= 2 && count < maxPopulationPerCell) {
            Mouse baby = new Mouse();
            baby.setCurrentLocation(loc);
            loc.addAnimal(baby);
        }
    }

    @Override
    public String getEmoji() {
        return "🐁";
    }
}
