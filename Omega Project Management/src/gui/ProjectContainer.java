package gui;

import javax.swing.*;

@SuppressWarnings("serial")
public class ProjectContainer extends JPanel {
    private JLabel projectNameLabel;
    private JTextField projectName;
    
	public ProjectContainer() {
		setLayout(null);
        
        projectNameLabel = new JLabel("Create new Project");
        projectNameLabel.setBounds(147, 80, 150, 30);
        
        projectName = new JTextField("Project Name...");
        projectName.setBounds(130, 110, 150, 30);

        add(projectNameLabel);
        add(projectName);
	}
	
	public void setProjectNameLabel(String text) {
		projectNameLabel.setText(text);
	}
	
	public String getProjectNameLabel() {
		return projectNameLabel.getText();
	}
	
	public void setProjectName(String text) {
		projectName.setText(text);
	}
	
	public String getProjectName() {
		return projectName.getText();
	}
}