package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;

import backend.*;

@SuppressWarnings("serial")
public class FilterFrame extends JFrame implements Observer {
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

    private JCheckBox empCheckBox, itemCheckBox, logisticCheckBox;
    private DefaultListModel<String> listModel;
    private JList<String> list;
    private JLabel currentCostLabel;
    private JTextField currentCostField;
    
    public FilterFrame(DataManager dataManager) {
        this.dataManager = dataManager;
        dataManager.addObserver(this);
        
        setTitle("Filter Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(580, 10, 920, 420);
        setLayout(new GridLayout(1, 2));
        
        procedureFilter = new JPanel();
        procedureFilter.setLayout(null);
        procedureDetail = new JPanel(new BorderLayout());
        
        proceduresDCBM = new DefaultComboBoxModel<>();
        proceduresCB = new JComboBox<>(proceduresDCBM);
        proceduresCB.setBounds(10, 10, 180, 30);
        procedureFilter.add(proceduresCB);
        
        procedureProject = new JLabel("PROJECT: ");
        procedureTask = new JLabel("TASK: ");
        procedureProject.setBounds(10, 50, 200, 30);
        procedureTask.setBounds(220, 50, 200, 30);
        
        // Initialize checkboxes
        empCheckBox = new JCheckBox("Employees");
        itemCheckBox = new JCheckBox("Items");
        logisticCheckBox = new JCheckBox("Logistics");

        empCheckBox.setSelected(true);
        itemCheckBox.setSelected(true);
        logisticCheckBox.setSelected(true);
        
        // Set bounds for checkboxes
        empCheckBox.setBounds(10, 90, 100, 30);
        itemCheckBox.setBounds(170, 90, 100, 30);
        logisticCheckBox.setBounds(290, 90, 100, 30);

        // Add checkboxes to the panel
        procedureFilter.add(empCheckBox);
        procedureFilter.add(itemCheckBox);
        procedureFilter.add(logisticCheckBox);

        // Initialize and position the JList and its model
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setFont(new Font("Serif", Font.PLAIN, 16));
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setBounds(10, 130, 370, 150);
        procedureFilter.add(listScrollPane);

        // Initialize and position the current cost label and field
        currentCostLabel = new JLabel("Current cost:");
        currentCostLabel.setBounds(10, 310, 100, 30);
        currentCostField = new JTextField();
        currentCostField.setBounds(120, 310, 100, 30);
        currentCostField.setEditable(false);
        procedureFilter.add(currentCostLabel);
        procedureFilter.add(currentCostField);

        buttonsPanel = new JPanel(new GridLayout(0, 3));
        procedureDetail.add(buttonsPanel, BorderLayout.NORTH);
        
        empButton = new JButton("Employees");
        buttonsPanel.add(empButton);
        
        itemButton = new JButton("Items");
        buttonsPanel.add(itemButton);
        
        logisticButton = new JButton("Logistics");
        buttonsPanel.add(logisticButton);
        
        employeeDTM = new DefaultTableModel(fillEmployeeTable(), new String[]{"Employee", "Specialty", "Wage/Hour"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        itemDTM = new DefaultTableModel(fillItemTable(), new String[]{"Item", "Cost/Unit"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        logisticDTM = new DefaultTableModel(fillLogisticTable(), new String[]{"Logistic", "Cost/Unit"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        detailTable = new JTable(employeeDTM);
        detailTable.setRowHeight(25);
        detailTable.setFont(new Font("Serif", Font.PLAIN, 18));
        detailTable.getTableHeader().setFont(new Font("Serif", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(detailTable);
        procedureDetail.add(scrollPane, BorderLayout.CENTER);
        
        procedureFilter.add(procedureProject);
        procedureFilter.add(procedureTask);
        
        add(procedureFilter);
        add(procedureDetail);
        
        fillProcedures();
        
        proceduresCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProcedureParent();
                updateListAndCost();
            }
        });

        empCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateListAndCost();
            }
        });

        itemCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateListAndCost();
            }
        });

        logisticCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateListAndCost();
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
                detailTable.setModel(itemDTM);
            }
        });

        logisticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailTable.setModel(logisticDTM);
            }
        });
    }
    
    public void fillProcedures() {
    	Procedure p = (Procedure) proceduresDCBM.getSelectedItem();
        Set<Procedure> allProcedures = dataManager.getAllProcedures();
        proceduresDCBM.removeAllElements();
        proceduresDCBM.addElement(null);
        if(!allProcedures.isEmpty()) {
            for(Procedure procedure : allProcedures) {
                proceduresDCBM.addElement(procedure);
            }
        }
        proceduresDCBM.setSelectedItem(p);
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
    	if(task == null) return null;
    	
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
    
    public void updateProcedureParent() {
        Procedure selectedProcedure = (Procedure) proceduresCB.getSelectedItem();
        if (selectedProcedure != null) {
            Task selectedProcedureTask = getProcedureTask(selectedProcedure);
            Project selectedProcedureProject = getProcedureProject(selectedProcedureTask);

            if (selectedProcedureTask != null && selectedProcedureProject != null) {
                procedureProject.setText("PROJECT: " + selectedProcedureProject.getProjectName());
                procedureTask.setText("TASK: " + selectedProcedureTask.getTaskName());
            } else {
                procedureProject.setText("PROJECT: ");
                procedureTask.setText("TASK: ");
            }
        } else {
            procedureProject.setText("PROJECT: ");
            procedureTask.setText("TASK: ");
        }
    }

    public void updateListAndCost() {
        listModel.clear();
        double totalCost = 0.0;

        Procedure selectedProcedure = (Procedure) proceduresCB.getSelectedItem();
        if (selectedProcedure != null) {
            if (empCheckBox.isSelected()) {
                Set<ProcedureEmployee> employees = selectedProcedure.getEmployees();
                listModel.addElement("EMPLOYEES");
                for (ProcedureEmployee employee : employees) {
                    listModel.addElement(employee.getEmployee().getEmployeeCode() + ", Worked: " + employee.getHoursWorked());
                }
                totalCost += selectedProcedure.employeesCost();
            }
            if (itemCheckBox.isSelected()) {
                Set<ProcedureItem> items = selectedProcedure.getItems();
                listModel.addElement("ITEMS");
                for (ProcedureItem item : items) {
                    listModel.addElement(item.getItem().getItemName() + ", Quantity: " + item.getQuantity());
                }
                totalCost += selectedProcedure.itemsCost();
            }
            if (logisticCheckBox.isSelected()) {
                Set<ProcedureLogistic> logistics = selectedProcedure.getLogistics();
                listModel.addElement("LOGISTICS");
                for (ProcedureLogistic logistic : logistics) {
                    listModel.addElement(logistic.getLogistic().getLogisticName() + ", Unit: " + logistic.getQuantity());
                }
                totalCost += selectedProcedure.logisticsCost();
            }
        }

        currentCostField.setText(String.format("%.2f", totalCost));
    }

    public Object[][] fillEmployeeTable() {
        Set<Employee> employees = dataManager.getEmployees();
        Object[][] employeeTable = new Object[employees.size()][3];
        int index = 0;
        
        for (Employee employee : employees) {
            Object[] row = new Object[3];
            row[0] = employee;
            row[1] = employee.getSpecialty().getSpecialtyName();
            row[2] = employee.getSpecialty().getWagePerHour();
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

    @Override
    public void update(Observable o, Object arg) {
        fillProcedures();
        updateProcedureParent();
        updateListAndCost();
        employeeDTM.setDataVector(fillEmployeeTable(), new String[]{"Employee", "Specialty", "Wage/Hour"});
        itemDTM.setDataVector(fillItemTable(), new String[]{"Item", "Cost/Unit"});
        logisticDTM.setDataVector(fillLogisticTable(), new String[]{"Logistic", "Cost/Unit"});
    }
}
