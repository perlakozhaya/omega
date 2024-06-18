
public class ProcedureItem implements Comparable<ProcedureItem> {
	private Procedure procedure;
	private String itemName;
	private double costPerUnit;
	private double quantity;
	
	public ProcedureItem(String itemName, double quantity, Procedure procedure) {
		this.procedure = procedure;
		this.quantity = quantity;
		
		if(ItemList.hasItem(itemName)) {
			this.itemName = itemName; 
			costPerUnit = ItemList.getCostPerUnit(itemName);
		}
	}

	public double procedureCost() {
		return costPerUnit * quantity;
	}

	public String getItemName() {
		return itemName;
	}
	
	public double getCostPerUnit() {
		return costPerUnit;
	}
	
	public double getQuantity() {
		return quantity;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public void setCostPerUnit(double costPerUnit) {
		this.costPerUnit = costPerUnit;
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

	@Override
	public int compareTo(ProcedureItem pi) {
		return this.procedure.compareTo(pi.procedure);
	}
}

