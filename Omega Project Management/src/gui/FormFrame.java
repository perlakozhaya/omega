package gui;

import backend.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
        
     // Populate Project ComboBox with available projects on first load
        fillProject();

        // Action listener for project selection
        projectBOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Project selectedProject = (Project) projectBOX.getSelectedItem();
                if (selectedProject != null) {
                    fillTask(selectedProject);
                    taskBOX.setEnabled(true);
                    projectC.setLabelContent("Project Name");
                    projectC.setFieldContent(selectedProject.getProjectName());
                } else {
                    taskBOX.setEnabled(false);
                    taskBOX.removeAllItems();
                    projectC.setLabelContent("Create new Project");
                    projectC.setFieldContent("Project Name...");
                }
                cardLayout.show(centerPanel, "Project");
            }
        });

        // Action listener for task selection
        taskBOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task selectedTask = (Task) taskBOX.getSelectedItem();
                if (selectedTask != null) {
                    fillProcedure(selectedTask);
                    procedureBOX.setEnabled(true);
                    taskC.setLabelContent("Task Name");
                    taskC.setFieldContent(selectedTask.getTaskName());
                } else {
                    procedureBOX.setEnabled(false);
                    procedureBOX.removeAllItems();
                    taskC.setLabelContent("Create new Task");
                    taskC.setFieldContent("Task Name...");
                }
                cardLayout.show(centerPanel, "Task");
            }
        });

        // Action listener for procedure selection
        procedureBOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Procedure selectedProcedure = (Procedure) procedureBOX.getSelectedItem();
                if (selectedProcedure != null) {
                    procedureC.setNameLabelContent("Procedure Name");
                    procedureC.setNameFieldContent(selectedProcedure.getProcedureName());
                    procedureC.setDurationFieldContent(selectedProcedure.getProcedureDuration() + "");
                } else {
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
                handleProject(selectedProject);
                // Handle task selection or creation
                handleTask(selectedProject, selectedTask);
                // Handle procedure selection or creation
                handleProcedure(selectedTask, selectedProcedure);
                
                // Handle adding worked hours for a selected employee
                if(procedureC.getCurrentCard() == "employee") {
                	JList<Employee> empList = procedureC.getEmployeeContainer().getEmployeeList();
                	Employee employee = empList.getSelectedValue();
                	double workedHours = Double.parseDouble(procedureC.getEmployeeContainer().getWorkedHoursField());
                	
                	empList.addListSelectionListener(new ListSelectionListener() {
                		@Override
                		public void valueChanged(ListSelectionEvent e) {
                			Employee selectedEmp = empList.getSelectedValue();
                			Procedure selectedPr = (Procedure) procedureBOX.getSelectedItem();
                			boolean found = false;
                			
                			// update worked hours for working employees
                			for(ProcedureEmployee pe : selectedPr.getEmployees()) {
                				if(pe.getEmployee().equals(selectedEmp)) {
                					procedureC.getEmployeeContainer().setWorkedHoursField(pe.getHoursWorked() + "");
                					found = true;                        				
                				}
                			}
                			if(!found) {
                				procedureC.getEmployeeContainer().setWorkedHoursField("0");
                			}
                		}
                	});
                	
                	if(employee != null) {
                		if(workedHours > selectedProcedure.getProcedureDuration()) {
            				JOptionPane.showMessageDialog(FormFrame.this, "Employee hours cannot exceed the procedure duration!", "Error", JOptionPane.ERROR_MESSAGE);
            				return;
            			}
                		boolean found = false;
                		
                		for(ProcedureEmployee pe : selectedProcedure.getEmployees()) {
                			if(pe.getEmployee().equals(employee)) {
                				pe.setHoursWorked(workedHours);
                				found = true;
                			}
                		}
                		if(!found) {
                			selectedProcedure.addEmployee(employee, workedHours);
                			employee.addProcedure(selectedProcedure, workedHours);
                		}
                		
                	}
                	
                	// delete employee if worked hours set to 0

                	// validate worked hours (mech negative wala string)
                	
                } else if(procedureC.getCurrentCard() == "item") {
                	
                	
                } else if(procedureC.getCurrentCard() == "logistic") {
                	
                	
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
    
    // Other methods
    public double parsePositiveDouble(String value, String errorMessage) throws IllegalArgumentException {
        double number = Double.parseDouble(value);
        if (number <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
        return number;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(FormFrame.this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void fillProject() {
    	projectDCBM.removeAllElements();
		projectDCBM.addElement(null);
		for(Project p : Project.projects) {
			projectDCBM.addElement(p);
		}
    }
    
    private void fillTask(Project p) {    		
		taskDCBM.removeAllElements();
		taskDCBM.addElement(null);
		for(Task t : p.getTasks()) {
			taskDCBM.addElement(t);
		}    		
    }
    
    private void fillProcedure(Task t) {
    	procedureDCBM.removeAllElements();
		procedureDCBM.addElement(null);
		for(Procedure pr : t.getProcedures()) {
			procedureDCBM.addElement(pr);
		}
    }
    
    private void handleProject(Project selectedProject) {
        String projectName = projectC.getFieldContent().trim();
        if (selectedProject != null) {
            if (projectName.isEmpty()) {
                showError("Project Name cannot be empty!");
                return;
            }
            selectedProject.setProjectName(projectName);
            projectC.setFieldContent(projectName);
        } else {
        	if (!projectName.equals("Project Name...")) {
        		if (projectName.isEmpty()) {
        			showError("Project Name cannot be empty!");
        			return;
        		}
        		selectedProject = new Project(projectName, Status.INCOMPLETE);
        		projectDCBM.addElement(selectedProject);
        		projectBOX.setSelectedItem(selectedProject);
        	}
        }
    }

    private void handleTask(Project selectedProject, Task selectedTask) {
        String taskName = taskC.getFieldContent().trim();
        if (selectedTask == null) {
            if (!taskName.equals("Task Name...")) {
                if (taskName.isEmpty()) {
                    showError("Task Name cannot be empty!");
                    return;
                }
                selectedTask = new Task(taskName, Status.INCOMPLETE);
                selectedProject.addTask(selectedTask);
                taskDCBM.addElement(selectedTask);
                taskBOX.setSelectedItem(selectedTask);
            }
        } else {
            if (taskName.isEmpty()) {
                showError("Task Name cannot be empty!");
                return;
            }
            selectedTask.setTaskName(taskName);
            taskC.setFieldContent(taskName);
        }
    }

    private void handleProcedure(Task selectedTask, Procedure selectedProcedure) {
        String procedureName = procedureC.getNameFieldContent().trim();
        String durationString = procedureC.getDurationFieldContent().trim();

        if (selectedProcedure == null) {
            if (!procedureName.equals("Procedure Name...") && !durationString.equals("0")) {
                if (procedureName.isEmpty() || durationString.isEmpty()) {
                    showError("Procedure name/duration empty!");
                    return;
                }
                try {
                    double procedureDuration = parsePositiveDouble(durationString, "Procedure Duration must be a positive number!");
                    selectedProcedure = new Procedure(procedureName, procedureDuration, Status.INCOMPLETE);
                    selectedTask.addProcedure(selectedProcedure);
                    procedureDCBM.addElement(selectedProcedure);
                    procedureBOX.setSelectedItem(selectedProcedure);
                } catch (IllegalArgumentException ex) {
                    showError(ex.getMessage());
                    return;
                }
            }
        } else {
            if (procedureName.isEmpty() || durationString.isEmpty()) {
                showError("Procedure name/duration empty!");
                return;
            }
            try {
                double procedureDuration = parsePositiveDouble(durationString, "Procedure Duration must be a positive number!");
                selectedProcedure.setProcedureName(procedureName);
                selectedProcedure.setProcedureDuration(procedureDuration);
                procedureC.setNameFieldContent(procedureName);
                procedureC.setDurationFieldContent(String.valueOf(procedureDuration));
            } catch (IllegalArgumentException ex) {
                showError(ex.getMessage());
                return;
            }
        }
    }
}