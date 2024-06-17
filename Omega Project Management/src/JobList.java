import java.util.HashMap;
import java.util.Map;

public class JobList {
    public static Map<String, Double> jobs = new HashMap<>();

    public JobList(String specialty, double wagePerHour) {
        addJob(specialty, wagePerHour);
    }

    public static void addJob(String specialty, double wagePerHour) {
        if (specialty == null || specialty.isEmpty()) {
            throw new IllegalArgumentException("Specialty cannot be null or empty");
        }
        if (wagePerHour <= 0) {
            throw new IllegalArgumentException("Wage per hour must be positive");
        }
        jobs.put(specialty, wagePerHour);
    }

    public static double getWagePerHour(String specialty) {
        if (jobs.containsKey(specialty)) {
            return jobs.get(specialty);
        } else {
            throw new IllegalArgumentException("Specialty not found");
        }
    }

    public static boolean hasSpecialty(String specialty) {
        return jobs.containsKey(specialty);
    }

    public static void removeJob(String specialty) {
        if (jobs.containsKey(specialty)) {
            jobs.remove(specialty);
        } else {
            throw new IllegalArgumentException("Specialty not found");
        }
    }
    
}