package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.*;

import backend.*;

@SuppressWarnings("serial")
public class FilterFrame extends JFrame implements Observer {
	private DefaultComboBoxModel<Procedure> proceduresDCBM;
	private JComboBox<Procedure> proceduresCB;
	
	private DataManager dataManager;
	
	public FilterFrame(DataManager dataManager) {
		this.dataManager = dataManager;
		dataManager.addObserver(this);
		
		setTitle("Filter Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 10, 600, 420);
		setLayout(null);
		
		proceduresDCBM = new DefaultComboBoxModel<>();
		proceduresCB = new JComboBox<>(proceduresDCBM);
		proceduresCB.setBounds(10, 10, 100, 30);
		add(proceduresCB);
		
		update(null, null);
		
//		Procedure selectedProcedure = getSelectedProcedure();
//		Task selectedProcedureTask = getProcedureTask(selectedProcedure);
//		Project selectedProcedureProject = getProcedureProject(selectedProcedureTask);
	}
	
	public void fillProcedures() {
		Set<Procedure> allProcedures = dataManager.getProcedures();
		if(!allProcedures.isEmpty()) {
			for(Procedure procedure : allProcedures) {
				proceduresDCBM.addElement(procedure);
			}
		}
	}
	
	public Task getProcedureTask(Procedure procedure) {
		Set<Task> allTasks = dataManager.getTasks();
		if(!allTasks.isEmpty()) {
			for(Task task : allTasks) {
				if(task.getProcedures().contains(procedure)) {
					return task;
				}
			}
		}
		return null;
	}
	
	public Project getProcedureProject(Task task) {
		Set<Project> allProjects = dataManager.getProjects();
		if(!allProjects.isEmpty()) {
			for(Project project : allProjects) {
				if(project.getTasks().contains(task)) {
					return project;
				}
			}
		}
		return null;
	}
	
	public Procedure getSelectedProcedure() {
		return (Procedure) proceduresCB.getSelectedItem();
	}

	@Override
	public void update(Observable o, Object arg) {
		fillProcedures();
	}
}