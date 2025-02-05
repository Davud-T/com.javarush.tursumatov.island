package model;

import model.animals.Animal;
import config.Settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Location {

    private final int x;
    private final int y;
    private final List<Animal> animals = Collections.synchronizedList(new ArrayList<>());
    private int plantCount = Settings.MAX_PLANTS_PER_CELL;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public synchronized void addAnimal(Animal a) {
        animals.add(a);
    }

    public synchronized void removeAnimal(Animal a) {
        animals.remove(a);
    }

    public synchronized List<Animal> getAnimalsSnapshot() {
        return new ArrayList<>(animals);
    }

    public synchronized int getPlantCount() {
        return plantCount;
    }

    public synchronized void addPlants(int count) {
        plantCount = Math.min(plantCount + count, Settings.MAX_PLANTS_PER_CELL);
    }

    public synchronized boolean consumePlant() {
        if (plantCount > 0) {
            plantCount--;
            return true;
        }
        return false;
    }
}
