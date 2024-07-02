package backend;
import java.io.Serializable;
import java.util.*;

public class Procedure implements Comparable<Procedure>, Serializable {
  private String procedureName;
  private double procedureDuration;
  private String status = "Execute";
  private Set<ProcedureEmployee> employees;
  private Set<ProcedureItem> items;
  private Set<ProcedureLogistic> logistics;
  
  public Procedure(String procedureName, double procedureDuration) {
    this.procedureName = procedureName;
    this.procedureDuration = procedureDuration;
    employees = new HashSet<>();
    items = new HashSet<>();
    logistics = new HashSet<>();
  }
  
  public void addEmployee(Employee employee, double hoursWorked) {
    ProcedureEmployee pe = new ProcedureEmployee(this, employee, hoursWorked);
    employees.add(pe);
  }
  
  public void addItem(Item item, double quantity) {
    ProcedureItem pi = new ProcedureItem(this, item, quantity);
    items.add(pi);
  }
  
  public void addLogistic(Logistic logistic, double quantity) {
        ProcedureLogistic pl = new ProcedureLogistic(this, logistic, quantity);
        logistics.add(pl);
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
  
  public double logisticsCost() {
    double total = 0.0;
    Iterator<ProcedureLogistic> i = logistics.iterator();
    while(i.hasNext()) {
      total += i.next().getCost();
    }
    return total;
  }

  public double currentLogisticsCost() {
	  double total = 0.0;
	  Iterator<ProcedureLogistic> i = logistics.iterator();
	  while(i.hasNext()) {
		  ProcedureLogistic pl = i.next();
		  
		  if(pl.procedure.status != "Execute") {
			  total += pl.getCost();			  
		  }
	  }
	  return total;
  }
  
  public double procedureCost() {
    return employeesCost() + itemsCost() + logisticsCost();
  }
  
  public double currentProcedureCost() {
	  return currentEmployeesCost() + currentItemsCost() + currentLogisticsCost();
  }
  
  @Override
  public String toString() {
    return this.procedureName;
  }
  
  // Getters & Setters
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
  public Set<ProcedureEmployee> getEmployees() {
    return employees;
  }
  public void setEmployees(Set<ProcedureEmployee> employees) {
    this.employees = employees;
  }
  public Set<ProcedureItem> getItems() {
    return items;
  }
  public void setItems(Set<ProcedureItem> items) {
    this.items = items;
  }
  public Set<ProcedureLogistic> getLogistics() {
    return logistics;
  }
  public void setLogistics(Set<ProcedureLogistic> logistics) {
    this.logistics = logistics;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
	this.status = status;
  }
  
  @Override
  public int compareTo(Procedure p) {
	return this.procedureName.compareTo(p.procedureName);
  }
}
