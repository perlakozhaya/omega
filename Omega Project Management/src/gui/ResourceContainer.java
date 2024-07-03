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
public class ResourceContainer extends JPanel {
	private JScrollPane scrollPane;
	private JList<Resource> resourceLST;
	private DefaultListModel<Resource> resourceDLM;
	
	private JLabel quantityLB;
	private JTextField quantityFLD;
	
	private JButton resourceAddBTN;
	
	private DataManager dataManager;
	private FormFrame formFrame;
	
	public ResourceContainer(DataManager dataManager, FormFrame formFrame) {
		this.dataManager = dataManager;
		this.formFrame = formFrame;
		
		setLayout(null);
		setBackground(new Color(227, 227, 227));

		resourceDLM = new DefaultListModel<Resource>();
		resourceLST = new JList<Resource>(resourceDLM);
		resourceLST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	    scrollPane = new JScrollPane(resourceLST);
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
	    
	    resourceAddBTN = new JButton("Add Resource");
	    resourceAddBTN.setBounds(240, 55, 140, 21);
	    add(resourceAddBTN);
	    
//		--------------------------------------------------------------------
        
        fillResource();
        
        resourceAddBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Procedure selectedProcedure = formFrame.getSelectedProcedure();
				Resource selectedResource = getSelectedResource();
				handleResource(selectedProcedure, selectedResource);
		        fillResource();
			}
        });
        
        resourceLST.addListSelectionListener(new ListSelectionListener() {
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
		Resource selectedResource = getSelectedResource();
        boolean found = false;
        for (ProcedureResource pl : selectedProcedure.getResources()) {
            if (pl.getResource().equals(selectedResource)) {
                setQuantityFLD(pl.getQuantity() + "");
                found = true;
                break;
            }
        }
        if (!found) setQuantityFLD("0");
    }

	public void fillResource() {
    	Set<Resource> resources = dataManager.getResources();
    	resourceDLM.removeAllElements();
		resourceDLM.addElement(null);
		if(!resources.isEmpty()) {
			for(Resource l : dataManager.getResources()) {
				resourceDLM.addElement(l);
			}			
		}
	}
	
	public void handleResource(Procedure selectedProcedure, Resource selectedResource) {
		if (selectedProcedure == null) {
            formFrame.showError("No procedure selected. Please select a procedure first.");
            return;
        }
		
		String quantityString = getQuantityFLD().trim();
		
		if(selectedResource == null) {
			formFrame.showError("Select resource and try again");
			return;
		}
		
		if (quantityString.isEmpty()) {
        	formFrame.showError("Resource quantity cannot be empty!");
        	return;
        }
        
        try {
            double quantity = formFrame.parsePositiveDouble(quantityString, "Quantity must be a positive number!");
            
    		boolean updated = dataManager.updateResourceInProcedure(selectedProcedure, selectedResource, quantity);
    		if(!updated) {
    			dataManager.addResourceToProcedure(selectedProcedure, selectedResource, quantity);
    		}
	    } catch (IllegalArgumentException ex) {
	        formFrame.showError("Quantity must be a positive number!");
	        return;
	    }
	}
	
	public Resource getSelectedResource() {
		return (Resource) resourceLST.getSelectedValue();
	}
	
	public String getQuantityFLD() {
		return quantityFLD.getText();
	}
	
	public void setQuantityFLD(String text) {
		quantityFLD.setText(text);
	}
}