package backend;

import java.util.*;

public class DataManager extends Observable {
	private Set<Project> projects;
	
	public DataManager() {
		projects = new HashSet<>();
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
	
	public void updateTaskInProject(Project project, Task oldTask, Task newTask) {
		if (projects.contains(project)) {
            Set<Task> tasks = project.getTasks();
            if (tasks.contains(oldTask)) {
            	project.removeTask(oldTask);
            	project.addTask(newTask);
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
	
	public void updateProcedureInTask(Task task, Procedure oldProcedure, Procedure newProcedure) {
        Set<Procedure> procedures = task.getProcedures();
        if (procedures.contains(oldProcedure)) {
        	task.removeProcedure(oldProcedure);
        	task.addProcedure(newProcedure);
            setChanged();
            notifyObservers();
        }
	}
	
	public void removeProcedureFromTask(Task task, Procedure procedure) {
	     task.removeProcedure(procedure);
	     setChanged();
	     notifyObservers();
	}
	
	public Set<Project> getProjects() {
	     return projects;
	}
}