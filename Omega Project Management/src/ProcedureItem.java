
public class ProcedureItem extends ProcedureDetail {
	private double quantity;
	private Item item;
	
	public ProcedureItem(Procedure procedure, Item item, double quantity) {
		super(procedure);
		this.quantity = quantity;
		this.item = item; 
	}

	public double getCostPerUnit() {
		return item.getCostPerUnit();
	}
	
	@Override
	public double getCost() {
		return getCostPerUnit() * quantity;
	}

	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
}

