abstract class Status{
	final protected String execute = "EXECUTE";
	final protected String running = "RUNNING";
	final protected String done = "DONE";
	
	String currentStatus;
	Status(String status){
		currentStatus = status;
	}
	
	void changeStatus() {
		switch(currentStatus) {
		case execute: 
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
