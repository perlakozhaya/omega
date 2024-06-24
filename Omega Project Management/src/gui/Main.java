package gui;

import backend.Procedure;
import backend.Project;
import backend.Task;

public class Main {

	public static void main(String[] args) {
    	Project p1 = new Project("project 1", null);
		Project p2 = new Project("project 2", null);
		
		Task t1 = new Task("task 1", null);
		Task t2 = new Task("task 2", null);
		Task t3 = new Task("task 3", null);
		
		Procedure pr1 = new Procedure("procedure 1", null);
		Procedure pr2 = new Procedure("procedure 2", null);
		Procedure pr3 = new Procedure("procedure 3", null);
		Procedure pr4 = new Procedure("procedure 4", null);
		
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