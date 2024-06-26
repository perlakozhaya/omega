package backend;
import java.util.Set;
import java.util.TreeSet;

public class Task extends Status implements Comparable<Task> {
  private String taskName;
  private Set<Procedure> procedures;
  
  public Task(String taskName, String status) {
    super(status);
    this.taskName = taskName;
    procedures = new TreeSet<Procedure>();
  }
  
  public void addProcedure(Procedure p) {
    procedures.add(p);
  }
  
  public double taskCost() {
    double total = 0.0;
    for(Procedure p : procedures) {
      total += p.procedureCost();      
    }
    return total;
  }
  
  public double taskDuration() {
    double duration = 0.0;
    for(Procedure p : procedures) {
      duration += p.getProcedureDuration();      
    }
    return duration;
  }
  
  @Override
  public String toString() {
    return this.taskName;
  }
  
  public String getTaskName() {
    return taskName;
  }
  
  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public void setProcedures(Set<Procedure> p) {
    procedures = p;
  }
  
  public Set<Procedure> getProcedures(){
    return procedures;
  }
  
  @Override
    public int compareTo(Task t) {
        return this.taskName.compareTo(t.taskName);
    }

//  @Override
//  void updateOnStatus() {
//    // TODO Auto-generated method stub
//    
//  }
}
