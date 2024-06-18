
public class ProcedureEmployee implements Comparable<ProcedureEmployee> {
	Procedure procedure;
	private Employee employee; 
	private double hours;
	
	public ProcedureEmployee(Employee employee, double hours, Procedure procedure) { 
		this.procedure = procedure;
		this.employee = employee;
		this.hours = hours; 
	}

	public double employeeCost() {
		return employee.getSpecialty().getWagePerHour() * hours;
	}

	@Override
	public int compareTo(ProcedureEmployee pe) {
		return this.procedure.compareTo(pe.procedure);
	}
}