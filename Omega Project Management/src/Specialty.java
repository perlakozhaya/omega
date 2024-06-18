import java.util.HashMap;
import java.util.Map;

public class Specialty {
	String specialtyName;
    public static Map<String, Double> jobs = new HashMap<>();

    public Specialty(String specialtyName, double wagePerHour) {
    	this.specialtyName = specialtyName;
        addJob(specialtyName, wagePerHour);
    }

    public static void addJob(String specialtyName, double wagePerHour) {
        if (specialtyName == null || specialtyName.isEmpty()) {
            throw new IllegalArgumentException("specialtyName cannot be null or empty");
        }
        if (wagePerHour <= 0) {
            throw new IllegalArgumentException("Wage per hour must be positive");
        }
        jobs.put(specialtyName, wagePerHour);
    }
    
    public static void removeJob(String specialtyName) {
        if (jobs.containsKey(specialtyName)) {
            jobs.remove(specialtyName);
        } else {
            throw new IllegalArgumentException("specialtyName not found");
        }
    }
    
    public double getWagePerHour() {
        if (jobs.containsKey(specialtyName)) {
            return jobs.get(specialtyName);
        } else {
            throw new IllegalArgumentException("specialtyName not found");
        }
    }   
}