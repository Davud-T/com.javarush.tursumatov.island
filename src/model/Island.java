package model;

import config.Settings;
import model.animals.Animal;
import util.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Island {
    private final Location[][] locations;

    public Island() {
        int width = Settings.ISLAND_WIDTH;
        int height = Settings.ISLAND_HEIGHT;
        locations = new Location[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                locations[y][x] = new Location(x, y);
            }
        }
    }

    public Location getLocation(int x, int y) {
        if (x < 0 || x >= Settings.ISLAND_WIDTH || y < 0 || y >= Settings.ISLAND_HEIGHT) {
            return null;
        }
        return locations[y][x];
    }

    public List<Location> getAdjacentLocations(Location loc) {
        List<Location> adjacent = new ArrayList<>();
        int x = loc.getX();
        int y = loc.getY();
        for (Direction d : Direction.values()) {
            int newX = x + d.getDx();
            int newY = y + d.getDy();
            Location neighbor = getLocation(newX, newY);
            if (neighbor != null) {
                adjacent.add(neighbor);
            }
        }
        return adjacent;
    }

    public void simulationTick() {
        for (int y = 0; y < locations.length; y++) {
            for (int x = 0; x < locations[y].length; x++) {
                Location loc = locations[y][x];
                for (Animal animal : loc.getAnimals()) {
                    animal.eat();
                    animal.move(this);
                    animal.reproduce();
                    if (!animal.updateHunger()) {
                        loc.removeAnimal(animal);
                    }
                }
            }
        }
    }

    public void printAnimalCounts() {
        Map<String, Integer> counts = new HashMap<>();
        for (int y = 0; y < locations.length; y++) {
            for (int x = 0; x < locations[y].length; x++) {
                for (Animal animal : locations[y][x].getAnimals()) {
                    String emoji = animal.getEmoji();
                    counts.put(emoji, counts.getOrDefault(emoji, 0) + 1);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Animal counts: ");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("  ");
        }
        System.out.println(sb.toString());
    }

}
