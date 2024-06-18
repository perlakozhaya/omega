import java.util.*;

public class Logistic {
	public static Map<String, Double> logistics = new HashMap<>();
	
	public Logistic(String logisticName, double pricePerUnit) {
		addLogistic(logisticName, pricePerUnit);
	}
	
	public static void addLogistic(String logisticName, double costPerUnit) {
		if (logisticName == null || logisticName.isEmpty()) {
            throw new IllegalArgumentException("logisticName cannot be null or empty");
        }
        if (costPerUnit <= 0) {
            throw new IllegalArgumentException("Cost per unit must be positive");
        }
        logistics.put(logisticName, costPerUnit);
	}
	
	public static boolean hasLogistic(String logisticName) {
        return logistics.containsKey(logisticName);
    }
	
	public static void removeLogistic(String logisticName) {
        if (hasLogistic(logisticName)) {
            logistics.remove(logisticName);
        } else {
            throw new IllegalArgumentException("logisticName not found");
        }
    }
	
	public static double getCostPerUnit(String logisticName) {
        if (hasLogistic(logisticName)) {
            return logistics.get(logisticName);
        } else {
            throw new IllegalArgumentException("logisticName not found");
        }
    }
}

