
public class Employee {
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
}
