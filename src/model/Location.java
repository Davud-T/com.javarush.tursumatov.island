package model;

import config.Settings;
import model.animals.Animal;
import java.util.ArrayList;
import java.util.List;

public class Location {
    private final int x;
    private final int y;
    private final List<Animal> animals;
    private final List<Plant> plants;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
    }

    public synchronized void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public synchronized List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public synchronized void addPlant(Plant plant) {
        plants.add(plant);
    }

    public synchronized void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public synchronized List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void growPlants() {
        int currentPlants = plants.size();
        for (int i = 0; i < Settings.PLANT_GROWTH_PER_TICK && currentPlants + i < Settings.MAX_PLANTS_PER_CELL; i++) {
            plants.add(new Plant("🌿"));
        }
    }

    @Override
    public String toString() {
        return "Location(" + x + ", " + y + ") " +
                "Animals: " + animals.size() +
                ", Plants: " + plants.size();
    }
}
