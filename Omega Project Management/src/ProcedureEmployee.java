
public class ProcedureEmployee extends ProcedureDetail {
	private Employee employee; 
	private double hours;
	
	public ProcedureEmployee(Employee employee, double hours, Procedure procedure) { 
		super(procedure); 
		this.employee = employee;
		this.hours = hours; 
	}

	@Override
	public double procedureCost() {
		return employee.getWagePerHour() * hours;
	}
}