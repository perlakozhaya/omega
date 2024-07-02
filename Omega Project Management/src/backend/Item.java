package backend;

import java.io.Serializable;

public class Item implements Comparable<Item>, Serializable {
	private String itemName;
	private double costPerUnit;

    public Item(String itemName, double costPerUnit) {
    	this.itemName = itemName;
    	this.costPerUnit = costPerUnit;
    }

	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
    public double getCostPerUnit() {
        return costPerUnit;
    }
    
    public void setCostPerUnit(double costPerUnit) {
    	this.costPerUnit = costPerUnit;
    }

	@Override
	public int compareTo(Item i) {
		return this.itemName.compareTo(i.itemName);
	}
	
	@Override
	public String toString() {
		return itemName;
	}
}