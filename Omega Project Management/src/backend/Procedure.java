package backend;

import java.io.Serializable;
import java.util.*;

public class Procedure implements Comparable<Procedure>, Serializable {
	private static final long serialVersionUID = 6264653076488117932L;
	
	private String procedureName;
	private double procedureDuration;
	private String status = "Execute";
	private Set<ProcedureEmployee> employees;
	private Set<ProcedureItem> items;
	private Set<ProcedureResource> resources;
	  	public Procedure(String procedureName, double procedureDuration) {
		this.procedureName = procedureName;
	    this.procedureDuration = procedureDuration;
	    employees = new HashSet<>();
	    items = new HashSet<>();
	    resources = new HashSet<>();	}
  
	public void addEmployee(Employee employee, double hoursWorked) {
		ProcedureEmployee pe = new ProcedureEmployee(this, employee, hoursWorked);
		employees.add(pe);
	}
  
	public void addItem(Item item, double quantity) {
		ProcedureItem pi = new ProcedureItem(this, item, quantity);
		items.add(pi);
	}
  
	public void addResource(Resource resource, double quantity) {
		ProcedureResource pl = new ProcedureResource(this, resource, quantity);
		resources.add(pl);
	}
  
	public double employeesCost() {
		double total = 0.0;
		Iterator<ProcedureEmployee> i = employees.iterator();
		while(i.hasNext()) {
			total += i.next().getCost();
		}
		return total;
	}
  
	public double currentEmployeesCost() {
		double total = 0.0;
		Iterator<ProcedureEmployee> i = employees.iterator();
		while(i.hasNext()) {
			ProcedureEmployee pe = i.next();
			if(pe.procedure.status != "Execute") {
				total += pe.getCost();			  
			}
		}
		return total;
	}
  
	public double itemsCost() {
		double total = 0.0;
		Iterator<ProcedureItem> i = items.iterator();
		while(i.hasNext()) {
			total += i.next().getCost();			  
		}
		return total;
	}

	public double currentItemsCost() {
		double total = 0.0;
		Iterator<ProcedureItem> i = items.iterator();
		while(i.hasNext()) {
			ProcedureItem pi = i.next();
			if(pi.procedure.status != "Execute") {
				total += pi.getCost();			  
			}
		}
		return total;
	}
  
	public double resourcesCost() {
		double total = 0.0;
		Iterator<ProcedureResource> i = resources.iterator();
		while(i.hasNext()) {
			total += i.next().getCost();
		}
		return total;
	}

	public double currentResourcesCost() {
		double total = 0.0;
		Iterator<ProcedureResource> i = resources.iterator();
		while(i.hasNext()) {
			ProcedureResource pr = i.next();
			if(pr.procedure.status != "Execute") {
				total += pr.getCost();			  
			}
		}
		return total;
	}
  
	public double procedureCost() {
		return employeesCost() + itemsCost() + resourcesCost();
	}
  
	public double currentProcedureCost() {
		return currentEmployeesCost() + currentItemsCost() + currentResourcesCost();
	}
  
	public String getProcedureName() {
		return procedureName;
	}
	
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	
	public double getProcedureDuration() {
		return procedureDuration;
	}
	
	public void setProcedureDuration(double procedureDuration) {
		this.procedureDuration = procedureDuration;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Set<ProcedureEmployee> getEmployees() {
		return employees;
	}

	public Set<ProcedureItem> getItems() {
		return items;
	}

	public Set<ProcedureResource> getResources() {
		return resources;
	}

	@Override
	public int compareTo(Procedure p) {
		return this.procedureName.compareTo(p.procedureName);
	}
	
	@Override
	public String toString() {
		return this.procedureName;
	}
}
