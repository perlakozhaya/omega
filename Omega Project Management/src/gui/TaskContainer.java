package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import backend.*;

@SuppressWarnings("serial")
public class TaskContainer extends JPanel {
    private JLabel taskNameLB;
    private JTextField taskNameFLD;
    private JButton taskApplyBTN;
    
    private DataManager dataManager;
    private FormFrame formFrame;
    
	public TaskContainer(DataManager dataManager, FormFrame formFrame) {
		this.dataManager = dataManager;
        this.formFrame = formFrame;
		setLayout(null);
        
        taskNameLB = new JLabel("Create new Task");
        taskNameLB.setBounds(130, 80, 150, 30);
        taskNameLB.setHorizontalAlignment(SwingConstants.CENTER);
        
        taskNameFLD = new JTextField("Task Name...");
        taskNameFLD.setBounds(130, 110, 150, 30);
        
        taskApplyBTN = new JButton("Apply Changes");
        taskApplyBTN.setBounds(130, 180, 150, 30);

        add(taskNameLB);
        add(taskNameFLD);
        add(taskApplyBTN);
        
        taskApplyBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Project selectedProject = formFrame.getSelectedProject();
				Task selectedTask = formFrame.getSelectedTask();
				handleTask(selectedProject, selectedTask);
			}
        });
	}
	
    private void handleTask(Project selectedProject, Task selectedTask) {
        String taskName = getTaskField().trim();
        
        if (taskName.isEmpty()) {
        	formFrame.showError("Task Name cannot be empty!");
        	return;
        }
        
        if (selectedTask == null) {
            createTask(selectedProject, taskName);
        } else {
            dataManager.updateTaskInProject(selectedProject, selectedTask, taskName);
            setTaskField(taskName);
            formFrame.fillTask();
        }
    }
	
    private void createTask(Project selectedProject, String taskName) {
    	if (!taskName.equals("Task Name...")) {
            Task newTask = new Task(taskName);
            dataManager.addTaskToProject(selectedProject, newTask);
            formFrame.fillTask();
            formFrame.setSelectedTask(newTask);
        }
    }
    
	public void setTaskLabel(String text) {
		taskNameLB.setText(text);
	}
	
	public String getTaskLabel() {
		return taskNameLB.getText();
	}
	
	public void setTaskField(String text) {
		taskNameFLD.setText(text);
	}
	
	public String getTaskField() {
		return taskNameFLD.getText();
	}
}
