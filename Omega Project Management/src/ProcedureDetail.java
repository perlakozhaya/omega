public abstract class ProcedureDetail {
	protected Procedure procedure;
	
	public ProcedureDetail(Procedure procedure) {
		this.procedure = procedure;
	}
	
	public abstract double procedureCost();

}
