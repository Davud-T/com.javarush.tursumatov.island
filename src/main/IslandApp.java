package main;

import initializer.SimulationInitializer;
import model.Island;
import model.Location;
import model.animals.Animal;
import config.Settings;

import java.util.List;
import java.util.concurrent.*;

public class IslandApp {
    public static void main(String[] args) {

        Island island = new Island(Settings.ISLAND_WIDTH, Settings.ISLAND_HEIGHT);
        SimulationInitializer.initialize(island);

        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(4);
        ExecutorService animalExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Runnable animalLifecycleTask = () -> {
            for (int i = 0; i < island.getWidth(); i++) {
                for (int j = 0; j < island.getHeight(); j++) {
                    Location loc = island.getLocation(i, j);
                    List<Animal> animals = loc.getAnimalsSnapshot();
                    for (Animal animal : animals) {
                        // Если животное уже погибло – пропускаем
                        if (!animal.isAlive()) continue;
                        animalExecutor.submit(() -> {
                            animal.act(island);
                        });
                    }
                }
            }
        };

        Runnable plantGrowthTask = () -> {
            for (int i = 0; i < island.getWidth(); i++) {
                for (int j = 0; j < island.getHeight(); j++) {
                    Location loc = island.getLocation(i, j);
                    synchronized (loc) {
                        int newPlants = ThreadLocalRandom.current().nextInt(1, 4);
                        loc.addPlants(newPlants);
                    }
                }
            }
        };

        Runnable statisticsTask = () -> {
            island.printStatistics();
        };

        Runnable simulationTickTask = () -> {
            animalLifecycleTask.run();
            plantGrowthTask.run();
            statisticsTask.run();

            if (island.getTotalAnimalCount() == 0) {
                System.out.println("Все животные погибли. Симуляция завершена.");
                scheduledExecutor.shutdown();
                animalExecutor.shutdown();
            }
        };

        scheduledExecutor.scheduleAtFixedRate(simulationTickTask, 0,
                Settings.SIMULATION_TICK_MILLIS, TimeUnit.MILLISECONDS);
    }
}
