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
        setTitle("Project Management Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 200, 420, 480);
        setResizable(true);
        setLayout(new BorderLayout());
        this.dataManager = dataManager;
//        dataManager.addObserver(this);

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
        
//      -------------------------------------------------------------------------
        
//        update(null, null);
        
        fillProject();

        // Change Project Container label and field based on project selection / creation
        projectBOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Project selectedProject = getSelectedProject();
                if (selectedProject != null) {
            		fillTask();
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

        // Change Task Container label and field based on task selection / creation
        taskBOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task selectedTask = getSelectedTask();
                if (selectedTask != null) {
            		fillProcedure();
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

        // Change Procedure Container label and field based on procedure selection / creation
        procedureBOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Procedure selectedProcedure = getSelectedProcedure();
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
    }
    
    public void fillProject() {
    	Set<Project> projects = dataManager.getProjects();
    	projectDCBM.removeAllElements();
		projectDCBM.addElement(null);
		if(!projects.isEmpty()) {
			for(Project p : projects) {
				projectDCBM.addElement(p);
			}
		}
    }
    
    public void fillTask() {
    	Set<Task> tasks = getSelectedProject().getTasks();
    	taskDCBM.removeAllElements();
    	taskDCBM.addElement(null);
		if(!tasks.isEmpty()) {
			for(Task t : tasks) {
				taskDCBM.addElement(t);
			}
		}
    }
    
    public void fillProcedure() {
    	Set<Procedure> procedures = getSelectedTask().getProcedures();
    	procedureDCBM.removeAllElements();
    	procedureDCBM.addElement(null);
		if(!procedures.isEmpty()) {
			for(Procedure pr : procedures) {
				procedureDCBM.addElement(pr);
			}
		}
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
}