package backend;

public class ProcedureLogistic extends ProcedureDetail {
	private double quantity;
	private Logistic logistic;
	
	public ProcedureLogistic(Procedure procedure, Logistic logistic, double quantity) {
		super(procedure);
		this.logistic = logistic;
		this.quantity = quantity;
	}

	@Override
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
}
