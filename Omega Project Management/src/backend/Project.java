package backend;

import java.io.Serializable;
import java.util.*;

public class Project implements Comparable<Project>, Serializable {
	private static final long serialVersionUID = 4048447254018226548L;

	private String projectName;
	private Set<Task> tasks;

	public Project(String projectName) {
		this.projectName = projectName;
		tasks = new TreeSet<Task>();
	}

	public void addTask(Task task) {
		tasks.add(task);
	}

	public void removeTask(Task task) {
		tasks.remove(task);
	}

	public double projectCost() {
		double total = 0.0;
		for(Task t : tasks) {
			total += t.taskCost();      
		}
		return total;
	}

	public double currentProjectCost() {
		double total = 0.0;
		for(Task t : tasks) {
			total += t.currentTaskCost();      
		}
		return total;
	}
  
	public double projectDuration() {
		double duration = 0.0;
		for(Task t : tasks) {
			duration += t.taskDuration();      
		}
		return duration;
	}

	public double currentProjectDuration() {
		double duration = 0.0;
		for(Task t : tasks) {
			duration += t.currentTaskDuration();      
		}
		return duration;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	@Override
	public int compareTo(Project p) {
		return this.projectName.compareTo(p.projectName);
	}

	@Override
	public String toString() {
		return this.projectName;
	}
}