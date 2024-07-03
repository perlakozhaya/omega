package backend;

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class DataManager extends Observable implements Serializable {

	private Set<Project> projects;
	private Set<Employee> employees;
	private Set<Specialty> specialties;
	private Set<Item> items;
    private Set<Resource> resources;

	public DataManager() {
		projects = new TreeSet<>();
		employees = new TreeSet<>();
		specialties = new TreeSet<>();
		items = new TreeSet<>();
		resources = new TreeSet<>();
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
	
	public void addResource(Resource resource) {
		resources.add(resource);
		setChanged();
		notifyObservers();
	}
	
	public void addResourceToProcedure(Procedure procedure, Resource resource, double quantity) {
		procedure.addResource(resource, quantity);
		setChanged();
		notifyObservers();
	}
	
	public boolean updateResourceInProcedure(Procedure procedure, Resource resource, double quantity) {
        if(!resources.contains(resource)) {
        	return false;
        }
    	for(ProcedureResource pl : procedure.getResources()) {
    		if(pl.getResource().equals(resource)) {
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
	
	public void saveDataToFile(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
	
	public static DataManager loadDataFromFile(File file) {
        DataManager dataManager = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
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
	
	public Set<Resource> getResources() {
		return resources;
	}
}