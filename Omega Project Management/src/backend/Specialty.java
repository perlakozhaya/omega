package backend;

import java.io.Serializable;

public class Specialty implements Comparable<Specialty>, Serializable {
	private String specialtyName;
	private double wagePerHour;
	
	public Specialty(String specialtyName, double wagePerHour) {
		this.specialtyName = specialtyName;
		this.wagePerHour = wagePerHour;
	}
	
	public String getSpecialtyName() {
		return specialtyName;
	}
	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}
	public double getWagePerHour() {
		return wagePerHour;
	}
	public void setWagePerHour(double wagePerHour) {
		this.wagePerHour = wagePerHour;
	}

	@Override
	public int compareTo(Specialty s) {
		return this.specialtyName.compareTo(s.specialtyName);
	}
	
	@Override
	public String toString() {
		return specialtyName;
	}
}