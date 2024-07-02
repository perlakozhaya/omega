package backend;

import java.io.Serializable;

public class Employee implements Comparable<Employee>, Serializable {
	private String employeeCode;
	private Specialty specialty;
	
	public Employee(String employeeName, Specialty specialty) {
		this.employeeCode = employeeName;
		this.specialty = specialty;
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

	@Override
	public int compareTo(Employee o) {
		return this.employeeCode.compareTo(o.employeeCode);
	}
	
	@Override
	public String toString() {
		return employeeCode;
	}
}
