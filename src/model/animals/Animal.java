package model.animals;

import model.Island;
import model.Location;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Animal {
    protected String species;
    protected double weight;
    protected int maxCountPerCell;
    protected int speed;
    protected double requiredFood;
    protected double energy;
    protected Location location;
    protected boolean alive = true;


    protected final int id;
    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    public Animal(Location location) {
        this.location = location;
        this.id = idGenerator.incrementAndGet();
        this.energy = getMaxEnergy();
    }

    public double getMaxEnergy() {
        return requiredFood;
    }

    public String getSpecies() {
        return species;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
    }


    public void act(Island island) {
        if (!alive) return;
        eat(island);
        move(island);
        reproduce(island);
        // Энергозатраты – 10% от максимума
        energy -= (getMaxEnergy() * 0.1);
        if (energy <= 0) {
            die();
            location.removeAnimal(this);
        }
    }


    public abstract void eat(Island island);

    protected void move(Island island) {
        int currentX = location.getX();
        int currentY = location.getY();
        int dx = ThreadLocalRandom.current().nextInt(-speed, speed + 1);
        int dy = ThreadLocalRandom.current().nextInt(-speed, speed + 1);
        int newX = Math.max(0, Math.min(island.getWidth() - 1, currentX + dx));
        int newY = Math.max(0, Math.min(island.getHeight() - 1, currentY + dy));

        if (newX == currentX && newY == currentY) return;

        Location newLocation = island.getLocation(newX, newY);

        Location firstLock, secondLock;
        if (compareLocations(this.location, newLocation) <= 0) {
            firstLock = this.location;
            secondLock = newLocation;
        } else {
            firstLock = newLocation;
            secondLock = this.location;
        }

        synchronized (firstLock) {
            synchronized (secondLock) {
                // Проверяем, не превышает ли целевая локация максимально допустимое количество животных данного вида
                long count = newLocation.getAnimalsSnapshot().stream()
                        .filter(a -> a.getSpecies().equals(this.species))
                        .count();
                if (count >= maxCountPerCell) return;

                this.location.removeAnimal(this);
                newLocation.addAnimal(this);
                this.location = newLocation;
            }
        }
    }

    private int compareLocations(Location l1, Location l2) {
        if (l1.getX() != l2.getX()) {
            return Integer.compare(l1.getX(), l2.getX());
        }
        return Integer.compare(l1.getY(), l2.getY());
    }

    protected void reproduce(Island island) {
        List<Animal> animals = location.getAnimalsSnapshot();
        boolean partnerFound = animals.stream()
                .anyMatch(a -> a != this && a.getSpecies().equals(this.species) && a.isAlive());
        if (partnerFound && energy >= getMaxEnergy()) {
            Animal offspring = createOffspring(location);
            location.addAnimal(offspring);
            // Расход энергии на размножение – делим энергию пополам
            energy /= 2;
        }
    }

    protected abstract Animal createOffspring(Location location);
}
