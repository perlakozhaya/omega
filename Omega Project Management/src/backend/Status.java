package backend;

abstract class Status {
	final static String INCOMPLETE = "Incomplete";
	final static String EXECUTE = "Execute";
	final static String ONGOING = "Ongoing";
	final static String DONE = "Done";
	private String currentStatus;
	
	Status(String status) {
		currentStatus = status;
	}
	
	abstract boolean changeStatus();

	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
}