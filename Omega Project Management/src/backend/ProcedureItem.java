package backend;

import java.io.Serializable;

public class ProcedureItem implements Comparable<ProcedureItem>, Serializable {
	private static final long serialVersionUID = -4574400223855581756L;
	
	private Procedure procedure;
	private double quantity;
	private Item item;
	
	public ProcedureItem(Procedure procedure, Item item, double quantity) {
		this.procedure = procedure;
		this.quantity = quantity;
		this.item = item; 
	}

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
	
	@Override
	public int compareTo(ProcedureItem o) {
		return this.procedure.compareTo(o.procedure);
	}
	
	public Procedure getProcedure() {
		return procedure;
	}
}
