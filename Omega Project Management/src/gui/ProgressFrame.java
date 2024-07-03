package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import backend.*;

@SuppressWarnings("serial")
public class ProgressFrame extends JFrame implements Observer {
    private DataManager dataManager;

    private JPanel contentPane, headerPNL, centerPNL;
    private JTextField costFLD, durationFLD;
    private JLabel costLB, durationLB;

    private JTable table;
    private JComboBox<Project> projects;

    private DefaultTableModel tableDTM;
    private DefaultComboBoxModel<Project> projectsDCBM;

    public ProgressFrame(DataManager dataManager) {
        this.dataManager = dataManager;
        dataManager.addObserver(this);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 720, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        headerPNL = new JPanel();
        headerPNL.setBackground(Color.LIGHT_GRAY);
        headerPNL.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.add(headerPNL, BorderLayout.NORTH);
        headerPNL.setLayout(new GridLayout(1, 6));

        costLB = new JLabel("COST");
        costLB.setHorizontalAlignment(SwingConstants.CENTER);
        costLB.setFont(new Font("Serif", Font.PLAIN, 18));
        headerPNL.add(costLB);

        costFLD = new JTextField();
        costFLD.setEditable(false);
        costFLD.setHorizontalAlignment(SwingConstants.CENTER);
        costFLD.setFont(new Font("Serif", Font.PLAIN, 18));
        headerPNL.add(costFLD);

        headerPNL.add(new JLabel());
        headerPNL.add(new JLabel());

        durationLB = new JLabel("DURATION");
        durationLB.setHorizontalAlignment(SwingConstants.CENTER);
        durationLB.setFont(new Font("Serif", Font.PLAIN, 18));
        headerPNL.add(durationLB);

        durationFLD = new JTextField();
        durationFLD.setHorizontalAlignment(SwingConstants.CENTER);
        durationFLD.setEditable(false);
        durationFLD.setFont(new Font("Serif", Font.PLAIN, 18));
        headerPNL.add(durationFLD);

        centerPNL = new JPanel();
        centerPNL.setBorder(new EmptyBorder(10, 5, 5, 5));
        contentPane.add(centerPNL, BorderLayout.CENTER);
        centerPNL.setLayout(null);

        projectsDCBM = new DefaultComboBoxModel<Project>();

        projects = new JComboBox<>(projectsDCBM);
        projects.setBounds(319, 5, 100, 25);
        projects.setFont(new Font("Serif", Font.PLAIN, 18));
        centerPNL.add(projects);

        tableDTM = new DefaultTableModel(addTableContent(), new String[]{"Task", "Procedure", "Cost", "Duration", "Status"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };

        table = new JTable(tableDTM);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("Serif", Font.PLAIN, 18));
        table.setModel(tableDTM);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 35, 685, 305);
        centerPNL.add(scrollPane);

        // Add the ListSelectionListener to the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (event.getValueIsAdjusting()) return;

                int selectedRow = table.getSelectedRow();
                int selectedColumn = table.getSelectedColumn();

                if (selectedRow != -1 && selectedColumn == table.getColumn("Status").getModelIndex()) {
                    String newStatus = dataManager.changeStatus(getProcedureCell());
                    table.setValueAt(newStatus, selectedRow, selectedColumn);

                    update(null, null);

                    table.clearSelection();
                }
            }
        });

        projects.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update(null, null);
            }
        });

        update(null, null);
    }

    public void fillProjectBox() {
        Project project = (Project) projects.getSelectedItem();

        projectsDCBM.removeAllElements();
        projectsDCBM.addElement(null);

        for (Project p : dataManager.getProjects()) {
            projectsDCBM.addElement(p);
        }

        projects.setSelectedItem(project);
    }

    public double totalProjectCost() {
        if (projects.getSelectedItem() != null) {
            Project p = (Project) projects.getSelectedItem();
            return p.projectCost();
        } else {
            return 0.0;
        }
    }

    public double currentProjectCost() {
        if (projects.getSelectedItem() != null) {
            Project p = (Project) projects.getSelectedItem();
            return p.currentProjectCost();
        } else {
            return 0.0;
        }
    }

    public double totalProjectDuration() {
        if (projects.getSelectedItem() != null) {
            Project p = (Project) projects.getSelectedItem();
            return p.projectDuration();
        } else {
            return 0.0;
        }
    }

    public double currentProjectDuration() {
        if (projects.getSelectedItem() != null) {
            Project p = (Project) projects.getSelectedItem();
            return p.currentProjectDuration();
        } else {
            return 0.0;
        }
    }

    public Object[][] addTableContent() {

        if (projects.getSelectedItem() != null) {

            Project selectedProject = (Project) projects.getSelectedItem();
            Set<Task> tasks = selectedProject.getTasks();

            ArrayList<Object[]> executeList = new ArrayList<>();
            ArrayList<Object[]> ongoingList = new ArrayList<>();
            ArrayList<Object[]> doneList = new ArrayList<>();

            for (Task task : tasks) {
                Set<Procedure> procedures = task.getProcedures();

                for (Procedure procedure : procedures) {
                    Object[] row = new Object[5];
                    row[0] = task;
                    row[1] = procedure;
                    row[2] = procedure.procedureCost();
                    row[3] = procedure.getProcedureDuration();
                    row[4] = procedure.getStatus();

                    String status = (String) row[4];
                    switch (status) {
                        case "Execute":
                            executeList.add(row);
                            break;
                        case "Ongoing":
                            ongoingList.add(row);
                            break;
                        case "Done":
                            doneList.add(row);
                            break;
                        default:
                            break;
                    }
                }
            }

            ArrayList<Object[]> combinedList = new ArrayList<>();
            combinedList.addAll(executeList);
            combinedList.addAll(ongoingList);
            combinedList.addAll(doneList);

            return combinedList.toArray(new Object[0][]);
        }
        return null;
    }

    public Procedure getProcedureCell() {
        int rowIndex = table.getSelectedRow();
        int columnIndex = 1;

        Procedure procedure = (Procedure) tableDTM.getValueAt(rowIndex, columnIndex);

        return procedure;
    }

    @Override
    public void update(Observable o, Object arg) {
        costFLD.setText(currentProjectCost() + " / " + totalProjectCost());
        durationFLD.setText(currentProjectDuration() + " / " + totalProjectDuration());

        fillProjectBox();
        
        tableDTM.setDataVector(addTableContent(), new String[]{"Task", "Procedure", "Cost", "Duration", "Status"});
    }
}
