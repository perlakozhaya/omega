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
		Procedure pr2 = new Procedure("procedure 2", 20, null);
		Procedure pr3 = new Procedure("procedure 3", 30, null);
		Procedure pr4 = new Procedure("procedure 4", 40, null);
		
		Specialty s1 = new Specialty("Specialty 1", 10);
		Specialty s2 = new Specialty("Specialty 2", 20);
		Specialty s3 = new Specialty("Specialty 3", 30);
		Specialty s4 = new Specialty("Specialty 4", 40);
		Specialty s5 = new Specialty("Specialty 5", 50);
		
		Employee e1 = new Employee("Employee 1", s1);
		Employee e2 = new Employee("Employee 2", s2);
		Employee e3 = new Employee("Employee 3", s3);
		Employee e4 = new Employee("Employee 4", s4);
		Employee e5 = new Employee("Employee 5", s5);
		
		Item i1 = new Item("Item 1", 10);
		Item i2 = new Item("Item 2", 20);
		Item i3 = new Item("Item 3", 30);
		Item i4 = new Item("Item 4", 40);
		Item i5 = new Item("Item 5", 50);
		
		Logistic l1 = new Logistic("Logistic 1", 10);
		Logistic l2 = new Logistic("Logistic 2", 20);
		Logistic l3 = new Logistic("Logistic 3", 30);
		Logistic l4 = new Logistic("Logistic 4", 40);
		Logistic l5 = new Logistic("Logistic 5", 50);
		
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