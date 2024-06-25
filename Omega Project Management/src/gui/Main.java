package gui;

import backend.*;

public class Main {

	public static void main(String[] args) {
    	Project p1 = new Project("project 1", null);
		Project p2 = new Project("project 2", null);
		
		Task t1 = new Task("task 1", null);
		Task t2 = new Task("task 2", null);
		Task t3 = new Task("task 3", null);
		
		Procedure pr1 = new Procedure("procedure 1", 10, null);
		Procedure pr2 = new Procedure("procedure 2", 10, null);
		Procedure pr3 = new Procedure("procedure 3", 10, null);
		Procedure pr4 = new Procedure("procedure 4", 10, null);
		
		Specialty s1 = new Specialty("Specialty 1", 12);
		Specialty s2 = new Specialty("Specialty 2", 9);
		
		Employee e1 = new Employee("Employee 1", s1);
		Employee e2 = new Employee("Employee 2", s2);
		
		t1.addProcedure(pr1);
		t1.addProcedure(pr2);
		t2.addProcedure(pr3);
		t3.addProcedure(pr4);
		
		p1.addTask(t1);
		p1.addTask(t2);
		p2.addTask(t3);
		
		Project.projects.add(p1);
		Project.projects.add(p2);
		
        new FormFrame("Form Frame");
    }
}