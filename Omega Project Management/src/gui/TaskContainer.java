package gui;

import javax.swing.*;

import backend.*;

@SuppressWarnings("serial")
public class TaskContainer extends JPanel {
    private JLabel taskNameLB;
    private JTextField taskNameFLD;
    
	public TaskContainer() {
		setLayout(null);
        
        taskNameLB = new JLabel("Create new Task");
        taskNameLB.setBounds(130, 80, 150, 30);
        taskNameLB.setHorizontalAlignment(SwingConstants.CENTER);
        
        taskNameFLD = new JTextField("Task Name...");
        taskNameFLD.setBounds(130, 110, 150, 30);

        add(taskNameLB);
        add(taskNameFLD);
	}
	
	public void setLabelContent(String text) {
		taskNameLB.setText(text);
	}
	
	public String getLabelContent() {
		return taskNameLB.getText();
	}
	
	public void setFieldContent(String text) {
		taskNameFLD.setText(text);
	}
	
	public String getFieldContent() {
		return taskNameFLD.getText();
	}
}
