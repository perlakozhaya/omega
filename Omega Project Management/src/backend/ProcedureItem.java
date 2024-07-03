package backend;

import java.io.Serializable;

public class ProcedureItem extends ProcedureDetail implements Serializable {
	private static final long serialVersionUID = -4574400223855581756L;
	
	private double quantity;
	private Item item;
	
	public ProcedureItem(Procedure procedure, Item item, double quantity) {
		super(procedure);
		this.quantity = quantity;
		this.item = item; 
	}

	@Override
	public double getCost() {
		return item.getCostPerUnit() * quantity;
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
