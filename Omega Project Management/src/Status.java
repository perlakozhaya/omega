abstract class Status{
	final protected String excute = "EXCUTE";
	final protected String running = "RUNNIG";
	final protected String done = "DONE";
	
	String currentStatus;
	Status(String status){
		currentStatus = status;
	}
	
	void changeStatus() {
		switch(currentStatus) {
		case excute: 
			currentStatus = running;
			break;
		case running:
			currentStatus = done;
		case done:
			break;
		default:
			System.err.println("Not Initialized Status");
		}
	}
}
