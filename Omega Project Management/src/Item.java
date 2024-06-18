import java.util.HashMap;
import java.util.Map;

public class Item {
	private String itemName;
    public static Map<String, Double> items = new HashMap<>();

    public Item(String itemName, double costPerUnit) {
    	this.itemName = itemName;
        addItem(itemName, costPerUnit);
    }

    private void addItem(String itemName, double costPerUnit) {
        if (itemName == null || itemName.isEmpty()) {
            throw new IllegalArgumentException("itemName cannot be null or empty");
        }
        if (costPerUnit <= 0) {
            throw new IllegalArgumentException("Cost per unit must be positive");
        }
        items.put(itemName, costPerUnit);
    }

    public void removeItem() {
        if (items.containsKey(itemName)) {
            items.remove(itemName);
        } else {
            throw new IllegalArgumentException("itemName not found");
        }
    }

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
    public double getCostPerUnit() {
        if (items.containsKey(itemName)) {
            return items.get(itemName);
        } else {
            throw new IllegalArgumentException("itemName not found");
        }
    }

}