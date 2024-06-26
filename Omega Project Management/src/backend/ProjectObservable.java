package backend;

import java.util.Observable;
import java.util.Set;
import java.util.TreeSet;

public class ProjectObservable extends Observable {
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
    
    public Set<Project> getProjects() {
        return projects;
    }
}