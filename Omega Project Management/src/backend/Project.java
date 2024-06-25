package backend;
import java.util.*;

public class Project extends Status implements Comparable<Project> {
  private String projectName;
  private Set<Task> tasks;
    public static Set<Project> projects = new TreeSet<>();
  
  public Project(String projectName, String status) {
    super(status);
    this.projectName = projectName;
    tasks = new TreeSet<Task>();
    projects.add(this);
  }
  
  public void addTask(Task t) {
    tasks.add(t);
  }
  
  public double projectCost() {
    double total = 0.0;
    for(Task t : tasks) {
      total += t.taskCost();      
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
  
  @Override
  public int compareTo(Project p) {
    return this.projectName.compareTo(p.projectName);
  }
  
  @Override
  public String toString() {
    return this.projectName;
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

  @Override
  void updateOnStatus() {
    // TODO Auto-generated method stub
    
  }
}
