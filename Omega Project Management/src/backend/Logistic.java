package backend;

public class Logistic implements Comparable<Logistic> {
	private String logisticName;
	private double costPerUnit;
	
	public Logistic(String logisticName, double costPerUnit) {
		this.logisticName = logisticName;
		this.costPerUnit = costPerUnit;
	}
	
	public String getLogisticName() {
		return logisticName;
	}
	
	public void setLogisticName(String logisticName) {
		this.logisticName = logisticName;
	}
	
    public double getCostPerUnit() {
        return costPerUnit;
    }
    
    public void setCostPerUnit(double costPerUnit) {
    	this.costPerUnit = costPerUnit;
    }

	@Override
	public int compareTo(Logistic l) {
		return this.logisticName.compareTo(l.logisticName);
	}
	
	@Override
	public String toString() {
		return logisticName;
	}
}