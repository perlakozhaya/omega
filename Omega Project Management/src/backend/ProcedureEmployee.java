package backend;
public class ProcedureEmployee extends ProcedureDetail {
  private Employee employee; 
  private double hoursWorked;
  
  public ProcedureEmployee(Procedure procedure, Employee employee, double hoursWorked) { 
    super(procedure);
    this.employee = employee;
    this.hoursWorked = hoursWorked; 
  }

  @Override
  public double getCost() {
    return employee.getSpecialty().getWagePerHour() * hoursWorked;
  }
  
  public Employee getEmployee() {
    return employee;
  }
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
  public double getHoursWorked() {
    return hoursWorked;
  }
  public void setHoursWorked(double hoursWorked) {
    this.hoursWorked = hoursWorked;
  }
}
