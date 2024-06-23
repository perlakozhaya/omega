package backend;
import java.util.HashSet;
import java.util.Set;

public class Employee {
	private String employeeCode;
	private Specialty specialty;
	private Set<ProcedureEmployee> procedures;
	
	public Employee(String employeeName, Specialty specialty) {
		this.employeeCode = employeeName;
		this.specialty = specialty;
		
		procedures = new HashSet<>();
	}
	
	public void addProcedure(Procedure procedure, double hoursWorked) {
        ProcedureEmployee pe = new ProcedureEmployee(procedure, this, hoursWorked);
        procedures.add(pe);
	}

	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeName) {
		this.employeeCode = employeeName;
	}
	public Specialty getSpecialty() {
		return specialty;
	}
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
	public Set<ProcedureEmployee> getProcedures() {
		return procedures;
	}
	public void setProcedures(Set<ProcedureEmployee> procedures) {
		this.procedures = procedures;
	}
}
