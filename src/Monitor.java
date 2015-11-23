public class Monitor {
	protected String HOST;
	protected int TTL;
	protected double RTT;
	protected String DESTINATION;
	protected int timeStep;
	protected double meanRTT;
	protected int meanTTL;
	protected double sdRTT;
	protected double sdTTL;
	
	
	public Monitor(String HOST, int TTL, double RTT, String DESTINATION, int TimeStep){
		this.HOST = HOST;
		this.TTL = TTL;
		this.RTT = RTT;
		this.DESTINATION = DESTINATION;
		this.timeStep = TimeStep;
		
	}
	
	public Monitor(double meanRTT,int meanTTL,double sdRTT,double sdTTL){
		this.meanRTT =	meanRTT;
		this.meanTTL =	meanTTL;
		this.sdRTT	 =	sdRTT;
		this.sdTTL	 =	sdTTL;
	}
	
	public String toString() {
		return this.HOST + "\n" + this.TTL + "\n" + this.RTT + "\n" + this.DESTINATION;
	}


	public double getSdTTL(){
		return sdTTL;		
	}
	
	public void setSdTTL(double sdTTL){
		this.sdTTL = sdTTL;		
	}

	public double getSdRTT(){
		return sdRTT;		
	}
	public void setSdRTT(double sdRTT){
		this.sdRTT = sdRTT;		
	}

	public int getMeanTTL(){
		return meanTTL;		
	}
	public void setMeanTTL(int meanTTL){
		this.meanTTL = meanTTL;		
	}
	
	public double getMeanRTT(){
		return meanRTT;		
	}
	public void setMeanRTT(double meanRTT){
		this.meanRTT = meanRTT;		
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
