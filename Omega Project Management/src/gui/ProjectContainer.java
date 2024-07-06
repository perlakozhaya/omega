package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import backend.*;

@SuppressWarnings("serial")
public class ProjectContainer extends JPanel {
    private JLabel projectNameLB;
    private JTextField projectNameFLD;
    private JButton projectApplyBTN, projectDeleteBTN;
    
    private DataManager dataManager;
    private FormFrame formFrame;
    
	public ProjectContainer(DataManager dataManager, FormFrame formFrame) {
		this.dataManager = dataManager;
        this.formFrame = formFrame;
		setLayout(null);
        
        projectNameLB = new JLabel("Create new Project");
        projectNameLB.setBounds(190, 80, 150, 30);
        projectNameLB.setHorizontalAlignment(SwingConstants.CENTER);
       
        projectNameFLD = new JTextField("Project Name...");
        projectNameFLD.setBounds(190, 110, 150, 30);
        
        projectApplyBTN = new JButton("Apply Changes");
        projectApplyBTN.setBounds(190, 180, 150, 30);
        
        projectDeleteBTN = new JButton("Delete Project");
        projectDeleteBTN.setBounds(190, 220, 150, 30);

        add(projectNameLB);
        add(projectNameFLD);
        add(projectApplyBTN);
        add(projectDeleteBTN);
        
        projectApplyBTN.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		Project selectedProject = formFrame.getSelectedProject();
        		handleProject(selectedProject);
        		formFrame.setSelectedProject(selectedProject);
        	}
        });
        
        projectDeleteBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Project selectedProject = formFrame.getSelectedProject();
                if(selectedProject != null) {
                	int response = JOptionPane.showConfirmDialog(null, 
                			"Are you sure you want to delete this project?", 
                			"Confirm Deletion",
                			JOptionPane.YES_NO_OPTION,
                			JOptionPane.WARNING_MESSAGE);
                	
                	if (response == JOptionPane.YES_OPTION) {
                		dataManager.removeProject(selectedProject);
                		formFrame.fillProject();
                	}                	
                }
                else {
                	formFrame.showMessage("Select a project to delete.");
                }
            }
        });
	}
	
    private void handleProject(Project selectedProject) {
        String projectName = getProjectField().trim();
        
        if (projectName.isEmpty()) {
        	formFrame.showMessage("Project Name cannot be empty!");
        	return;
        }
        
        if (selectedProject != null) {
            dataManager.updateProject(selectedProject, projectName);
            setProjectField(projectName);
    		formFrame.fillProject();
        } else {
    		createProject(projectName);
        }
    }
    
    private void createProject(String projectName) {
    	if (!projectName.equals("Project Name...")) {
	    	Project newProject = new Project(projectName);
			dataManager.addProject(newProject);
			formFrame.fillProject();
			formFrame.setSelectedProject(newProject);
    	}
    }
	
	public void setProjectLabel(String text) {
		projectNameLB.setText(text);
	}
	
	public String getProjectLabel() {
		return projectNameLB.getText();
	}
	
	public void setProjectField(String text) {
		projectNameFLD.setText(text);
	}
	
	public String getProjectField() {
		return projectNameFLD.getText();
	}
}