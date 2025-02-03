package com.island.simulation.main;



import config.Settings;
import model.Island;
import model.Location;
import model.Plant;
import model.animals.herbivores.*;
import model.animals.predators.*;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationMain {
    public static void main(String[] args) {

        Island island = new Island();
        Random random = new Random();
        int width = Settings.ISLAND_WIDTH;
        int height = Settings.ISLAND_HEIGHT;

        for (int i = 0; i < Settings.INITIAL_WOLVES; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Wolf wolf = new Wolf();
            wolf.setCurrentLocation(loc);
            loc.addAnimal(wolf);
        }

        for (int i = 0; i < Settings.INITIAL_BOAS; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Boa boa = new Boa();
            boa.setCurrentLocation(loc);
            loc.addAnimal(boa);
        }

        for (int i = 0; i < Settings.INITIAL_FOXES; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Fox fox = new Fox();
            fox.setCurrentLocation(loc);
            loc.addAnimal(fox);
        }

        for (int i = 0; i < Settings.INITIAL_BEARS; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Bear bear = new Bear();
            bear.setCurrentLocation(loc);
            loc.addAnimal(bear);
        }

        for (int i = 0; i < Settings.INITIAL_EAGLES; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Eagle eagle = new Eagle();
            eagle.setCurrentLocation(loc);
            loc.addAnimal(eagle);
        }

        for (int i = 0; i < Settings.INITIAL_RABBITS; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Rabbit rabbit = new Rabbit();
            rabbit.setCurrentLocation(loc);
            loc.addAnimal(rabbit);
        }

        for (int i = 0; i < Settings.INITIAL_HORSES; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Horse horse = new Horse();
            horse.setCurrentLocation(loc);
            loc.addAnimal(horse);
        }

        for (int i = 0; i < Settings.INITIAL_DEERS; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Deer deer = new Deer();
            deer.setCurrentLocation(loc);
            loc.addAnimal(deer);
        }

        for (int i = 0; i < Settings.INITIAL_MICE; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Mouse mouse = new Mouse();
            mouse.setCurrentLocation(loc);
            loc.addAnimal(mouse);
        }

        for (int i = 0; i < Settings.INITIAL_GOATS; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Goat goat = new Goat();
            goat.setCurrentLocation(loc);
            loc.addAnimal(goat);
        }

        for (int i = 0; i < Settings.INITIAL_SHEEPS; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Sheep sheep = new Sheep();
            sheep.setCurrentLocation(loc);
            loc.addAnimal(sheep);
        }

        for (int i = 0; i < Settings.INITIAL_BOARS; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Boar boar = new Boar();
            boar.setCurrentLocation(loc);
            loc.addAnimal(boar);
        }

        for (int i = 0; i < Settings.INITIAL_BUFFALO; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Buffalo buffalo = new Buffalo();
            buffalo.setCurrentLocation(loc);
            loc.addAnimal(buffalo);
        }

        for (int i = 0; i < Settings.INITIAL_DUCKS; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Duck duck = new Duck();
            duck.setCurrentLocation(loc);
            loc.addAnimal(duck);
        }

        for (int i = 0; i < Settings.INITIAL_CATERPILLARS; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            Caterpillar caterpillar = new Caterpillar();
            caterpillar.setCurrentLocation(loc);
            loc.addAnimal(caterpillar);
        }

        for (int i = 0; i < Settings.INITIAL_PLANTS; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Location loc = island.getLocation(x, y);
            loc.addPlant(new Plant("🌿"));
        }


        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("---- New Day ----");
            island.simulationTick();
            island.printAnimalCounts();
        }, 0, Settings.TICK_DURATION_MS, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(20 * Settings.TICK_DURATION_MS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
        System.out.println("Simulation terminated.");
    }
}
