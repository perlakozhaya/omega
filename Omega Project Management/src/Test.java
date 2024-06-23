public class Test {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {	
		Specialty s1 = new Specialty("s1", 10);
		Specialty s2 = new Specialty("s2", 20);
		
		ItemList.addItem("item1", 1.1);
		ItemList.addItem("item2", 2.2);
		
		Employee emp1 = new Employee("emp_1", s1);
		Employee emp2 = new Employee("emp_2", s2);
		
		Procedure p1 = new Procedure("p1", 10, "DONE");
		Procedure p2 = new Procedure("p2", 10, "RUNNING");
		
		ProcedureEmployee pe1 = new ProcedureEmployee(p1, emp1, 6);
		ProcedureEmployee pe2 = new ProcedureEmployee(p1, emp2, 4);
		p1.getEmployees().add(pe1);
		p1.getEmployees().add(pe2);
		ProcedureEmployee pe3 = new ProcedureEmployee(p2, emp1, 4);
		
		ProcedureItem pi1 = new ProcedureItem("item1", 6.0, p1);
		ProcedureItem pi2 = new ProcedureItem("item2", 4.0, p1);

		System.out.println(p1.employeesCost());
	}

}
