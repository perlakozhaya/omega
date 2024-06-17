
public class Employee {
	private String employeeCode;
	private String employeeJob;
	private double wagePerHour;
	
	public Employee(String employeeName, String employeeJob) {
		this.employeeCode = employeeName;
		
		if(JobList.hasSpecialty(employeeJob)) {
			this.employeeJob = employeeJob;
			wagePerHour = JobList.getWagePerHour(employeeJob);
		}
	}

	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeName) {
		this.employeeCode = employeeName;
	}
	public String getEmployeeJob() {
		return employeeJob;
	}
	public void setEmployeeJob(String employeeJob) {
		this.employeeJob = employeeJob;
	}
	public double getWagePerHour() {
		return wagePerHour;
	}
	public void setWagePerHour(double wagePerHour) {
		this.wagePerHour = wagePerHour;
	}
	
//	public Set<Task> getAllTasks() {
//		
//	}
}
