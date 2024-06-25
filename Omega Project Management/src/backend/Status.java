package backend;

abstract class Status {
  final static String INCOMPLETE = "Incomplete";
  final static String EXECUTE = "Not Started";
  final static String ONGOING = "Ongoing";
  final static String DONE = "Done";
  private String currentStatus;
  
  Status(String status) {
    currentStatus = status;
  }
  
  public boolean changeStatus() {
    final String[] statusOrder = {INCOMPLETE, EXECUTE, ONGOING, DONE};
      String currentStatus = getCurrentStatus();
      
      for(int i = 0; i < statusOrder.length-1; i++) {
          if(statusOrder[i].equals(currentStatus)) {
            setCurrentStatus(statusOrder[i + 1]);
            return true;
          } 
      }
      return false;
  }

  abstract void updateOnStatus();
  
  public String getCurrentStatus() {
    return currentStatus;
  }
  public void setCurrentStatus(String currentStatus) {
    this.currentStatus = currentStatus;
  }
}
