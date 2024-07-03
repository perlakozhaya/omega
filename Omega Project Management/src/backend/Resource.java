package backend;

import java.io.Serializable;

public class Resource implements Comparable<Resource>, Serializable {
	private static final long serialVersionUID = 1782385636058491171L;
	
	private String resourceName;
	private double costPerUnit;
	
	public Resource(String resourceName, double costPerUnit) {
		this.resourceName = resourceName;
		this.costPerUnit = costPerUnit;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
    public double getCostPerUnit() {
        return costPerUnit;
    }
    
    public void setCostPerUnit(double costPerUnit) {
    	this.costPerUnit = costPerUnit;
    }

	@Override
	public int compareTo(Resource r) {
		return this.resourceName.compareTo(r.resourceName);
	}
	
	@Override
	public String toString() {
		return resourceName;
	}
}