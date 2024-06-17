
public class ProcedureItem extends ProcedureDetail {
	private String itemName;
	private double costPerUnit;
	private double quantity;
	
	public ProcedureItem(String itemName, double quantity, Procedure procedure) {
		super(procedure);
		this.quantity = quantity;
		
		if(ItemList.hasItem(itemName)) {
			this.itemName = itemName; 
			costPerUnit = ItemList.getCostPerUnit(itemName);
		}
	}
	
	@Override
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
}