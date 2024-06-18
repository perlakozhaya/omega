import java.util.*;

public class Project extends Status implements Comparable<Project> {
	private String projectName;
	private Set<Task> tasks;
	
	Project(String projectName, String status) {
		super(status);
		this.projectName = projectName;
		tasks = new TreeSet<Task>();
	}
	
	@Override
	public int compareTo(Project p) {
		return this.projectName.compareTo(p.projectName);
	}

	@Override
	boolean changeStatus() {
		// TODO Auto-generated method stub
		return false;
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
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}
