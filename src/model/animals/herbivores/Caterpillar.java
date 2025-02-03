package model.animals.herbivores;

import model.Island;
import model.Location;
import model.Plant;
import model.animals.Animal;

public class Caterpillar extends Animal implements Herbivore {

    public Caterpillar() {
        super(0.01, 1000, 0, 0);
    }

    @Override
    public void eat() {
        Location loc = getCurrentLocation();
        if (!loc.getPlants().isEmpty()) {
            Plant plant = loc.getPlants().get(0);
            loc.removePlant(plant);
            currentFoodLevel = foodRequired; // остается 0
        }
    }

    @Override
    public void move(Island island) {
    }

    @Override
    public void reproduce() {
        Location loc = getCurrentLocation();
        int count = 0;
        for (Animal a : loc.getAnimals()) {
            if (a instanceof Caterpillar) {
                count++;
            }
        }

        if (count >= 2 && count < maxPopulationPerCell) {
            Caterpillar baby = new Caterpillar();
            baby.setCurrentLocation(loc);
            loc.addAnimal(baby);
        }
    }

    @Override
    public String getEmoji() {
        return "🐛";
    }
}
