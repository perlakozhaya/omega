package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import backend.*;

@SuppressWarnings("serial")
public class ItemContainer extends JPanel {
	private JScrollPane scrollPane;
	private JList<Item> itemLST;
	private DefaultListModel<Item> itemDLM;
	private JLabel quantityLB;
	private JTextField quantityFLD;
	private JButton itemAddBTN, createItem;
	private JPanel cardPanel;
	private CreateItemContainer createItemC;
	
	private DataManager dataManager;
	private FormFrame formFrame;
	
	CardLayout cardLayout;
	
	public ItemContainer(DataManager dataManager, FormFrame formFrame) {
		this.dataManager = dataManager;
		this.formFrame = formFrame;
		
		setLayout(null);
		setBackground(new Color(227, 227, 227));

		itemDLM = new DefaultListModel<Item>();
		itemLST = new JList<Item>(itemDLM);
		itemLST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	    scrollPane = new JScrollPane(itemLST);
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
	    
	    itemAddBTN = new JButton("Add Item");
	    itemAddBTN.setBounds(240, 55, 140, 21);
	    add(itemAddBTN);
	    
	    createItem = new JButton("Create Item");
	    createItem.setBounds(240, 133, 140, 20);
	    add(createItem);
	    
	    createItemC = new CreateItemContainer(formFrame, dataManager, this);
	    
	    cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        cardPanel.setBounds(0, 170, 395, 50);
        cardPanel.add(new JPanel(), "Empty");
        cardPanel.add(createItemC, "CreateItem");
        add(cardPanel);
        
//		--------------------------------------------------------------------
        
        fillItem();
        
        itemAddBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Procedure selectedProcedure = formFrame.getSelectedProcedure();
				Item selectedItem = getSelectedItem();
				handleItem(selectedProcedure, selectedItem);
		        fillItem();
			}
        });
        
        itemLST.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				fillQuantity();
			}
        	
        });
        
        // Show Create Item Container on click of Create Item
        createItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "CreateItem");
                revalidate();
                repaint();
            }
        });
        
	}
	
	public void fillQuantity() {
		Procedure selectedProcedure = formFrame.getSelectedProcedure();
		if (selectedProcedure == null) {
			setQuantityFLD("0");
            return;
        }
		Item selectedItem = getSelectedItem();
        boolean found = false;
        for (ProcedureItem pi : selectedProcedure.getItems()) {
            if (pi.getItem().equals(selectedItem)) {
                setQuantityFLD(pi.getQuantity() + "");
                found = true;
                break;
            }
        }
        if (!found) setQuantityFLD("0");
    }

	public void fillItem() {
    	Set<Item> items = dataManager.getItems();
    	itemDLM.removeAllElements();
		itemDLM.addElement(null);
		if(!items.isEmpty()) {
			for(Item i : dataManager.getItems()) {
				itemDLM.addElement(i);
			}			
		}
	}
	
	public void handleItem(Procedure selectedProcedure, Item selectedItem) {
		if (selectedProcedure == null) {
            formFrame.showError("No procedure selected. Please select a procedure first.");
            return;
        }
		
		String quantityString = getQuantityFLD().trim();
		
		if(selectedItem == null) {
			formFrame.showError("Select an item and try again");
			return;
		}
		
		if (quantityString.isEmpty()) {
        	formFrame.showError("Item quantity cannot be empty!");
        	return;
        }
        
        try {
            double quantity = formFrame.parsePositiveDouble(quantityString, "Quantity must be a positive number!");
            
    		boolean updated = dataManager.updateItemInProcedure(selectedProcedure, selectedItem, quantity);
    		if(!updated) {
    			dataManager.addItemToProcedure(selectedProcedure, selectedItem, quantity);
    		}
	    } catch (IllegalArgumentException ex) {
	        formFrame.showError("Quantity must be a positive number!");
	        return;
	    }
	}
	
	public Item getSelectedItem() {
		return (Item) itemLST.getSelectedValue();
	}
	
	public String getQuantityFLD() {
		return quantityFLD.getText();
	}
	
	public void setQuantityFLD(String text) {
		quantityFLD.setText(text);
	}
	
	public JPanel getCardPanel() {
		return cardPanel;
	}
}