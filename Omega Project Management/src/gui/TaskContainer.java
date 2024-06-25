package gui;

import javax.swing.*;

import backend.*;

@SuppressWarnings("serial")
public class TaskContainer extends JPanel {
    private JLabel taskNameLabel;
    private JTextField taskName;
    
	public TaskContainer() {
		setLayout(null);
        
        taskNameLabel = new JLabel("Create new task");
        taskNameLabel.setBounds(130, 80, 150, 30);
        taskNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        taskName = new JTextField("Task Name...");
        taskName.setBounds(130, 110, 150, 30);

        add(taskNameLabel);
        add(taskName);
	}

	public void applyTask(Project p, Task t) {
	    String taskName = getTaskName().trim();

	    if (!taskName.isEmpty()) {
	        if (p != null) {
	            if (t == null) {
	                t = new Task(taskName, "Incomplete");
	                p.addTask(t);
	            } else {
	                t.setTaskName(taskName);
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Invalid Name!\n", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void setTaskNameLabel(String text) {
		taskNameLabel.setText(text);
	}
	
	public String getTaskNameLabel() {
		return taskNameLabel.getText();
	}
	
	public void setTaskName(String text) {
		taskName.setText(text);
	}
	
	public String getTaskName() {
		return taskName.getText();
	}
}
