import java.util.*;

public class Procedure extends Status {
	private String procedureName;
	private double duration;
//	private Set<Logistic> logistics;
	
	private Set<ProcedureItem> items;
	private Set<ProcedureEmployee> employees;
	
	public Procedure(String procedureName, double duration, String status) {
		super(status);
		this.procedureName = procedureName;
		this.duration = duration;
		
		items = new TreeSet<ProcedureItem>();
		employees = new TreeSet<ProcedureEmployee>();
	}
	
	
	//----------------------------------------------------
	//getters and setters
	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public double getduration() {
		return duration;
	}

	public void setduration(double duration) {
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

}