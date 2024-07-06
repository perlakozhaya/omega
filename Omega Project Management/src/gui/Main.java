package gui;

import java.io.*;

import backend.*;

public class Main {

	public static void main(String[] args) {
		File file = new File("src/data.dat");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Error creating file: " + ex.getMessage());
            }
        }
        
        // If failed to load DataManager from file, create new instance.
		DataManager dataManager = DataManager.loadDataFromFile(file);
		if (dataManager == null) {
            dataManager = new DataManager();
        }
//	    dataManager.addLogistic(new Logistic("Electricity Usage", 0.12)); // cost per kWh for manufacturing
//	    dataManager.addLogistic(new Logistic("Component Procurement", 5.00)); // cost per electronic component
//	    dataManager.addLogistic(new Logistic("PCB Manufacturing", 10.00)); // cost per printed circuit board
//	    dataManager.addLogistic(new Logistic("Assembly Labor", 20.00)); // cost per hour for assembly
//	    dataManager.addLogistic(new Logistic("Quality Assurance", 15.00)); // cost per hour for QA
//	    dataManager.addLogistic(new Logistic("Product Packaging", 1.00)); // cost per unit of packaging
//	    dataManager.addLogistic(new Logistic("Distribution Shipping", 7.50)); // cost per unit shipped
//	    dataManager.addLogistic(new Logistic("Software Development", 40.00)); // cost per hour for software
//
//	    dataManager.addItem(new Item("Resistor", 0.10)); // cost per resistor
//	    dataManager.addItem(new Item("Capacitor", 0.15)); // cost per capacitor
//	    dataManager.addItem(new Item("Microcontroller", 5.00)); // cost per microcontroller
//	    dataManager.addItem(new Item("PCB Board", 10.00)); // cost per PCB board
//	    dataManager.addItem(new Item("Solder", 0.05)); // cost per unit of solder
//	    dataManager.addItem(new Item("Plastic Enclosure", 2.50)); // cost per plastic enclosure
//	    dataManager.addItem(new Item("Power Supply Unit", 12.00)); // cost per power supply unit
//	    dataManager.addItem(new Item("Connectors", 0.20)); // cost per connector
//	    dataManager.addItem(new Item("Heat Sink", 1.00)); // cost per heat sink
//	    dataManager.addItem(new Item("LED Display", 3.00)); // cost per LED display
//	    dataManager.addItem(new Item("Buttons and Switches", 0.50)); // cost per button/switch
//	    dataManager.addItem(new Item("Cables and Wires", 0.30)); // cost per unit of cable/wire
//	    dataManager.addItem(new Item("Adhesives", 0.25)); // cost per unit of adhesive
//	    dataManager.addItem(new Item("Thermal Paste", 0.10)); // cost per unit of thermal paste
//	    dataManager.addItem(new Item("Packaging Material", 1.00)); // cost per unit of packaging material
//	    
//	 // Create specialties
//	    Specialty electricalEngineer = new Specialty("Electrical Engineer", 30.00);
//	    Specialty technician = new Specialty("Technician", 18.00);
//	    Specialty assemblyWorker = new Specialty("Assembly Worker", 15.00);
//	    Specialty productDesigner = new Specialty("Product Designer", 25.00);
//	    Specialty plasticMolder = new Specialty("Plastic Molder", 12.00);
//	    Specialty electricalTechnician = new Specialty("Electrical Technician", 18.00);
//	    Specialty softwareEngineer = new Specialty("Software Engineer", 28.00);
//	    Specialty qaInspector = new Specialty("QA Inspector", 20.00);
//	    Specialty packagingWorker = new Specialty("Packaging Worker", 12.00);
//	    Specialty shippingStaff = new Specialty("Shipping Staff", 10.00);
//
//	    // Add specialties to data manager
//	    dataManager.addSpecialty(electricalEngineer);
//	    dataManager.addSpecialty(technician);
//	    dataManager.addSpecialty(assemblyWorker);
//	    dataManager.addSpecialty(productDesigner);
//	    dataManager.addSpecialty(plasticMolder);
//	    dataManager.addSpecialty(electricalTechnician);
//	    dataManager.addSpecialty(softwareEngineer);
//	    dataManager.addSpecialty(qaInspector);
//	    dataManager.addSpecialty(packagingWorker);
//	    dataManager.addSpecialty(shippingStaff);
//
//	    // Create employees with assigned specialties
//	    dataManager.addEmployee(new Employee("Adel Khalaf", electricalEngineer));
//	    dataManager.addEmployee(new Employee("Lina Khoury", technician));
//	    dataManager.addEmployee(new Employee("Rami Nader", assemblyWorker));
//	    dataManager.addEmployee(new Employee("Jana Fawaz", productDesigner));
//	    dataManager.addEmployee(new Employee("Michel Harb", plasticMolder));
//	    dataManager.addEmployee(new Employee("Nadine Saliba", electricalTechnician));
//	    dataManager.addEmployee(new Employee("Elias Maalouf", softwareEngineer));
//	    dataManager.addEmployee(new Employee("Sara Haddad", qaInspector));
//	    dataManager.addEmployee(new Employee("George Moussa", packagingWorker));
//	    dataManager.addEmployee(new Employee("Leila Abi Khalil", shippingStaff));
//
//	    // Adding more employees with the same specialty
//	    dataManager.addEmployee(new Employee("Fadi Gerges", electricalEngineer));
//	    dataManager.addEmployee(new Employee("Nour Adib", electricalEngineer));
//	    dataManager.addEmployee(new Employee("Amal Daher", technician));
	    
        FormFrame formFrame = new FormFrame(dataManager);
        formFrame.setVisible(true);
        
        ProgressFrame progressFrame = new ProgressFrame(dataManager);
        progressFrame.setVisible(true);
        
        FilterFrame filterFrame = new FilterFrame(dataManager);
        filterFrame.setVisible(true);
    }
}