package backend;

public abstract class ProcedureDetail implements Comparable<ProcedureDetail> {
	protected Procedure procedure;
	
	public ProcedureDetail(Procedure procedure) {
		this.procedure = procedure;
	}
	
	public abstract double getCost();
	
	@Override
	public int compareTo(ProcedureDetail o) {
		return this.procedure.compareTo(o.procedure);
	}
}

class ProcedureEmployee extends ProcedureDetail {
	  private Employee employee; 
	  private double hoursWorked;
	  
	  public ProcedureEmployee(Procedure procedure, Employee employee, double hoursWorked) { 
	    super(procedure);
	    this.employee = employee;
	    this.hoursWorked = hoursWorked; 
	  }

	  @Override
	  public double getCost() {
	    return employee.getSpecialty().getWagePerHour() * hoursWorked;
	  }
	  
	  public Employee getEmployee() {
	    return employee;
	  }
	  public void setEmployee(Employee employee) {
	    this.employee = employee;
	  }
	  public double getHoursWorked() {
	    return hoursWorked;
	  }
	  public void setHoursWorked(double hoursWorked) {
	    this.hoursWorked = hoursWorked;
	  }
}

class ProcedureItem extends ProcedureDetail {
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

class ProcedureLogistic extends ProcedureDetail {
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
}

