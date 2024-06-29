package backend;

import java.util.*;

public class DataManager extends Observable {
	private Set<Project> projects;
	private Set<Employee> employees;
	
	public DataManager() {
		projects = new TreeSet<>();
		employees = new TreeSet<>();
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
        if(employees.contains(employee)) {
        	for(ProcedureEmployee pe : procedure.getEmployees()) {
        		if(pe.getEmployee().equals(employee)) {
            		pe.setHoursWorked(hoursWorked);
            		setChanged();
            		notifyObservers();
            		return true;
        		}
        	}
        	return false;
        }
        return false;
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public Set<Project> getProjects() {
	     return projects;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}
}