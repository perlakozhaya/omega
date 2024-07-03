package backend;

import java.io.Serializable;

public class ProcedureResource extends ProcedureDetail implements Serializable {
	private static final long serialVersionUID = 5707924760016289173L;
	
	private double quantity;
	private Resource resource;
	
	public ProcedureResource(Procedure procedure, Resource resource, double quantity) {
		super(procedure);
		this.resource = resource;
		this.quantity = quantity;
	}

	@Override
	public double getCost() {
		return resource.getCostPerUnit() * quantity;
	}
	
	public double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public Resource getResource() {
		return resource;
	}
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
