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
		
        FormFrame formFrame = new FormFrame(dataManager);
        formFrame.setVisible(true);
        
        ProgressFrame progressFrame = new ProgressFrame(dataManager);
        progressFrame.setVisible(true);
    }
}