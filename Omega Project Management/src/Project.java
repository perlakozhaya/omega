import java.util.*;

public class Project extends Status implements Comparable<Project>{
	private String projectName;
	Set<Task> tasks;
	
	Project(String projectName, String status) {
		super(status);
		this.projectName = projectName;
		tasks = new TreeSet<Task>();
	}
	
	//-----------------------------------------------
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public int compareTo(Project p) {
		return this.projectName.compareTo(p.projectName);
	}
}
