package gui;

import javax.swing.*;

import backend.Project;

@SuppressWarnings("serial")
public class ProjectContainer extends JPanel {
    private JLabel projectNameLabel;
    private JTextField projectName;
    
	public ProjectContainer() {
		setLayout(null);
        
        projectNameLabel = new JLabel("Create new Project");
        projectNameLabel.setBounds(130, 80, 150, 30);
        projectNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
       
        projectName = new JTextField("Project Name...");
        projectName.setBounds(130, 110, 150, 30);

        add(projectNameLabel);
        add(projectName);
	}
	
	public boolean applyProject(Project p) {
	    String projectName = getProjectName().trim();

	    if (!projectName.isEmpty()) {
	        if (p == null) {
	            p = new Project(projectName, "Incomplete");
	            return false; // Indicating a new project creation
	        } else {
	            p.setProjectName(projectName);
	            return true; // Indicating project update
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Invalid Name!\n", "Error", JOptionPane.ERROR_MESSAGE);
	        return true; // Prevent further task processing
	    }
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