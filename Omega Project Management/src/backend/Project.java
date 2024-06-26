package backend;
import java.util.*;

public class Project extends Status implements Comparable<Project> {
  private String projectName;
  private Set<Task> tasks;
  
  public Project(String projectName, String status) {
    super(status);
    this.projectName = projectName;
    tasks = new TreeSet<Task>();
  }

//  public double projectCost() {
//    double total = 0.0;
//    for(Task t : tasks) {
//      total += t.taskCost();      
//    }
//    return total;
//  }
//  
//  public double projectDuration() {
//    double duration = 0.0;
//    for(Task t : tasks) {
//      duration += t.taskDuration();      
//    }
//    return duration;
//  }
  
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


//  @Override
//  void updateOnStatus() {
//    // TODO Auto-generated method stub
//    
//  }
}

@SuppressWarnings("deprecation")
class ProjectObservable extends Observable {
    private Set<Project> projects = new TreeSet<>();
    
    void addTask(Project p, Task t) {
    	p.getTasks().add(t);  
    	
    	setChanged();
        notifyObservers(p);
    }
    
    void addProject(Project p) {
    	projects.add(p);
    	
    	setChanged();
        notifyObservers(p);
    }
    
    void removeTask(Project p, Task t) {
    	p.getTasks().remove(t);
    	
    	setChanged();
        notifyObservers(p);
    }
    
    void removeProject(Project p) {
    	projects.remove(p);
    	
    	setChanged();
        notifyObservers(p);
    }
    
    void setProjectName(Project p, String name) {
    	p.setProjectName(name);
    	
    	setChanged();
        notifyObservers(p);
    }
    
    
}
