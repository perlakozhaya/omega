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
        setBounds(10, 200, 420, 450);
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
                    projectC.setProjectNameLabel("Create new Project");
                    projectC.setProjectName("Project Name...");
                    cardLayout.show(centerPanel, "Project");
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
        			cardLayout.show(centerPanel, "Task");
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
                    cardLayout.show(centerPanel, "Procedure");
                }
            }
        });
        
        applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Project project = (Project) projectBOX.getSelectedItem();
				Task task = (Task) taskBOX.getSelectedItem();
				Procedure procedure = (Procedure) procedureBOX.getSelectedItem();
				
				if (project == null && task == null && procedure == null) {
					String name;
					if((name = projectC.getProjectName().trim()) != "") {
						project = new Project(name, "Incomplete");
						
						fillProject();
					}
				}
			}
        });
        
        exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
        	
        });
        
//      ------------------------------------------------------------
        setJMenuBar(menuBar);
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
			p.addObserver(this);
		}
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
