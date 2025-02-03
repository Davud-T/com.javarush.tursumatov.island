package model.animals.herbivores;

import model.Island;
import model.Location;
import model.Plant;
import model.animals.Animal;
import util.Direction;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Rabbit extends Animal implements Herbivore {
    public Rabbit() {
        super(2, 150, 2, 0.45);
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
        Location current = getCurrentLocation();
        List<Location> adjacent = island.getAdjacentLocations(current);
        Location bestLocation = null;
        int maxPlants = -1;

        for (Location loc : adjacent) {
            int plantCount = loc.getPlants().size();
            if (plantCount > maxPlants) {
                maxPlants = plantCount;
                bestLocation = loc;
            }
        }

        int currentPlantCount = current.getPlants().size();
        if (bestLocation != null && maxPlants > currentPlantCount) {
            current.removeAnimal(this);
            bestLocation.addAnimal(this);
            setCurrentLocation(bestLocation);
        } else {
            // Если в соседних локациях нет более богатой еды – перемещаемся случайно
            Location randomLoc = adjacent.get(ThreadLocalRandom.current().nextInt(adjacent.size()));
            current.removeAnimal(this);
            randomLoc.addAnimal(this);
            setCurrentLocation(randomLoc);
        }
    }

    @Override
    public void reproduce() {
        Location loc = getCurrentLocation();
        int count = 0;
        for (Animal a : loc.getAnimals()) {
            if (a instanceof Rabbit) count++;
        }
        if (count >= 2 && count < maxPopulationPerCell) {
            Rabbit baby = new Rabbit();
            baby.setCurrentLocation(loc);
            loc.addAnimal(baby);
        }
    }

    @Override
    public String getEmoji() {
        return "\uD83D\uDC07";
    }
}
