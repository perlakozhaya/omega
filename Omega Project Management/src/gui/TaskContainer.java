package gui;

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
