package backend;

import java.io.Serializable;

public class ProcedureEmployee implements Comparable<ProcedureEmployee>, Serializable {
	private static final long serialVersionUID = -6889775114948764064L;
	
	private Procedure procedure;
	private Employee employee; 
	private double hoursWorked;
	  
	public ProcedureEmployee(Procedure procedure, Employee employee, double hoursWorked) { 
	    this.procedure = procedure;
	    this.employee = employee;
	    this.hoursWorked = hoursWorked; 
	}

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
	
	@Override
	public int compareTo(ProcedureEmployee o) {
		return this.procedure.compareTo(o.procedure);
	}
	
	public Procedure getProcedure() {
		return procedure;
	}
}