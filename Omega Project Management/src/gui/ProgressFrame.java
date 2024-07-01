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

@SuppressWarnings({ "serial", "deprecation" })
public class ProgressFrame extends JFrame implements Observer{
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
        headerPNL.setBackground(new Color(255, 255, 128));
        headerPNL.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.add(headerPNL, BorderLayout.NORTH);
        headerPNL.setLayout(new GridLayout(1, 7));

        costLB = new JLabel("COST");
        costLB.setHorizontalAlignment(SwingConstants.CENTER);
        headerPNL.add(costLB);

        costFLD = new JTextField();
        costFLD.setEditable(false);
        costFLD.setHorizontalAlignment(SwingConstants.CENTER);
        headerPNL.add(costFLD);

        headerPNL.add(new JLabel());
        headerPNL.add(new JLabel());
        headerPNL.add(new JLabel());

        durationLB = new JLabel("DURATION");
        durationLB.setHorizontalAlignment(SwingConstants.CENTER);
        headerPNL.add(durationLB);

        durationFLD = new JTextField();
        durationFLD.setHorizontalAlignment(SwingConstants.CENTER);
        durationFLD.setEditable(false);
        headerPNL.add(durationFLD);

        centerPNL = new JPanel();
        centerPNL.setBorder(new EmptyBorder(10, 5, 5, 5));
        centerPNL.setBackground(new Color(128, 255, 255));
        contentPane.add(centerPNL, BorderLayout.CENTER);
        centerPNL.setLayout(null);

        projectsDCBM = new DefaultComboBoxModel<Project>();
        
        projects = new JComboBox<>(projectsDCBM);
        projects.setBounds(319, 5, 100, 25);
        centerPNL.add(projects);
        
        tableDTM = new DefaultTableModel(addTableContent(), new String[] {"Task", "Procedure", "Cost", "Duration", "Status"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        
        table = new JTable(tableDTM);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(tableDTM);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 35, 685, 305);
        centerPNL.add(scrollPane);

//         Add the ListSelectionListener to the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        	@Override
        	public void valueChanged(ListSelectionEvent event) {
        		if (event.getValueIsAdjusting()) return;
        		
        		int selectedRow = table.getSelectedRow();
        		int selectedColumn = table.getSelectedColumn();
        		
        		if (selectedRow != -1 && selectedColumn == table.getColumn("Status").getModelIndex()) {
//        			String currentStatus = (String) table.getValueAt(selectedRow, selectedColumn);
        			String newStatus = dataManager.changeStatus(getProcedureCell());
        			table.setValueAt(newStatus, selectedRow, selectedColumn);
        			
        			// Reset the selection (optional)
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
		
		for(Project p : dataManager.getProjects()) {
			projectsDCBM.addElement(p);
		}
		
		projects.setSelectedItem(project);
	}
	
	public double totalProjectCost() {
		if(projects.getSelectedItem() != null) {
			Project p = (Project) projects.getSelectedItem();
			return p.projectCost();
		}else {
			return 0.0;
		}
	}

	public double currentProjectCost() {
		if(projects.getSelectedItem() != null) {
			Project p = (Project) projects.getSelectedItem();
			return p.currentProjectCost();
		}else {
			return 0.0;
		}
	}

	public double totalProjectDuration() {
		if(projects.getSelectedItem() != null) {
			Project p = (Project) projects.getSelectedItem();
			return p.projectDuration();
		}else {
			return 0.0;
		}
	}
	
	public Object[][] addTableContent() {
		
		if(projects.getSelectedItem() != null) {
			
		    Project selectedProject = (Project) projects.getSelectedItem();
		    Set<Task> tasks = selectedProject.getTasks();
		    ArrayList<Object[]> list = new ArrayList<>();
	
		    for (Task task : tasks) {
		        Set<Procedure> procedures = task.getProcedures();
	
		        for (Procedure procedure : procedures) {
		            Object[] row = new Object[5];
		            row[0] = task;
		            row[1] = procedure;
		            row[2] = procedure.procedureCost();
		            row[3] = procedure.getProcedureDuration();
		            row[4] = procedure.getStatus();
	
		            list.add(row);
		        }
		    }
		    return list.toArray(new Object[0][]);
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
		durationFLD.setText(0.0 + " / " + totalProjectDuration());

		fillProjectBox();
		
		tableDTM.setDataVector(addTableContent(), new String[] {"Task", "Procedure", "Cost", "Duration", "Status"});
        
	}

}
