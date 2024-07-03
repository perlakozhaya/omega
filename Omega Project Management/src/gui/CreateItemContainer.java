package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import backend.*;

@SuppressWarnings("serial")
public class CreateItemContainer extends JPanel {
	private JLabel itemNameLB, costPerUnitLB, emptyLB;
	private JTextField itemNameFLD, costPerUnitFLD;
	private JButton itemAddBTN;
	
	private FormFrame formFrame;
	private DataManager dataManager;
	private ItemContainer itemC;
	
	public CreateItemContainer(FormFrame formFrame, DataManager dataManager, ItemContainer itemC) {
		this.formFrame = formFrame;
		this.dataManager = dataManager;
		this.itemC = itemC;
		
	    setLayout(new GridLayout(2, 3, 10, 0));
	    
	    itemNameLB = new JLabel("Item Name");
	    itemNameLB.setHorizontalAlignment(SwingConstants.CENTER);
	    add(itemNameLB);
	    
	    costPerUnitLB = new JLabel("Cost per Unit");
	    costPerUnitLB.setHorizontalAlignment(SwingConstants.CENTER);
	    add(costPerUnitLB);
	    
	    emptyLB = new JLabel("              ");
	    emptyLB.setHorizontalAlignment(SwingConstants.CENTER);
	    add(emptyLB);
	    
	    itemNameFLD = new JTextField();
	    add(itemNameFLD);
	    
	    costPerUnitFLD = new JTextField();
	    add(costPerUnitFLD);
	    
	    itemAddBTN = new JButton("Add");
	    add(itemAddBTN);
	    	    
        itemAddBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createItem();
				itemNameFLD.setText("");
				costPerUnitFLD.setText("");
            }
        });
	}
	
	public void createItem() {
		String itemName = itemNameFLD.getText().trim();
	    String costString = costPerUnitFLD.getText().trim();

	    if (itemName.isEmpty()) {
	        formFrame.showError("Item name cannot be empty!\n");
	        return;
	    }

	    if (costString.isEmpty()) {
	        formFrame.showError("Please enter a cost per unit.\n");
	        return;
	    }
	    
	    try {
            double costPerUnit = formFrame.parsePositiveDouble(costString, "Cost must be a positive number!");
            
    		if(!dataManager.addItem(new Item(itemName, costPerUnit))) {
    			formFrame.showError("Item already exists.\n");
    			return;
    		}
    		itemC.fillItem();
    		
	    } catch (IllegalArgumentException ex) {
	        formFrame.showError("Cost must be a positive number!");
	        return;
	    }
	}
}