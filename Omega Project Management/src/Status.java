
abstract class Status {
	final protected String INCOMPLETE = "Incomplete";
	final protected String EXECUTE = "Execute";
	final protected String ONGOING = "Ongoing";
	final protected String DONE = "Done";
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