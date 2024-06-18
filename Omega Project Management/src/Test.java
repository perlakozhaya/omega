
public class Test {
	public static void main(String[] args) {  
		Specialty s1 = new Specialty("s1", 10);
		Specialty s2 = new Specialty("s2", 20);
					
		Employee emp1 = new Employee("emp_1", s1);
		Employee emp2 = new Employee("emp_2", s2);
					
		Procedure p1 = new Procedure("p1", 10, "DONE");
		Procedure p2 = new Procedure("p2", 10, "RUNNING");
					
		p1.addEmployee(emp1, 2);
		p1.addEmployee(emp2, 3);

		Item i1 = new Item("item1", 5);
		Item i2 = new Item("item2", 10);
		p1.addItem(i1, 4);
		p1.addItem(i2, 2);
		
		System.out.println(p1.employeesCost());
		System.out.println(p1.itemsCost());
		System.out.println(p1.procedureCost());
	}
}