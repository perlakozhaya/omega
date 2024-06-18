import java.util.HashMap;
import java.util.Map;

public class Specialty {
	String name;
    public static Map<String, Double> jobs = new HashMap<>();

    public Specialty(String name, double wagePerHour) {
    	this.name = name;
        addJob(name, wagePerHour);
    }

    public static void addJob(String name, double wagePerHour) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (wagePerHour <= 0) {
            throw new IllegalArgumentException("Wage per hour must be positive");
        }
        jobs.put(name, wagePerHour);
    }

    public static void removeJob(String name) {
        if (jobs.containsKey(name)) {
            jobs.remove(name);
        } else {
            throw new IllegalArgumentException("name not found");
        }
    }
    
    public double getWagePerHour() {
        if (jobs.containsKey(name)) {
            return jobs.get(name);
        } else {
            throw new IllegalArgumentException("name not found");
        }
    }

    public boolean hasname() {
        return jobs.containsKey(name);
    }
    
}