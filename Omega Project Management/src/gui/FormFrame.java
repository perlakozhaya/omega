package gui;

import backend.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class FormFrame extends JFrame {
    // Panels for different sections of the GUI
    private JPanel buttonPanel, centerPanel, comboPanel;
    
    // Buttons for user actions
    private JButton applyButton, deleteButton;
    
    // ComboBoxes for selecting projects, tasks, and procedures
    private JComboBox<Project> projectBOX;
    private JComboBox<Task> taskBOX;
    private JComboBox<Procedure> procedureBOX;
    
    // Models for the ComboBoxes
    private DefaultComboBoxModel<Project> projectDCBM;
    private DefaultComboBoxModel<Task> taskDCBM;
    private DefaultComboBoxModel<Procedure> procedureDCBM;
    
    // Containers for different GUI components
    private ProjectContainer projectC;
    private TaskContainer taskC;
    private ProcedureContainer procedureC;
    
    // Layout for switching between different containers
    private CardLayout cardLayout;
    
    // Create Menu Bar
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem save;
    private JMenuItem exit;

    // Constructor for initializing the frame
    public FormFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 200, 420, 480);
        setResizable(true);
        setLayout(new BorderLayout());

        // Initialize containers
        projectC = new ProjectContainer();
        taskC = new TaskContainer();
        procedureC = new ProcedureContainer();

        // Add menu bar items
        menuBar = new JMenuBar();
        file = new JMenu("File");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        file.add(save);
        file.add(exit);
        menuBar.add(file);
        setJMenuBar(menuBar);
        
        // Initialize and configure button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adds padding around buttons
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // Initialize buttons
        applyButton = new JButton("Apply");
        deleteButton = new JButton("Delete");

        // Add buttons to the button panel with spacing
        buttonPanel.add(Box.createHorizontalGlue()); // Pushes buttons to the center
        buttonPanel.add(applyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Space between buttons
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createHorizontalGlue()); // Pushes buttons to the center

        // Initialize card layout for center panel
        cardLayout = new CardLayout();
        centerPanel = new JPanel(cardLayout);
        
        // Add containers to the center panel
        centerPanel.add(projectC, "Project");
        centerPanel.add(taskC, "Task");
        centerPanel.add(procedureC, "Procedure");

        // Initialize and configure combo panel
        comboPanel = new JPanel();
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.X_AXIS));
        comboPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adds padding around combo boxes
        comboPanel.setBackground(Color.LIGHT_GRAY);
        
        // Initialize ComboBox models
        projectDCBM = new DefaultComboBoxModel<Project>();
        taskDCBM = new DefaultComboBoxModel<Task>();
        procedureDCBM = new DefaultComboBoxModel<Procedure>();

        // Initialize ComboBoxes with models
        projectBOX = new JComboBox<>(projectDCBM);
        
        taskBOX = new JComboBox<>(taskDCBM);           
        taskBOX.setEnabled(false);
        
        procedureBOX = new JComboBox<>(procedureDCBM); 
        procedureBOX.setEnabled(false);
        
        // Add ComboBoxes to the combo panel with spacing
        comboPanel.add(projectBOX);
        comboPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        comboPanel.add(taskBOX);
        comboPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        comboPanel.add(procedureBOX);
        
