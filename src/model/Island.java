package model;

import model.animals.Animal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        locations = new Location[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Location getLocation(int x, int y) {
        return locations[x][y];
    }

    public void printStatistics() {
        Map<String, Integer> speciesCount = new HashMap<>();
        int totalPlants = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Location loc = locations[i][j];
                List<Animal> animals = loc.getAnimalsSnapshot();
                for (Animal a : animals) {
                    speciesCount.put(a.getSpecies(), speciesCount.getOrDefault(a.getSpecies(), 0) + 1);
                }
                totalPlants += loc.getPlantCount();
            }
        }
        System.out.println("=== Статистика острова ===");
        speciesCount.forEach((species, count) ->
                System.out.println(species + ": " + count));
        System.out.println("Растения (всего): " + totalPlants);
        System.out.println("==========================");
    }

    public int getTotalAnimalCount() {
        int count = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                count += locations[i][j].getAnimalsSnapshot().size();
            }
        }
        return count;
    }
}
