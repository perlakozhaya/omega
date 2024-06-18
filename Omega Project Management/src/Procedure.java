import java.util.*;

public class Procedure extends Status implements Comparable<Procedure> {
	private String procedureName;
	private double duration;
	
	private Set<Logistic> logistics;
	private Set<ProcedureItem> items;
	private Set<ProcedureEmployee> employees;
	
	public Procedure(String procedureName, double duration, String status) {
		super(status);
		this.procedureName = procedureName;
		this.duration = duration;
		
		employees = new TreeSet<ProcedureEmployee>();
		items = new TreeSet<ProcedureItem>();
	}
	
	public double employeesCost() {
		double total = 0.0;
		Iterator<ProcedureEmployee> i = employees.iterator();
		while(i.hasNext()) {
			total += i.next().employeeCost();
		}
		return total;
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

	public Set<ProcedureItem> getItems() {
		return items;
	}

	public void setItems(Set<ProcedureItem> items) {
		this.items = items;
	}

	public Set<ProcedureEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<ProcedureEmployee> employees) {
		this.employees = employees;
	}
	
	public Set<Logistic> getLogistics() {
		return logistics;
	}

	public void setLogistics(Set<Logistic> logistics) {
		this.logistics = logistics;
	}

	@Override
	public int compareTo(Procedure p) {
		return procedureName.compareTo(p.procedureName);
	}

}