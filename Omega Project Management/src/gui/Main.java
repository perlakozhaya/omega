package gui;

import backend.*;

public class Main {

	public static void main(String[] args) {
        DataManager dataManager = new DataManager();

        FormFrame formFrame = new FormFrame(dataManager);
        formFrame.setVisible(true);

        DisplayFrame displayFrame = new DisplayFrame(dataManager);
        displayFrame.setVisible(true);
    }
}