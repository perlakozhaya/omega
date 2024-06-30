package gui;

import backend.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class DisplayFrame extends JFrame implements Observer {
    private DataManager dataManager;
    private JTextArea displayArea;
    
    private DefaultComboBoxModel<Specialty> specialtiesDCBM;
    
    private JPanel contentPane;
    private JTextField txtCurrentTotal;
    private JTextField durationFLD;
    private JPanel centerPNL;
    private JTable table;

    public DisplayFrame(DataManager dataManager) {
        setTitle("Project Progress");
        setBounds(420, 200, 420, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.dataManager = dataManager;
        dataManager.addObserver(this);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(displayArea, BorderLayout.CENTER);
        
        update(null, null);
        
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 720, 420);
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//        setContentPane(contentPane);
//        contentPane.setLayout(new BorderLayout());
//
//        JPanel headerPNL = new JPanel();
//        headerPNL.setBackground(new Color(255, 255, 128));
//        headerPNL.setBorder(new EmptyBorder(5, 5, 5, 5));
//        contentPane.add(headerPNL, BorderLayout.NORTH);
//        headerPNL.setLayout(new GridLayout(1, 7));
//
//        JLabel costLB = new JLabel("COST");
//        costLB.setHorizontalAlignment(SwingConstants.CENTER);
//        headerPNL.add(costLB);
//
//        txtCurrentTotal = new JTextField();
//        txtCurrentTotal.setEditable(false);
//        txtCurrentTotal.setHorizontalAlignment(SwingConstants.CENTER);
//        txtCurrentTotal.setText("Current / Total");
//        headerPNL.add(txtCurrentTotal);
//
//        headerPNL.add(new JLabel());
//        headerPNL.add(new JLabel());
//        headerPNL.add(new JLabel());
//
//        JLabel durationLB = new JLabel("DURATION");
//        durationLB.setHorizontalAlignment(SwingConstants.CENTER);
//        headerPNL.add(durationLB);
//
//        durationFLD = new JTextField();
//        durationFLD.setHorizontalAlignment(SwingConstants.CENTER);
//        durationFLD.setEditable(false);
//        durationFLD.setText("Current / Total");
//        headerPNL.add(durationFLD);
//
//        centerPNL = new JPanel();
//        centerPNL.setBorder(new EmptyBorder(10, 5, 5, 5));
//        centerPNL.setBackground(new Color(128, 255, 255));
//        contentPane.add(centerPNL, BorderLayout.CENTER);
//        centerPNL.setLayout(null);
//
//        JComboBox<String> comboBox = new JComboBox<>();
//        comboBox.setBounds(319, 5, 100, 25);
//        centerPNL.add(comboBox);
//        
//        String[] columnNames = {"Task", "Procedure", "Cost", "Duration", "Status"};
//        Object[][] data = {
//            {"Task1", "Procedure1", 100.0, 1.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"},
//            {"Task2", "Procedure2", 200.0, 2.0, "EXECUTE"}
//        };
//
//        DefaultTableModel tableM = new DefaultTableModel(data, columnNames) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false; // Make cells non-editable
//            }
//        };
//
//        table = new JTable(tableM);
//        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//
//        // Add the ListSelectionListener to the table
//        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent event) {
//                if (event.getValueIsAdjusting()) return;
//
//                int selectedRow = table.getSelectedRow();
//                int selectedColumn = table.getSelectedColumn();
//
//                if (selectedRow != -1 && selectedColumn == table.getColumn("Status").getModelIndex()) {
//                    String currentStatus = (String) table.getValueAt(selectedRow, selectedColumn);
//                        String newStatus = dataManager.changeStatus(currentStatus);
//                        table.setValueAt(newStatus, selectedRow, selectedColumn);
//
//                    // Reset the selection (optional)
//                    table.clearSelection();
//                }
//            }
//        });
//
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(5, 35, 685, 305);
//        centerPNL.add(scrollPane);
    }

    @Override
    public void update(Observable o, Object arg) {
        StringBuilder displayText = new StringBuilder();
        for (Project project : dataManager.getProjects()) {
            displayText.append("Project: ").append(project.getProjectName()).append("\n");
            for(Task task : project.getTasks()) {
                displayText.append("\tTask: ").append(task.getTaskName()).append("\n");
                for(Procedure procedure : task.getProcedures()) {
                    displayText.append("\tProcedure: ").append(procedure.getProcedureName()).append("\n");
                    for(ProcedureEmployee pe : procedure.getEmployees()) {
                    	displayText.append("\tEmployees: ").append(pe.getEmployee().getEmployeeCode()).append("\n");
                        displayText.append("\nWorked Hours: ").append(pe.getHoursWorked()).append("\n");
                    }
                }
            }
        }
        displayArea.setText(displayText.toString());
    }
}