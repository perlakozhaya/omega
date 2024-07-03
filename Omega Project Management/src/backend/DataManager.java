package backend;

import java.io.*;
import java.util.*;

public class DataManager extends Observable implements Serializable {
    private static final long serialVersionUID = 1L;

	private Set<Project> projects;
	private Set<Employee> employees;
	private Set<Specialty> specialties;
	private Set<Item> items;
    private Set<Logistic> logistics;

	public DataManager() {
		projects = new TreeSet<>();
		employees = new TreeSet<>();
		specialties = new TreeSet<>();
		items = new TreeSet<>();
		logistics = new TreeSet<>();
	}
	
	public void addProject(Project project) {
	     projects.add(project);
	     setChanged();
	     notifyObservers();
	}
	
	public void updateProject(Project project, String name) {
		if(projects.contains(project)) {
			project.setProjectName(name);
			setChanged();
			notifyObservers();
		}
	}
	
	public void removeProject(Project project) {
	     projects.remove(project);
	     setChanged();
	     notifyObservers();
	}
	
	public void addTaskToProject(Project project, Task task) {
		if (projects.contains(project)) {
            project.addTask(task);
            setChanged();
            notifyObservers();
        }
	}
	
	public void updateTaskInProject(Project project, Task task, String name) {
		if (projects.contains(project)) {
            Set<Task> tasks = project.getTasks();
            if (tasks.contains(task)) {
            	task.setTaskName(name);
                setChanged();
                notifyObservers();
            }
        }
	}
	
	public void removeTaskFromProject(Project project, Task task) {
		if (projects.contains(project)) {
            project.removeTask(task);
            setChanged();
            notifyObservers();
        }
	}
	
	public void addProcedureToTask(Task task, Procedure procedure) {
	     task.addProcedure(procedure);
	     setChanged();
	     notifyObservers();
	}
	
	public void updateProcedureInTask(Task task, Procedure procedure, String name, Double duration) {
        Set<Procedure> procedures = task.getProcedures();
        if (procedures.contains(procedure)) {
        	procedure.setProcedureName(name);
        	procedure.setProcedureDuration(duration);
            setChanged();
            notifyObservers();
        }
	}
	
	public void removeProcedureFromTask(Task task, Procedure procedure) {
	     task.removeProcedure(procedure);
	     setChanged();
	     notifyObservers();
	}
	
	public void addEmployeeToProcedure(Procedure procedure, Employee employee, double hoursWorked) {
		procedure.addEmployee(employee, hoursWorked);
		setChanged();
		notifyObservers();
	}
	
	public boolean updateEmployeeInProcedure(Procedure procedure, Employee employee, double hoursWorked) {
	    if (!employees.contains(employee)) {
	        return false;
	    }
	    for (ProcedureEmployee pe : procedure.getEmployees()) {
	        if (pe.getEmployee().equals(employee)) {
	            pe.setHoursWorked(hoursWorked);
	            setChanged();
	            notifyObservers();
	            return true;
	        }
	    }
	    return false;
	}

	public boolean addEmployee(Employee employee) {
		if(!employees.contains(employee)) {
			employees.add(employee);
			setChanged();
			notifyObservers();	
			return true;
		}
		return false;
	}
	
	public boolean addSpecialty(Specialty specialty) {
		if(!specialties.contains(specialty)) {
			specialties.add(specialty);
			setChanged();
			notifyObservers();	
			return true;
		}
		return false;
    }
	
	public boolean addItem(Item item) {
		if(!items.contains(item)) {
			items.add(item);
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}
	
	public void addItemToProcedure(Procedure procedure, Item item, double quantity) {
		procedure.addItem(item, quantity);
		setChanged();
		notifyObservers();
	}
	
	public boolean updateItemInProcedure(Procedure procedure, Item item, double quantity) {
		if(!items.contains(item)) {
			return false;
		}
		for(ProcedureItem pi : procedure.getItems()) {
			if(pi.getItem().equals(item)) {
				pi.setQuantity(quantity);
				setChanged();
				notifyObservers();
				return true;
			}
		}
		return false;
	}
	
	public void addLogistic(Logistic logistic) {
		logistics.add(logistic);
		setChanged();
		notifyObservers();
	}
	
	public void addLogisticToProcedure(Procedure procedure, Logistic logistic, double quantity) {
		procedure.addLogistic(logistic, quantity);
		setChanged();
		notifyObservers();
	}
	
	public boolean updateLogisticInProcedure(Procedure procedure, Logistic logistic, double quantity) {
        if(!logistics.contains(logistic)) {
        	return false;
        }
    	for(ProcedureLogistic pl : procedure.getLogistics()) {
    		if(pl.getLogistic().equals(logistic)) {
        		pl.setQuantity(quantity);
        		setChanged();
        		notifyObservers();
        		return true;
    		}
    	}
    	return false;
	}
	
	public String changeStatus(Procedure procedure) {
	    final String[] statusOrder = {"Execute", "Ongoing", "Done"};
	    String currentStatus = procedure.getStatus();
	    
	    for (int i = 0; i < statusOrder.length - 1; i++) {
	        if (statusOrder[i].equals(currentStatus)) {
	            procedure.setStatus(statusOrder[i + 1]);
	            setChanged();
	            notifyObservers();
	            return statusOrder[i + 1];
	        } 
	    }
	    return currentStatus;
	}
	
	public void saveDataToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
	
	public static DataManager loadDataFromFile(String fileName) {
        DataManager dataManager = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            dataManager = (DataManager) ois.readObject();
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error loading data: " + e.getMessage());
        }
        return dataManager;
    }
	
	public Set<Project> getProjects() {
	     return projects;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}
	
	public Set<Specialty> getSpecialties() {
		return specialties;
	}
	
	public Set<Item> getItems() {
		return items;
	}
	
	public Set<Logistic> getLogistics() {
		return logistics;
	}
}