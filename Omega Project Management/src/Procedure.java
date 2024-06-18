import java.util.*;

public class Procedure extends Status implements Comparable<Procedure> {
	private String procedureName;
	private double duration;
	private Set<ProcedureEmployee> employees;
//	private Set<Logistic> logistics;
//	private Set<Item> items;
	
	public Procedure(String procedureName, double duration, String status) {
		super(status);
		this.procedureName = procedureName;
		this.duration = duration;
		
		employees = new HashSet<>();
	}
	
	public void addEmployee(Employee employee, double hoursWorked) {
        ProcedureEmployee pe = new ProcedureEmployee(this, employee, hoursWorked);
        getEmployees().add(pe);
	}
	
	public double employeesCost() {
		double total = 0.0;
		Iterator<ProcedureEmployee> i = employees.iterator();
		while(i.hasNext()) {
			total += i.next().employeeCost();
		}
		return total;
	}
	
	@Override
	public int compareTo(Procedure p) {
		return procedureName.compareTo(p.procedureName);
	}
	
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public Set<ProcedureEmployee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<ProcedureEmployee> employees) {
		this.employees = employees;
	}
}