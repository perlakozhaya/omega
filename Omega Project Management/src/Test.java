public class Test {
	
	public static void main(String[] args) {	
		JobList.addJob("job1", 10);
		JobList.addJob("job2", 20);
		
		ItemList.addItem("item1", 1.1);
		ItemList.addItem("item2", 2.2);
		
		Employee emp1 = new Employee("emp_1", "job1");
		Employee emp2 = new Employee("emp_2", "job2");
		
		Procedure p1 = new Procedure("p1", 10, "DONE");
		Procedure p2 = new Procedure("p2", 10, "RUNNING");
		
		ProcedureEmployee pe1 = new ProcedureEmployee(emp1, 6, p1);
		ProcedureEmployee pe2 = new ProcedureEmployee(emp1, 4, p1);
		
		ProcedureItem pi1 = new ProcedureItem("item1", 6.0, p1);
		ProcedureItem pi2 = new ProcedureItem("item2", 4.0, p1);

		System.out.println(pe2.procedureCost());
	}

}
