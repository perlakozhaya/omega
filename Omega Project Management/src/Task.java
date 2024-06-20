import java.util.Set;
import java.util.TreeSet;

public class Task extends Status implements Comparable<Task>{
	private String taskName;
	Set<Procedure> procedures;
	
	public Task(String taskName, String status) {
		super(status);
		this.taskName = taskName;
		procedures = new TreeSet<Procedure>();
	}
	
	//---------------------------------------------
	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	
	@Override
    public int compareTo(Task other) {
        return this.taskName.compareTo(other.taskName);
    }

}