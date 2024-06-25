package gui;

import javax.swing.*;

import backend.Project;

@SuppressWarnings("serial")
public class ProjectContainer extends JPanel {
    private JLabel projectNameLB;
    private JTextField projectNameFLD;
    
	public ProjectContainer() {
		setLayout(null);
        
        projectNameLB = new JLabel("Create new Project");
        projectNameLB.setBounds(130, 80, 150, 30);
        projectNameLB.setHorizontalAlignment(SwingConstants.CENTER);
       
        projectNameFLD = new JTextField("Project Name...");
        projectNameFLD.setBounds(130, 110, 150, 30);

        add(projectNameLB);
        add(projectNameFLD);
	}
	
	public void setLabelContent(String text) {
		projectNameLB.setText(text);
	}
	
	public String getLabelContent() {
		return projectNameLB.getText();
	}
	
	public void setFieldContent(String text) {
		projectNameFLD.setText(text);
	}
	
	public String getFieldContent() {
		return projectNameFLD.getText();
	}
}