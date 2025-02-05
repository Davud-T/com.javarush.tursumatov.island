package utils;

import java.util.HashMap;
import java.util.Map;

public class FoodChain {
    // Структура: ключ – имя вида-хищника, значение – карта: ключ – цель, значение – вероятность (в %)
    private static final Map<String, Map<String, Integer>> table = new HashMap<>();

    static {
        Map<String, Integer> wolfMap = new HashMap<>();
        wolfMap.put("Horse", 10);
        wolfMap.put("Deer", 15);
        wolfMap.put("Rabbit", 60);
        wolfMap.put("Mouse", 80);
        wolfMap.put("Goat", 60);
        wolfMap.put("Sheep", 70);
        wolfMap.put("WildBoar", 15);
        wolfMap.put("Buffalo", 10);
        wolfMap.put("Duck", 40);
        table.put("Wolf", wolfMap);

        Map<String, Integer> boaMap = new HashMap<>();
        boaMap.put("Fox", 15);
        boaMap.put("Rabbit", 20);
        boaMap.put("Mouse", 40);
        boaMap.put("Duck", 10);
        table.put("Boa", boaMap);

        Map<String, Integer> foxMap = new HashMap<>();
        foxMap.put("Rabbit", 70);
        foxMap.put("Mouse", 90);
        foxMap.put("Duck", 60);
        foxMap.put("Caterpillar", 40);
        table.put("Fox", foxMap);

        Map<String, Integer> bearMap = new HashMap<>();
        bearMap.put("Boa", 80);
        bearMap.put("Deer", 80);
        bearMap.put("Rabbit", 80);
        bearMap.put("Mouse", 90);
        bearMap.put("Goat", 70);
        bearMap.put("Sheep", 70);
        bearMap.put("WildBoar", 50);
        bearMap.put("Buffalo", 20);
        bearMap.put("Duck", 10);
        table.put("Bear", bearMap);

        Map<String, Integer> eagleMap = new HashMap<>();
        eagleMap.put("Rabbit", 90);
        eagleMap.put("Mouse", 90);
        eagleMap.put("Duck", 80);
        table.put("Eagle", eagleMap);

        Map<String, Integer> horseMap = new HashMap<>();
        horseMap.put("Plants", 100);
        table.put("Horse", horseMap);

        Map<String, Integer> deerMap = new HashMap<>();
        deerMap.put("Plants", 100);
        table.put("Deer", deerMap);

        Map<String, Integer> rabbitMap = new HashMap<>();
        rabbitMap.put("Plants", 100);
        table.put("Rabbit", rabbitMap);

        Map<String, Integer> mouseMap = new HashMap<>();
        mouseMap.put("Plants", 100);
        mouseMap.put("Caterpillar", 90);
        table.put("Mouse", mouseMap);

        Map<String, Integer> goatMap = new HashMap<>();
        goatMap.put("Plants", 100);
        table.put("Goat", goatMap);

        Map<String, Integer> sheepMap = new HashMap<>();
        sheepMap.put("Plants", 100);
        table.put("Sheep", sheepMap);

        Map<String, Integer> wildBoarMap = new HashMap<>();
        wildBoarMap.put("Mouse", 50);
        wildBoarMap.put("Plants", 100);
        table.put("WildBoar", wildBoarMap);

        Map<String, Integer> buffaloMap = new HashMap<>();
        buffaloMap.put("Plants", 100);
        table.put("Buffalo", buffaloMap);

        Map<String, Integer> duckMap = new HashMap<>();
        duckMap.put("Caterpillar", 90);
        duckMap.put("Plants", 100);
        table.put("Duck", duckMap);

        Map<String, Integer> caterpillarMap = new HashMap<>();
        caterpillarMap.put("Plants", 100);
        table.put("Caterpillar", caterpillarMap);
    }

    public static int getProbability(String predator, String prey) {
        Map<String, Integer> predatorMap = table.get(predator);
        if (predatorMap == null) return 0;
        return predatorMap.getOrDefault(prey, 0);
    }
}
