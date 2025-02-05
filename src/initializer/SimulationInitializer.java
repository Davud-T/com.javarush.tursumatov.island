package initializer;

import model.Island;
import model.Location;
import model.animals.Animal;
import config.Settings;
import model.animals.herbivores.*;
import model.animals.predators.*;

import java.util.Random;

public class SimulationInitializer {

    private static void addAnimals(Island island, int count, Class<? extends Animal> clazz) {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int x = rand.nextInt(island.getWidth());
            int y = rand.nextInt(island.getHeight());
            Location loc = island.getLocation(x, y);
            try {
                Animal animal = clazz.getDeclaredConstructor(Location.class).newInstance(loc);
                loc.addAnimal(animal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void initialize(Island island) {
        addAnimals(island, Settings.INITIAL_WOLF_COUNT, Wolf.class);
        addAnimals(island, Settings.INITIAL_BOA_COUNT, Boa.class);
        addAnimals(island, Settings.INITIAL_FOX_COUNT, Fox.class);
        addAnimals(island, Settings.INITIAL_BEAR_COUNT, Bear.class);
        addAnimals(island, Settings.INITIAL_EAGLE_COUNT, Eagle.class);
        addAnimals(island, Settings.INITIAL_HORSE_COUNT, Horse.class);
        addAnimals(island, Settings.INITIAL_DEER_COUNT, Deer.class);
        addAnimals(island, Settings.INITIAL_RABBIT_COUNT, Rabbit.class);
        addAnimals(island, Settings.INITIAL_MOUSE_COUNT, Mouse.class);
        addAnimals(island, Settings.INITIAL_GOAT_COUNT, Goat.class);
        addAnimals(island, Settings.INITIAL_SHEEP_COUNT, Sheep.class);
        addAnimals(island, Settings.INITIAL_WILD_BOAR_COUNT, WildBoar.class);
        addAnimals(island, Settings.INITIAL_BUFFALO_COUNT, Buffalo.class);
        addAnimals(island, Settings.INITIAL_DUCK_COUNT, Duck.class);
        addAnimals(island, Settings.INITIAL_CATERPILLAR_COUNT, Caterpillar.class);
    }
}
