
public class ProcedureEmployee implements Comparable<ProcedureEmployee> {
	private Procedure procedure;
	private Employee employee; 
	private double hoursWorked;
	
	public ProcedureEmployee(Procedure procedure, Employee employee, double hoursWorked) { 
		this.procedure = procedure;
		this.employee = employee;
		this.hoursWorked = hoursWorked; 
	}

	public double employeeCost() {
		return employee.getSpecialty().getWagePerHour() * hoursWorked;
	}

	@Override
	public int compareTo(ProcedureEmployee pe) {
		return this.procedure.compareTo(pe.procedure);
	}
	
	public Procedure getProcedure() {
		return procedure;
	}
	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
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