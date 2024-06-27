package gui;

import backend.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FormFrame extends JFrame {
    // Panels for different sections of the GUI
    private JPanel centerPanel, comboBoxPanel;
    
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
    
    private DataManager dataManager;

    // Constructor for initializing the frame
    public FormFrame(DataManager dataManager) {
        this.dataManager = dataManager;
        setTitle("Project Management Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 200, 420, 480);
        setResizable(true);
        setLayout(new BorderLayout());

        // Add menu bar items
        menuBar = new JMenuBar();
        file = new JMenu("File");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        file.add(save);
        file.add(exit);
        menuBar.add(file);
        setJMenuBar(menuBar);
        
        // Initialize and configure combo panel
        comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.X_AXIS));
        comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        comboBoxPanel.setBackground(Color.LIGHT_GRAY);
        
        // Initialize ComboBoxes with models
        projectDCBM = new DefaultComboBoxModel<>();
        projectBOX = new JComboBox<>(projectDCBM);
        
        taskDCBM = new DefaultComboBoxModel<>();
        taskBOX = new JComboBox<>(taskDCBM);           
        taskBOX.setEnabled(false);
        
        procedureDCBM = new DefaultComboBoxModel<>();
        procedureBOX = new JComboBox<>(procedureDCBM); 
        procedureBOX.setEnabled(false);
        
        // Add ComboBoxes to the combo panel with spacing
        comboBoxPanel.add(projectBOX);
        comboBoxPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        comboBoxPanel.add(taskBOX);
        comboBoxPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        comboBoxPanel.add(procedureBOX);
        
        // Initialize containers
        projectC = new ProjectContainer(dataManager, this);
        taskC = new TaskContainer(dataManager, this);
        procedureC = new ProcedureContainer(dataManager, this);

        // Initialize card layout for center panel
        cardLayout = new CardLayout();
        centerPanel = new JPanel(cardLayout);
        
        // Add containers to the center panel
        centerPanel.add(projectC, "Project");
        centerPanel.add(taskC, "Task");
        centerPanel.add(procedureC, "Procedure");
        
        // Add panels to the frame
        add(comboBoxPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        
//      ------------------------------------------------------------
        
        // Action listener for project selection
        projectBOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Project selectedProject = (Project) projectBOX.getSelectedItem();
                if (selectedProject != null) {
                    fillTask(selectedProject);
                    taskBOX.setEnabled(true);
                    projectC.setProjectLabel("Project Name");
                    projectC.setProjectField(selectedProject.getProjectName());
                } else {
                    taskBOX.setEnabled(false);
                    taskBOX.removeAllItems();
                    projectC.setProjectLabel("Create new Project");
                    projectC.setProjectField("Project Name...");
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
                    taskC.setTaskLabel("Task Name");
                    taskC.setTaskField(selectedTask.getTaskName());
                } else {
                    procedureBOX.setEnabled(false);
                    procedureBOX.removeAllItems();
                    taskC.setTaskLabel("Create new Task");
                    taskC.setTaskField("Task Name...");
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
                    procedureC.setProcedureLabel("Procedure Name");
                    procedureC.setProcedureField(selectedProcedure.getProcedureName());
                    procedureC.setDurationField(selectedProcedure.getProcedureDuration() + "");
                } else {
                    procedureC.setProcedureLabel("Create new Procedure");
                    procedureC.setProcedureField("Procedure Name...");
                    procedureC.setDurationField("0");
                }
                cardLayout.show(centerPanel, "Procedure");
            }
        });
        
//        applyButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
                // Handle adding worked hours for a selected employee
