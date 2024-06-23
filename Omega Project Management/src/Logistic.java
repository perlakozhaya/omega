import java.util.*;

public class Logistic {
	private String logisticName;
	public static Map<String, Double> logistics = new HashMap<>();
	
	public Logistic(String logisticName, double pricePerUnit) {
		this.logisticName = logisticName;
		addLogistic(logisticName, pricePerUnit);
	}
	
	public void addLogistic(String logisticName, double costPerUnit) {
		if (logisticName == null || logisticName.isEmpty()) {
            throw new IllegalArgumentException("logisticName cannot be null or empty");
        }
        if (costPerUnit <= 0) {
            throw new IllegalArgumentException("Cost per unit must be positive");
        }
        logistics.put(logisticName, costPerUnit);
	}
	
	public void removeLogistic() {
        if (logistics.containsKey(logisticName)) {
            logistics.remove(logisticName);
        } else {
            throw new IllegalArgumentException("logisticName not found");
        }
    }
	
	public double getCostPerUnit() {
        if (logistics.containsKey(logisticName)) {
            return logistics.get(logisticName);
        } else {
            throw new IllegalArgumentException("logisticName not found");
        }
    }
}

