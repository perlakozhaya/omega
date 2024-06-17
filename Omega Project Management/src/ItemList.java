import java.util.HashMap;
import java.util.Map;

public class ItemList {
    public static Map<String, Double> items = new HashMap<>();

    public ItemList(String itemName, double costPerUnit) {
        addItem(itemName, costPerUnit);
    }

    public static void addItem(String itemName, double costPerUnit) {
        if (itemName == null || itemName.isEmpty()) {
            throw new IllegalArgumentException("itemName cannot be null or empty");
        }
        if (costPerUnit <= 0) {
            throw new IllegalArgumentException("Wage per hour must be positive");
        }
        items.put(itemName, costPerUnit);
    }

    public static double getCostPerUnit(String itemName) {
        if (items.containsKey(itemName)) {
            return items.get(itemName);
        } else {
            throw new IllegalArgumentException("itemName not found");
        }
    }

    public static boolean hasItem(String itemName) {
        return items.containsKey(itemName);
    }

    public static void removeItem(String itemName) {
        if (items.containsKey(itemName)) {
            items.remove(itemName);
        } else {
            throw new IllegalArgumentException("itemName not found");
        }
    }

}