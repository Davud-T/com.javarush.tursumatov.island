package util;

import model.animals.herbivores.*;
import model.animals.predators.*;

import java.util.HashMap;
import java.util.Map;

public class FoodChain {
    private static final Map<Class<?>, Map<Class<?>, Integer>> foodProbabilities = new HashMap<>();

    static {
        Map<Class<?>, Integer> wolfDiet = new HashMap<>();
        wolfDiet.put(Rabbit.class, 60);
        wolfDiet.put(Horse.class, 10);
        wolfDiet.put(Deer.class, 15);
        wolfDiet.put(Mouse.class, 80);
        wolfDiet.put(Goat.class, 60);
        wolfDiet.put(Sheep.class, 70);
        wolfDiet.put(Boar.class, 15);
        wolfDiet.put(Buffalo.class, 10);
        wolfDiet.put(Duck.class, 40);
        wolfDiet.put(Caterpillar.class, 0);
        foodProbabilities.put(Wolf.class, wolfDiet);

        Map<Class<?>, Integer> boaDiet = new HashMap<>();
        boaDiet.put(Rabbit.class, 20);
        boaDiet.put(Horse.class, 5);
        boaDiet.put(Deer.class, 5);
        boaDiet.put(Mouse.class, 40);
        boaDiet.put(Goat.class, 20);
        boaDiet.put(Sheep.class, 20);
        boaDiet.put(Boar.class, 10);
        boaDiet.put(Buffalo.class, 5);
        boaDiet.put(Duck.class, 30);
        boaDiet.put(Caterpillar.class, 50);
        foodProbabilities.put(Boa.class, boaDiet);

        Map<Class<?>, Integer> foxDiet = new HashMap<>();
        foxDiet.put(Rabbit.class, 70);
        foxDiet.put(Horse.class, 5);
        foxDiet.put(Deer.class, 5);
        foxDiet.put(Mouse.class, 90);
        foxDiet.put(Goat.class, 0);
        foxDiet.put(Sheep.class, 0);
        foxDiet.put(Boar.class, 0);
        foxDiet.put(Buffalo.class, 0);
        foxDiet.put(Duck.class, 50);
        foxDiet.put(Caterpillar.class, 40);
        foodProbabilities.put(Fox.class, foxDiet);

        Map<Class<?>, Integer> bearDiet = new HashMap<>();
        bearDiet.put(Rabbit.class, 80);
        bearDiet.put(Horse.class, 40);
        bearDiet.put(Deer.class, 80);
        bearDiet.put(Mouse.class, 90);
        bearDiet.put(Goat.class, 70);
        bearDiet.put(Sheep.class, 70);
        bearDiet.put(Boar.class, 50);
        bearDiet.put(Buffalo.class, 20);
        bearDiet.put(Duck.class, 10);
        bearDiet.put(Caterpillar.class, 0);
        foodProbabilities.put(Bear.class, bearDiet);

        Map<Class<?>, Integer> eagleDiet = new HashMap<>();
        eagleDiet.put(Rabbit.class, 90);
        eagleDiet.put(Horse.class, 0);
        eagleDiet.put(Deer.class, 0);
        eagleDiet.put(Mouse.class, 90);
        eagleDiet.put(Goat.class, 0);
        eagleDiet.put(Sheep.class, 0);
        eagleDiet.put(Boar.class, 0);
        eagleDiet.put(Buffalo.class, 0);
        eagleDiet.put(Duck.class, 80);
        eagleDiet.put(Caterpillar.class, 100);
        foodProbabilities.put(Eagle.class, eagleDiet);
    }

    public static int getProbability(Class<?> predator, Class<?> prey) {
        Map<Class<?>, Integer> predatorMap = foodProbabilities.get(predator);
        if (predatorMap != null) {
            for (Map.Entry<Class<?>, Integer> entry : predatorMap.entrySet()) {
                if (entry.getKey().isAssignableFrom(prey)) {
                    return entry.getValue();
                }
            }
        }
        return 0;
    }
}
