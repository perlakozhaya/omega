package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;

import backend.*;

@SuppressWarnings("serial")
public class LogisticContainer extends JPanel {
	private JScrollPane scrollPane;
	private JList<Logistic> logisticLST;
	private DefaultListModel<Logistic> logisticDLM;
	
	private JLabel quantityLB;
	private JTextField quantityFLD;
	
	private JButton logisticAddBTN;
	
	private DataManager dataManager;
	private FormFrame formFrame;
	
	public LogisticContainer(DataManager dataManager, FormFrame formFrame) {
		this.dataManager = dataManager;
		this.formFrame = formFrame;
		
		setLayout(null);
		setBackground(new Color(227, 227, 227));

		logisticDLM = new DefaultListModel<Logistic>();
		logisticLST = new JList<Logistic>(logisticDLM);
		logisticLST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	    scrollPane = new JScrollPane(logisticLST);
	    scrollPane.setBounds(0, 14, 220, 140);
	    add(scrollPane);
	    
	    quantityLB = new JLabel("Quantity");
	    quantityLB.setBounds(285, 15, 100, 13);
	    add(quantityLB);
	    
	    quantityFLD = new JTextField("0");
	    quantityFLD.setHorizontalAlignment(SwingConstants.CENTER);
	    quantityFLD.setBounds(240, 33, 140, 19);
	    add(quantityFLD);
	    quantityFLD.setColumns(10);
	    
	    logisticAddBTN = new JButton("Add Logistic");
	    logisticAddBTN.setBounds(240, 55, 140, 21);
	    add(logisticAddBTN);
	    
//		--------------------------------------------------------------------
        
        fillLogistic();
        
        logisticAddBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Procedure selectedProcedure = formFrame.getSelectedProcedure();
				Logistic selectedLogistic = getSelectedLogistic();
				handleLogistic(selectedProcedure, selectedLogistic);
		        fillLogistic();
			}
        });
        
        logisticLST.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				fillQuantity();
			}
        	
        });
	}
	
	public void fillQuantity() {
		Procedure selectedProcedure = formFrame.getSelectedProcedure();
		if (selectedProcedure == null) {
			setQuantityFLD("0");
            return;
        }
		Logistic selectedLogistic = getSelectedLogistic();
        boolean found = false;
        for (ProcedureLogistic pl : selectedProcedure.getLogistics()) {
            if (pl.getLogistic().equals(selectedLogistic)) {
                setQuantityFLD(pl.getQuantity() + "");
                found = true;
                break;
            }
        }
        if (!found) setQuantityFLD("0");
    }

	public void fillLogistic() {
    	Set<Logistic> logistics = dataManager.getLogistics();
    	logisticDLM.removeAllElements();
		logisticDLM.addElement(null);
		if(!logistics.isEmpty()) {
			for(Logistic l : dataManager.getLogistics()) {
				logisticDLM.addElement(l);
			}			
		}
	}
	
	public void handleLogistic(Procedure selectedProcedure, Logistic selectedLogistic) {
		if (selectedProcedure == null) {
            formFrame.showError("No procedure selected. Please select a procedure first.");
            return;
        }
		
		String quantityString = getQuantityFLD().trim();
		
		if(selectedLogistic == null) {
			formFrame.showError("Select logistic and try again");
			return;
		}
		
		if (quantityString.isEmpty()) {
        	formFrame.showError("Logistic quantity cannot be empty!");
        	return;
        }
        
        try {
            double quantity = formFrame.parsePositiveDouble(quantityString, "Quantity must be a positive number!");
            
    		boolean updated = dataManager.updateLogisticInProcedure(selectedProcedure, selectedLogistic, quantity);
    		if(!updated) {
    			dataManager.addLogisticToProcedure(selectedProcedure, selectedLogistic, quantity);
    		}
	    } catch (IllegalArgumentException ex) {
	        formFrame.showError("Quantity must be a positive number!");
	        return;
	    }
	}
	
	public Logistic getSelectedLogistic() {
		return (Logistic) logisticLST.getSelectedValue();
	}
	
	public String getQuantityFLD() {
		return quantityFLD.getText();
	}
	
	public void setQuantityFLD(String text) {
		quantityFLD.setText(text);
	}
}