//                if(procedureC.getCurrentCard() == "employee") {
//                	JList<Employee> empList = procedureC.getEmployeeContainer().getEmployeeList();
//                	Employee employee = empList.getSelectedValue();
//                	double workedHours = Double.parseDouble(procedureC.getEmployeeContainer().getWorkedHoursField());
//                	
//                	empList.addListSelectionListener(new ListSelectionListener() {
//                		@Override
//                		public void valueChanged(ListSelectionEvent e) {
//                			Employee selectedEmp = empList.getSelectedValue();
//                			Procedure selectedPr = (Procedure) procedureBOX.getSelectedItem();
//                			boolean found = false;
//                			
//                			// update worked hours for working employees
//                			for(ProcedureEmployee pe : selectedPr.getEmployees()) {
//                				if(pe.getEmployee().equals(selectedEmp)) {
//                					procedureC.getEmployeeContainer().setWorkedHoursField(pe.getHoursWorked() + "");
//                					found = true;                        				
//                				}
//                			}
//                			if(!found) {
//                				procedureC.getEmployeeContainer().setWorkedHoursField("0");
//                			}
//                		}
//                	});
//                	
//                	if(employee != null) {
//                		if(workedHours > selectedProcedure.getProcedureDuration()) {
//            				JOptionPane.showMessageDialog(FormFrame.this, "Employee hours cannot exceed the procedure duration!", "Error", JOptionPane.ERROR_MESSAGE);
//            				return;
//            			}
//                		boolean found = false;
//                		
//                		for(ProcedureEmployee pe : selectedProcedure.getEmployees()) {
//                			if(pe.getEmployee().equals(employee)) {
//                				pe.setHoursWorked(workedHours);
//                				found = true;
//                			}
//                		}
//                		if(!found) {
//                			selectedProcedure.addEmployee(employee, workedHours);
//                			employee.addProcedure(selectedProcedure, workedHours);
//                		}
//                		
//                	}
//                	
//                	// delete employee if worked hours set to 0
//
//                	// validate worked hours (mech negative wala string)
//                	
//                } else if(procedureC.getCurrentCard() == "item") {
//                	
//                	
//                } else if(procedureC.getCurrentCard() == "logistic") {
//                	
//                	
//                }
//            }
//        });
    }
    
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
    
    public void fillProject() {
    	projectDCBM.removeAllElements();
		projectDCBM.addElement(null);
		for(Project p : dataManager.getProjects()) {
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
    
    public Project getSelectedProject() {
    	return (Project) projectBOX.getSelectedItem();
    }
    
    public Task getSelectedTask() {
    	return (Task) taskBOX.getSelectedItem();
    }
    
    public Procedure getSelectedProcedure() {
    	return (Procedure) procedureBOX.getSelectedItem();
    }
    
    public void setSelectedProject(Project project) {
    	projectBOX.setSelectedItem(project);
    }
    
    public void setSelectedTask(Task task) {
    	taskBOX.setSelectedItem(task);
    }
    
    public void setSelectedProcedure(Procedure procedure) {
    	procedureBOX.setSelectedItem(procedure);
    }
    
//    private void handleTask(Project selectedProject, Task selectedTask) {
//        String taskName = taskC.getFieldContent().trim();
//        if (selectedTask == null) {
//            if (!taskName.equals("Task Name...")) {
//                if (taskName.isEmpty()) {
//                    showError("Task Name cannot be empty!");
//                    return;
//                }
//                selectedTask = new Task(taskName, Status.INCOMPLETE);
//                selectedProject.addTask(selectedTask);
//                taskDCBM.addElement(selectedTask);
//                taskBOX.setSelectedItem(selectedTask);
//            }
//        } else {
//            if (taskName.isEmpty()) {
//                showError("Task Name cannot be empty!");
//                return;
//            }
//            selectedTask.setTaskName(taskName);
//            taskC.setFieldContent(taskName);
//        }
//    }
//
//    private void handleProcedure(Task selectedTask, Procedure selectedProcedure) {
//        String procedureName = procedureC.getNameFieldContent().trim();
//        String durationString = procedureC.getDurationFieldContent().trim();
//
//        if (selectedProcedure == null) {
//            if (!procedureName.equals("Procedure Name...") && !durationString.equals("0")) {
//                if (procedureName.isEmpty() || durationString.isEmpty()) {
//                    showError("Procedure name/duration empty!");
//                    return;
//                }
//                try {
//                    double procedureDuration = parsePositiveDouble(durationString, "Procedure Duration must be a positive number!");
//                    selectedProcedure = new Procedure(procedureName, procedureDuration, Status.INCOMPLETE);
//                    selectedTask.addProcedure(selectedProcedure);
//                    procedureDCBM.addElement(selectedProcedure);
//                    procedureBOX.setSelectedItem(selectedProcedure);
//                } catch (IllegalArgumentException ex) {
//                    showError(ex.getMessage());
//                    return;
//                }
//            }
//        } else {
//            if (procedureName.isEmpty() || durationString.isEmpty()) {
//                showError("Procedure name/duration empty!");
//                return;
//            }
//            try {
//                double procedureDuration = parsePositiveDouble(durationString, "Procedure Duration must be a positive number!");
//                selectedProcedure.setProcedureName(procedureName);
//                selectedProcedure.setProcedureDuration(procedureDuration);
//                procedureC.setNameFieldContent(procedureName);
//                procedureC.setDurationFieldContent(String.valueOf(procedureDuration));
//            } catch (IllegalArgumentException ex) {
//                showError(ex.getMessage());
//                return;
//            }
//        }
//    }
}