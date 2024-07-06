package backend;

import java.io.Serializable;

public class ProcedureLogistic implements Comparable<ProcedureLogistic>, Serializable {
	private static final long serialVersionUID = 5707924760016289173L;
	
	private Procedure procedure;
	private double quantity;
	private Logistic logistic;
	
	public ProcedureLogistic(Procedure procedure, Logistic logistic, double quantity) {
		this.procedure = procedure;
		this.logistic = logistic;
		this.quantity = quantity;
	}

	public double getCost() {
		return logistic.getCostPerUnit() * quantity;
	}
	
	public double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public Logistic getLogistic() {
		return logistic;
	}
	
	public void setLogistic(Logistic logistic) {
		this.logistic = logistic;
	}
	
	@Override
	public int compareTo(ProcedureLogistic o) {
		return this.procedure.compareTo(o.procedure);
	}
	
	public Procedure getProcedure() {
		return procedure;
	}
}
