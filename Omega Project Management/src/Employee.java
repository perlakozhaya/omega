
public class Employee {
	private int employeeId;
	private String employeeName; 
	private String employeeJob;
	private double wagePerHour;
	
	public Employee(int employeeId, String employeeName, String employeeJob) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		
		if(JobList.hasSpecialty(employeeJob)) {
			this.employeeJob = employeeJob;
			wagePerHour = JobList.getWagePerHour(employeeJob);
		}
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
