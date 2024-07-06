package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.*;

@SuppressWarnings("serial")
public class ProcedureContainer extends JPanel {
	private JLabel procedureNameLB, procedureDurationLB;
	private JTextField procedureNameFLD, procedureDurationFLD;
    private JButton procedureApplyBTN;
	
	private JPanel detailsC, buttonsPanel, centerPanel;
	private JButton empButton, itemButton, logisticButton;
	private EmployeeContainer employeeC; 
	private ItemContainer itemC; 
	private LogisticContainer logisticC; 
	private CardLayout cardLayout;
	
	private DataManager dataManager;
    private FormFrame formFrame;
		
	public ProcedureContainer(DataManager dataManager, FormFrame formFrame) {
		this.dataManager = dataManager;
        this.formFrame = formFrame;
	    setLayout(null);
	    
	    procedureNameLB = new JLabel("Create new Procedure");
	    procedureNameLB.setBounds(80, 6, 130, 13);
	    
	    procedureNameFLD = new JTextField("Procedure Name...");
	    procedureNameFLD.setBounds(80, 22, 130, 25);

	    procedureDurationLB = new JLabel("Duration/h");
	    procedureDurationLB.setBounds(230, 6, 130, 13);
	    
	    procedureDurationFLD = new JTextField("0");
	    procedureDurationFLD.setBounds(230, 22, 130, 25);
	    
	    procedureApplyBTN = new JButton("Apply");
        procedureApplyBTN.setBounds(380, 22, 65, 25);
	    
	    add(procedureNameLB);
	    add(procedureNameFLD);
	    add(procedureDurationLB);
	    add(procedureDurationFLD);
	    add(procedureApplyBTN);
	    
	    detailsC = new JPanel();
	    detailsC.setBackground(new Color(181, 181, 181));
	    detailsC.setBounds(40, 53, 445, 310);
	    detailsC.setLayout(new BorderLayout());
	    add(detailsC);
	    
	    buttonsPanel = new JPanel();
	    buttonsPanel.setLayout(new GridLayout(0, 3));
	    detailsC.add(buttonsPanel, BorderLayout.NORTH);
	    
	    empButton = new JButton("Employees");
	    buttonsPanel.add(empButton);
	    
	    itemButton = new JButton("Items");
	    buttonsPanel.add(itemButton);
	    
	    logisticButton = new JButton("Logistics");
	    buttonsPanel.add(logisticButton);
	    
	    setCenterPanel(new JPanel());
	    centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    detailsC.add(centerPanel, BorderLayout.CENTER);
	    
	    cardLayout = new CardLayout();
	    centerPanel.setLayout(cardLayout);
	    
	    employeeC = new EmployeeContainer(dataManager, formFrame);
	    itemC = new ItemContainer(dataManager, formFrame);
	    logisticC = new LogisticContainer(dataManager, formFrame);

	    centerPanel.add(employeeC, "Employee Container");
	    centerPanel.add(itemC, "Item Container");
	    centerPanel.add(logisticC, "Logistic Container");
	    	    
	    procedureApplyBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Task selectedTask = formFrame.getSelectedTask();
				Procedure selectedProcedure = formFrame.getSelectedProcedure();
				handleProcedure(selectedTask, selectedProcedure);
			}
	    });
	    
	    empButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(centerPanel, "Employee Container");
			}   	
	    });

	    itemButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		cardLayout.show(centerPanel, "Item Container");
	    	}   	
	    });

	    logisticButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		cardLayout.show(centerPanel, "Logistic Container");
	    	}   	
	    });
	}
	
    private void handleProcedure(Task selectedTask, Procedure selectedProcedure) {
        String procedureName = getProcedureField().trim();
        String durationString = getDurationField().trim();
        
        if (procedureName.isEmpty() || durationString.isEmpty()) {
        	formFrame.showMessage("Procedure name/duration empty!");
        	return;
        }
        
        try {
            double procedureDuration = formFrame.parsePositiveDouble(durationString, "Procedure Duration must be a positive number!");
            if (selectedProcedure == null) {
            	createProcedure(selectedTask, procedureName, procedureDuration);
            } else {
            	updateProcedure(selectedTask, selectedProcedure, procedureName, procedureDuration);
            }
	    } catch (IllegalArgumentException ex) {
	        formFrame.showMessage("Procedure Duration must be a positive number!");
	        return;
	    }
    }
    
    private void createProcedure(Task selectedTask, String procedureName, double procedureDuration) {
        if (!procedureName.equals("Procedure Name...") && procedureDuration != 0) {
            Procedure newProcedure = new Procedure(procedureName, procedureDuration);
            dataManager.addProcedureToTask(selectedTask, newProcedure);
    		formFrame.fillProcedure();
            formFrame.setSelectedProcedure(newProcedure);
        }
    }
    
    private void updateProcedure(Task selectedTask, Procedure selectedProcedure, String procedureName, double procedureDuration) {
        dataManager.updateProcedureInTask(selectedTask, selectedProcedure, procedureName, procedureDuration);
        setProcedureField(procedureName);
        setDurationField(String.valueOf(procedureDuration));
		formFrame.fillProcedure();
        formFrame.setSelectedProcedure(selectedProcedure);
    }
		
	public String getProcedureLabel() {
		return procedureNameLB.getText();
	}
	
	public void setProcedureLabel(String text) {
		procedureNameLB.setText(text);
	}
	
	public String getProcedureField() {
		return procedureNameFLD.getText();
	}

	public void setProcedureField(String text) {
		procedureNameFLD.setText(text);
	}
	
	public String getDurationLabel() {
		return procedureDurationLB.getText();
	}
	
	public void setDurationLabel(String text) {
		procedureDurationLB.setText(text);
	}
	
	public String getDurationField() {
		return procedureDurationFLD.getText();
	}
	
	public void setDurationField(String text) {
		procedureDurationFLD.setText(text);
	}
	
	public EmployeeContainer getEmployeeContainer() {
		return employeeC;
	}
	
	public ItemContainer getItemContainer() {
		return itemC;
	}
	
	public LogisticContainer getLogisticContainer() {
		return logisticC;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
}
