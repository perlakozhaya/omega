package gui;

import javax.swing.*;

@SuppressWarnings("serial")
public class ProjectContainer extends JPanel{
    private JLabel projectNameLabel;
    private JTextField projectName;
    
	public ProjectContainer() {
		setLayout(null);
        
        projectNameLabel = new JLabel("Create new Project");
        projectNameLabel.setBounds(117, 80, 150, 30);
        
        projectName = new JTextField("Project Name...");
        projectName.setBounds(100, 110, 150, 30);

        add(projectNameLabel);
        add(projectName);
	}

}
