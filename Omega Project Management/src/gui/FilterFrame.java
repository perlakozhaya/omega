package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;

import backend.*;

@SuppressWarnings("serial")
public class FilterFrame extends JFrame {
	private JPanel procedureFilter, procedureDetail;
	private JPanel buttonsPanel;
	
	private DefaultComboBoxModel<Procedure> proceduresDCBM;
	private JComboBox<Procedure> proceduresCB;
	private JLabel procedureProject;
	private JLabel procedureTask;
	
	private JButton empButton, itemButton, logisticButton;
    private DefaultTableModel employeeDTM, itemDTM, logisticDTM;
    private JTable detailTable;

	private DataManager dataManager;
	
	public FilterFrame(DataManager dataManager) {
		this.dataManager = dataManager;
		
		setTitle("Filter Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(535, 10, 715, 420);
		setLayout(new GridLayout(1, 2));
		
		procedureFilter = new JPanel();
		procedureFilter.setLayout(null);
		procedureDetail = new JPanel(new BorderLayout());
		
		proceduresDCBM = new DefaultComboBoxModel<>();
		proceduresCB = new JComboBox<>(proceduresDCBM);
		proceduresCB.setBounds(10, 10, 100, 30);
		procedureFilter.add(proceduresCB);
		
		procedureProject = new JLabel("Project: ");
		procedureTask = new JLabel("Task: ");
		procedureProject.setBounds(10, 50, 100, 30);
		procedureTask.setBounds(120, 50, 100, 30);
		
		buttonsPanel = new JPanel(new GridLayout(0, 3));
	    procedureDetail.add(buttonsPanel, BorderLayout.NORTH);
	    
	    empButton = new JButton("Employees");
	    buttonsPanel.add(empButton);
	    
	    itemButton = new JButton("Items");
	    buttonsPanel.add(itemButton);
	    
	    logisticButton = new JButton("Logistics");
	    buttonsPanel.add(logisticButton);
	    
	    employeeDTM = new DefaultTableModel(fillEmployeeTable(), new String[]{"Employee", "Wage/Hour"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        detailTable = new JTable(employeeDTM);
        JScrollPane scrollPane = new JScrollPane(detailTable);
        procedureDetail.add(scrollPane, BorderLayout.CENTER);
		
		procedureFilter.add(procedureProject);
		procedureFilter.add(procedureTask);
		
		add(procedureFilter);
		add(procedureDetail);
		
		proceduresCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Procedure selectedProcedure = getSelectedProcedure();
				Task selectedProcedureTask = getProcedureTask(selectedProcedure);
				Project selectedProcedureProject = getProcedureProject(selectedProcedureTask);
				
				procedureProject.setText("Project: " + selectedProcedureProject.getProjectName());
				procedureTask.setText("Task: " + selectedProcedureTask.getTaskName());
			}
		});
		
		empButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailTable.setModel(employeeDTM);
            }
        });

		itemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemDTM = new DefaultTableModel(fillItemTable(), new String[]{"Item", "Cost/Unit"}) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                detailTable.setModel(itemDTM);
            }
        });

		logisticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logisticDTM = new DefaultTableModel(fillLogisticTable(), new String[]{"Logistic", "Cost/Unit"}) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                detailTable.setModel(logisticDTM);
            }
        });
	}
	
	public void fillProcedures() {
		Set<Procedure> allProcedures = dataManager.getAllProcedures();
		proceduresDCBM.removeAllElements();
		proceduresDCBM.addElement(null);
		if(!allProcedures.isEmpty()) {
			for(Procedure procedure : allProcedures) {
				proceduresDCBM.addElement(procedure);
			}
		}
	}
	
	public Task getProcedureTask(Procedure procedure) {
		Set<Task> allTasks = dataManager.getAllTasks();
		if(!allTasks.isEmpty()) {
			for(Task task : allTasks) {
				if(task.getProcedures().contains(procedure)) {
					return task;
				}
			}
		}
		return null;
	}
	
	public Project getProcedureProject(Task task) {
		Set<Project> allProjects = dataManager.getProjects();
		if(!allProjects.isEmpty()) {
			for(Project project : allProjects) {
				if(project.getTasks().contains(task)) {
					return project;
				}
			}
		}
		return null;
	}
	
	public Procedure getSelectedProcedure() {
		return (Procedure) proceduresCB.getSelectedItem();
	}
	
	public Object[][] fillEmployeeTable() {
		Set<Employee> employees = dataManager.getEmployees();
		Object[][] employeeTable = new Object[employees.size()][2];
		int index = 0;
		
		for (Employee employee : employees) {
			Object[] row = new Object[2];
			row[0] = employee;
			row[1] = employee.getSpecialty().getWagePerHour();
			employeeTable[index++] = row;
		}
		
		return employeeTable;
	}
	
	public Object[][] fillItemTable() {
		Set<Item> items = dataManager.getItems();
		Object[][] itemTable = new Object[items.size()][2];
		int index = 0;
		
		for (Item item : items) {
			Object[] row = new Object[2];
			row[0] = item;
			row[1] = item.getCostPerUnit();
			itemTable[index++] = row;
		}
		
		return itemTable;
	}
	
	public Object[][] fillLogisticTable() {
	    Set<Logistic> logistics = dataManager.getLogistics();
	    Object[][] logisticTable = new Object[logistics.size()][2];
	    int index = 0;

	    for (Logistic logistic : logistics) {
	        Object[] row = new Object[2];
	        row[0] = logistic;
	        row[1] = logistic.getCostPerUnit();
	        logisticTable[index++] = row;
	    }

	    return logisticTable;
	}
}