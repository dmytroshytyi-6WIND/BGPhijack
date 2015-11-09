public class Monitor {
	protected String HOST;
	protected int TTL;
	protected double RTT;
	protected String DESTINATION;
	protected int timeStep;
	
	
	public Monitor(String HOST, int TTL, double RTT, String DESTINATION, int TimeStep){
		this.HOST = HOST;
		this.TTL = TTL;
		this.RTT = RTT;
		this.DESTINATION = DESTINATION;
		this.timeStep = TimeStep;
	}
	public String toString() {
		return this.HOST + "\n" + this.TTL + "\n" + this.RTT + "\n" + this.DESTINATION;
	}
	
	public void setHOST(String HOST){
		this.HOST = HOST;		
	}
	public void setTimeStep(int TimeStep){
		this.timeStep = TimeStep;		
	}
	
	public int getTimeStep(){
		return timeStep;		
	}
	public void setTTL(int TTL){
		this.TTL = TTL;
	}
	public void setRTT(double RTT){
		this.RTT = RTT;
	}
	public void setDESTINATION(String DESTINATION){
		this.DESTINATION = DESTINATION;
	}

	public String getHOST(){
		return HOST;		
	}
	public int getTTL(){
		return TTL;
	}
	public double getRTT(){
		return RTT;
	}
	public String getDESTINATION(){
		return DESTINATION;
	}
}
