package backend;
import java.util.*;

public class Procedure extends Status implements Comparable<Procedure> {
	private String procedureName;
	private double duration;
	private Set<ProcedureEmployee> employees;
	private Set<ProcedureItem> items;
	private Set<ProcedureLogistic> logistics;
	
	public Procedure(String procedureName, double duration, String status) {
		super(status);
		this.procedureName = procedureName;
		this.duration = duration;
		
		employees = new HashSet<>();
		items = new HashSet<>();
		logistics = new HashSet<>();
	}
	
	public void addEmployee(Employee employee, double hoursWorked) {
		ProcedureEmployee pe = new ProcedureEmployee(this, employee, hoursWorked);
		employees.add(pe);
		setChanged();
		notifyObservers();
	}
	
	public void addItem(Item item, double quantity) {
		ProcedureItem pi = new ProcedureItem(this, item, quantity);
		items.add(pi);
		setChanged();
		notifyObservers();
	}
	
	public void addLogistic(Logistic logistic, double quantity) {
        ProcedureLogistic pl = new ProcedureLogistic(this, logistic, quantity);
        logistics.add(pl);
        setChanged();
		notifyObservers();
	}
	
	public double employeesCost() {
		double total = 0.0;
		Iterator<ProcedureEmployee> i = employees.iterator();
		while(i.hasNext()) {
			total += i.next().getCost();
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
	
	public double logisticsCost() {
		double total = 0.0;
		Iterator<ProcedureLogistic> i = logistics.iterator();
		while(i.hasNext()) {
			total += i.next().getCost();
		}
		return total;
	}
	
	public double procedureCost() {
		return employeesCost() + itemsCost() + logisticsCost();
	}
	
	@Override
	boolean changeStatus() {
		return false;
	}
	
	@Override
	public int compareTo(Procedure p) {
		return procedureName.compareTo(p.procedureName);
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
		setChanged();
		notifyObservers();
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
		setChanged();
		notifyObservers();
	}
	public Set<ProcedureEmployee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<ProcedureEmployee> employees) {
		this.employees = employees;
		setChanged();
		notifyObservers();
	}
	public Set<ProcedureItem> getItems() {
		return items;
	}
	public void setItems(Set<ProcedureItem> items) {
		this.items = items;
		setChanged();
		notifyObservers();
	}
	public Set<ProcedureLogistic> getLogistics() {
		return logistics;
	}
	public void setLogistics(Set<ProcedureLogistic> logistics) {
		this.logistics = logistics;
		setChanged();
		notifyObservers();
	}
}