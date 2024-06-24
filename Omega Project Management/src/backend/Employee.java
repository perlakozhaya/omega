package backend;

import java.util.*;

public class Employee implements Comparable<Employee> {
	private String employeeCode;
	private Specialty specialty;
	private Set<ProcedureEmployee> procedures;
	public static Set<Employee> allEmployees = new TreeSet<Employee>();
	
	public Employee(String employeeName, Specialty specialty) {
		this.employeeCode = employeeName;
		this.specialty = specialty;
		procedures = new HashSet<>();
		allEmployees.add(this);
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

	@Override
	public int compareTo(Employee o) {
		return this.employeeCode.compareTo(o.employeeCode);
	}
	
	@Override
	public String toString() {
		return employeeCode;
	}
}
