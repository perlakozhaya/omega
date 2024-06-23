package gui;

import javax.swing.*;

@SuppressWarnings("serial")
public class TaskContainer extends JPanel {
    private JLabel taskNameLabel;
    private JTextField taskName;
    
	public TaskContainer() {
		setLayout(null);
        
        taskNameLabel = new JLabel("Create new task");
        taskNameLabel.setBounds(117, 80, 150, 30);
        
        taskName = new JTextField("Task Name...");
        taskName.setBounds(100, 110, 150, 30);

        add(taskNameLabel);
        add(taskName);
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