//      ------------------------------------------------------------
        
        // On first load populate Project ComboBox with available projects
        fillProject();

        // On select of a project item, populate Task ComboBox with the respective tasks 
        projectBOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Project p = (Project) projectBOX.getSelectedItem();
                if (p != null) {
                    fillTask(p);
                    taskBOX.setEnabled(true);
                    projectC.setLabelContent("Project Name");
                    projectC.setFieldContent(p.getProjectName());
                } else {
                    taskBOX.setEnabled(false);
                    taskBOX.removeAllItems();
                    projectC.setLabelContent("Create new Project");
                    projectC.setFieldContent("Project Name...");
                }
                cardLayout.show(centerPanel, "Project");
            }
        });

        // On select of a task item, populate Procedure ComboBox with the respective procedures 
        taskBOX.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		Task t = (Task) taskBOX.getSelectedItem();
        		if (t != null) {
        			fillProcedure(t);
        			procedureBOX.setEnabled(true);
        			taskC.setLabelContent("Task Name");
        			taskC.setFieldContent(t.getTaskName());
        		} else {
        			procedureBOX.setEnabled(false);
        			procedureBOX.removeAllItems();
        			taskC.setLabelContent("Create new Task");
        			taskC.setFieldContent("Task Name...");
        		}
        		cardLayout.show(centerPanel, "Task");
        	}
        });

        procedureBOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (procedureBOX.getSelectedItem() != null) {
                	procedureC.setNameLabelContent("Procedure Name");
                    procedureC.setNameFieldContent(((Procedure) procedureBOX.getSelectedItem()).getProcedureName());
                    procedureC.setDurationFieldContent(((Procedure) procedureBOX.getSelectedItem()).getProcedureDuration() + "");
                }
                else {
                    procedureC.setNameLabelContent("Create new Procedure");
                    procedureC.setNameFieldContent("Procedure Name...");
                    procedureC.setDurationFieldContent("0");
                }
                cardLayout.show(centerPanel, "Procedure");
            }
        });
        
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Project selectedProject = (Project) projectBOX.getSelectedItem();
                Task selectedTask = (Task) taskBOX.getSelectedItem();
                Procedure selectedProcedure = (Procedure) procedureBOX.getSelectedItem();

                // Handle project selection or creation
                if (selectedProject != null) {
                    // Update project details
                	if(projectC.getFieldContent().isEmpty()) {
                		JOptionPane.showMessageDialog(FormFrame.this, "Project Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                	}
                    selectedProject.setProjectName(projectC.getFieldContent());
                    projectC.setFieldContent(selectedProject.getProjectName());
                } else {
                    // Handle new project creation logic
                    String projectName = projectC.getFieldContent().trim();
                    if (!projectName.equals("Project Name...")) {
                    	if(projectName.isEmpty()) {
                    		JOptionPane.showMessageDialog(FormFrame.this, "Project Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                    	}
                        selectedProject = new Project(projectName, Status.INCOMPLETE);
                        projectDCBM.addElement(selectedProject);
                        projectBOX.setSelectedItem(selectedProject);
                    }
                }

                // Handle task selection or creation
                if (selectedTask == null) {
                    // Handle new task creation logic
                    String taskName = taskC.getFieldContent().trim();
                    if (!taskName.equals("Task Name...")) {
                    	if(taskName.isEmpty()) {
                    		JOptionPane.showMessageDialog(FormFrame.this, "Task Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                    	}
                    	selectedTask = new Task(taskName, Status.INCOMPLETE);
                    	selectedProject.addTask(selectedTask);
                    	taskDCBM.addElement(selectedTask);
                    	taskBOX.setSelectedItem(selectedTask);
                    }
                } else {
                    // Update task details
                	if(taskC.getFieldContent().isEmpty()) {
                		JOptionPane.showMessageDialog(FormFrame.this, "Task Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                	}
                    selectedTask.setTaskName(taskC.getFieldContent());
                    taskC.setFieldContent(selectedTask.getTaskName());
                }

                // Handle procedure selection or creation
                if (selectedProcedure == null) {
                    // Handle new procedure creation logic
                    String procedureName = procedureC.getNameFieldContent().trim();
                    String durationString = procedureC.getDurationFieldContent().trim();
                    
                    if (!procedureName.equals("Procedure Name...") && !durationString.equals("0")) {
                    	if(procedureName.isEmpty() || durationString.isEmpty()) {
                    		JOptionPane.showMessageDialog(FormFrame.this, "Procedure name/duration empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    		return;
                    	}
                    	double procedureDuration;
                    	try {
                    		if(Double.parseDouble(durationString) > 0) {
                    			procedureDuration = Double.parseDouble(durationString);                    			
                    		}
                    		else {
                        		JOptionPane.showMessageDialog(FormFrame.this, "Procedure Duration must be positive!", "Error", JOptionPane.ERROR_MESSAGE);
                        		return;
                    		}
                    	} catch (IllegalArgumentException ex) {
                    		JOptionPane.showMessageDialog(FormFrame.this, "Invalid Procedure Duration! Must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    		return;
                    	}
                    	selectedProcedure = new Procedure(procedureName, procedureDuration, Status.INCOMPLETE);
                    	selectedTask.addProcedure(selectedProcedure);
                    	procedureDCBM.addElement(selectedProcedure);
                    	procedureBOX.setSelectedItem(selectedProcedure);
                    }
                } else {
                    // Update procedure details
                    	if(procedureC.getNameFieldContent().isEmpty() || 
                    	   procedureC.getDurationFieldContent().isEmpty()) {
                    		JOptionPane.showMessageDialog(FormFrame.this, "Procedure name/duration empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    		return;
                    	}
                    	double procedureDuration;
                    	try {
                    		if(Double.parseDouble(procedureC.getDurationFieldContent()) > 0) {
                    			procedureDuration = Double.parseDouble(procedureC.getDurationFieldContent());                    			
                    		}
                    		else {
                        		JOptionPane.showMessageDialog(FormFrame.this, "Procedure Duration must be positive!", "Error", JOptionPane.ERROR_MESSAGE);
                        		return;
                    		}
                    	} catch (IllegalArgumentException ex) {
                    		JOptionPane.showMessageDialog(FormFrame.this, "Invalid Procedure Duration! Must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    		return;
                    	}
                    selectedProcedure.setProcedureName(procedureC.getNameFieldContent());
                    selectedProcedure.setProcedureDuration(procedureDuration);
                    procedureC.setNameFieldContent(selectedProcedure.getProcedureName());
                    procedureC.setDurationFieldContent(String.valueOf(selectedProcedure.getProcedureDuration()));
                }
                
                if(procedureC.getCurrentCard() == "employee") {
                	
                	Employee employee = procedureC.getEmployeeContainer().getEmployeeList().getSelectedValue();
                	double workedHours = Double.parseDouble(procedureC.getEmployeeContainer().getWorkedHours());
                	
                	if(employee != null) {
                		boolean found = false;
                		System.out.println(employee + "\t" + workedHours);
                		
                		for(ProcedureEmployee pe : selectedProcedure.getEmployees()) {
                			System.out.println("inside the for loop");
                			if(pe.getEmployee().equals(employee)) {
                				pe.setHoursWorked(workedHours);
                				found = true;
                				System.out.println(employee + " : " + workedHours + " : updated " + found);
                			}
                		}
                		if(!found) {
                			selectedProcedure.addEmployee(employee, workedHours);
                			employee.addProcedure(selectedProcedure, workedHours);
                			System.out.println(employee + " : " + workedHours + " : updated " + found);
                		}
                	}
                	//3al 0 ma7e l employee
                	//on select on employee ya3mol update la worked hours
                	//check if the worked hours of the employee are more then the duration taba3 bati5a
                	
                }else if(procedureC.getCurrentCard() == "item") {
                	
                	
                }else if(procedureC.getCurrentCard() == "logistic") {
                	
                	
                }
            }
        });

//      ------------------------------------------------------------

        // Add panels to the frame
        add(buttonPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        add(comboPanel, BorderLayout.NORTH);
        
        // Make the frame visible
        setVisible(true);
    }
    
    public void fillProject() {
    	projectDCBM.removeAllElements();
		projectDCBM.addElement(null);
		for(Project p : Project.projects) {
			projectDCBM.addElement(p);
		}
    }
    
    public void fillTask(Project p) {    		
		taskDCBM.removeAllElements();
		taskDCBM.addElement(null);
		for(Task t : p.getTasks()) {
			taskDCBM.addElement(t);
		}    		
    }
    
    public void fillProcedure(Task t) {
    	procedureDCBM.removeAllElements();
		procedureDCBM.addElement(null);
		for(Procedure pr : t.getProcedures()) {
			procedureDCBM.addElement(pr);
		}
    }
}