package gui;

import backend.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

@SuppressWarnings("serial")
public class FormFrame extends JFrame implements Observer {
    // Panels for different sections of the GUI
    private JPanel buttonPanel, centerPanel, comboPanel;
    
    // Buttons for user actions
    private JButton clearButton, saveButton, deleteButton;
    
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

    // Constructor for initializing the frame
    public FormFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 200, 420, 450);
        setResizable(true);
        setLayout(new BorderLayout());

        // Initialize containers
        projectC = new ProjectContainer();
        taskC = new TaskContainer();
        procedureC = new ProcedureContainer();

        // Initialize and configure button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adds padding around buttons
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // Initialize buttons
        clearButton = new JButton("Clear");
        saveButton = new JButton("Save");
        deleteButton = new JButton("Delete");

        // Add buttons to the button panel with spacing
        buttonPanel.add(Box.createHorizontalGlue()); // Pushes buttons to the center
        buttonPanel.add(clearButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Space between buttons
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Space between buttons
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
        projectDCBM.addElement(null);
        for (Project p : Project.projects) {
        	projectDCBM.addElement(p);
            p.addObserver(this);
        }

        projectBOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (projectBOX.getSelectedItem() != null) {
                    fillTask();
                    taskBOX.setEnabled(true);
                    projectC.setProjectNameLabel("Project Name");
                    projectC.setProjectName(((Project) projectBOX.getSelectedItem()).getProjectName());
                    cardLayout.show(centerPanel, "Project");
                } else {
                    taskBOX.setEnabled(false);
                    taskBOX.removeAllItems();
                    procedureBOX.setEnabled(false);
                    procedureBOX.removeAllItems();
                    projectC.setProjectNameLabel("Create new project");
                    projectC.setProjectName("Project Name...");
                }
            }
        });

        taskBOX.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (taskBOX.getSelectedItem() != null) {
        			fillProcedure();
        			procedureBOX.setEnabled(true);
        			taskC.setTaskNameLabel("Task Name");
        			taskC.setTaskName(((Task) taskBOX.getSelectedItem()).getTaskName());
        			cardLayout.show(centerPanel, "Task");
        		} else {
        			procedureBOX.setEnabled(false);
        			procedureBOX.removeAllItems();
        			taskC.setTaskNameLabel("Create new task");
        			taskC.setTaskName("Task Name...");
        		}
        	}
        });

        procedureBOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (procedureBOX.getSelectedItem() != null) {
                	procedureC.getProcedureNameLabel().setText("Procedure Name");
                    procedureC.getProcedureName().setText(((Procedure) procedureBOX.getSelectedItem()).getProcedureName());
                    
                    cardLayout.show(centerPanel, "Procedure");
                }
                else {
                    procedureC.getProcedureNameLabel().setText("Create new procedure");
                    procedureC.getProcedureName().setText("Procedure Name...");
                   
                    
                    // show procedureDetails 
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
    
    public void fillTask() {
    	taskDCBM.removeAllElements();
		
		taskDCBM.addElement(null);
		for(Task t : ((Project) projectBOX.getSelectedItem()).getTasks()) {
			taskDCBM.addElement(t);
			t.addObserver(this);
		}
    }
    
    public void fillProcedure() {
    	procedureDCBM.removeAllElements();
		
		procedureDCBM.addElement(null);
		for(Procedure pr : ((Task) taskBOX.getSelectedItem()).getProcedures()) {
			procedureDCBM.addElement(pr);
			pr.addObserver(this);
		}
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Project) {
            fillTask();
        } else if (o instanceof Task) {
            fillProcedure();
        }
    }
}
