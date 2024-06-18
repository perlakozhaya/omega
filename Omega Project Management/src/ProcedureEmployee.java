
public class ProcedureEmployee{
	Procedure procedure;
	private Employee employee; 
	private double hours;
	
	public ProcedureEmployee(Employee employee, double hours, Procedure procedure) { 
		this.procedure = procedure;
		this.employee = employee;
		this.hours = hours; 
	}

	public double procedureCost() {
		return employee.getSpecialty().getWagePerHour() * hours;
	}
}