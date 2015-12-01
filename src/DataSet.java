import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;



public class DataSet {
		
	//ping for X times per time step combine into 1 mean result.
	public static ArrayList<Monitor> makeMeanValuesOfMesurement(ArrayList<Monitor> monitors){
		ArrayList<Monitor> tmpmonitors = new ArrayList<Monitor>();
		int numMonitor = -1;
		for (Monitor each: monitors){
			boolean matched = false;
			for (Monitor tmpm: tmpmonitors){
				if(each.getHOST().equals(tmpm.getHOST()) //)
				&&(each.getTimeStep()==tmpm.getTimeStep()))
				{
					matched = true;
					// THIS IS ERROR. FIX IT!!!
						tmpmonitors.get(numMonitor).setTTL( (each.getTTL()+ tmpmonitors.get(numMonitor).getTTL())/2 ); 
						tmpmonitors.get(numMonitor).setRTT( (each.getRTT()+ tmpmonitors.get(numMonitor).getRTT())/2 );
					// THIS IS WRONG COMPUTATION OF AVARAGE!!!!
				}
			}
			if (matched == false){
				tmpmonitors.add(each);
				numMonitor++;
			}
		}
		return tmpmonitors; 
	}
	
	//This function will help you
	
	public static ArrayList<Monitor> makeIntervalValues(ArrayList<Monitor> monitors){
		ArrayList<Monitor> tmpmonitors = new ArrayList<Monitor>();
		int numMonitor = -1;
		for (Monitor each: monitors){
			boolean matched = false;
			for (Monitor tmpm: tmpmonitors){
				if(each.getHOST().equals(tmpm.getHOST()) //)
				&&(each.getTimeStep()==tmpm.getTimeStep()))
				{
					matched = true;
					// THIS IS ERROR. FIX IT!!!
						tmpmonitors.get(numMonitor).setTTL( (each.getTTL()+ tmpmonitors.get(numMonitor).getTTL())/2 ); 
						tmpmonitors.get(numMonitor).setRTT( (each.getRTT()+ tmpmonitors.get(numMonitor).getRTT())/2 );
					// THIS IS WRONG COMPUTATION OF AVARAGE!!!!
				}
			}
			if (matched == false){
				tmpmonitors.add(each);
				numMonitor++;
			}
		}
		return tmpmonitors; 
	}
	
	public static ArrayList<ArrayList<Monitor>> timeStepsFor(ArrayList<Monitor> listMonitors){
		int currentTimeStep = 1;
		
		ArrayList<ArrayList<Monitor>> timeSteps = new ArrayList<ArrayList<Monitor>>() ;
		ArrayList<Monitor> tmpMonitorSetForOneTimeStep = new ArrayList<Monitor>() ;
		
		
		for (Monitor monitor: listMonitors){
			if (monitor.getTimeStep() == currentTimeStep){
				tmpMonitorSetForOneTimeStep.add(monitor);
			}else{
				timeSteps.add(new ArrayList<Monitor>(tmpMonitorSetForOneTimeStep));
				tmpMonitorSetForOneTimeStep.clear();
				tmpMonitorSetForOneTimeStep.add(monitor);
				currentTimeStep++;	
				}
		}
		return timeSteps;	
	}
	

	//on input we have arrays of timesteps for all monitors.
	//i.e we have one array with number of monitors. and embed array of time intervals for each monitor.
	public static Map<Integer, Monitor> ttlRttDstPerMonitorsQueues(ArrayList<Map<String,Monitor>> queues){
																   
		// ArrayList<ArrayList<ArrayList<String>>> Monitors =  new ArrayList<ArrayList<ArrayList<String>>>();
		Map<Integer, Monitor> listMonitors = new HashMap<Integer, Monitor>();
		int cntMonitor = 0;
		for (Map<String,Monitor> monitor : queues){
			double sumTTL = 0;
			double sumRTT = 0.0;
			int cntElemInArr = 0;
			ArrayList<Integer> arrTTL = new ArrayList<Integer>();
			ArrayList<Double> arrRTT = new ArrayList<Double>();
			
			for ( Entry<String, Monitor> entry : monitor.entrySet()){
				 Monitor timeStep = entry.getValue();
				 sumRTT += timeStep.getRTT();
				 sumTTL += (double)timeStep.getTTL();
				 arrTTL.add(timeStep.getTTL());
				 arrRTT.add(timeStep.getRTT());
				 cntElemInArr++;
			}
			
			//possible error according to the counter that increments in the func listMonitors
			listMonitors.put(cntMonitor++, 
					new Monitor(sumRTT / cntElemInArr, 
								sumTTL / cntElemInArr,
								Calc.getStandartDeviation(Modify.arrDoubleToString(arrRTT)),
								Calc.getStandartDeviation(Modify.arrIntToString(arrTTL))
								));
			
		}
		return listMonitors; 
	}
	
	 
	public static ArrayList<HashMap<Integer,Monitor>> destCompareTTLRTT (ArrayList<HashMap<Integer,Monitor>> listOfNotifyChanges){
		for (HashMap<Integer,Monitor> monitorsList:listOfNotifyChanges){
			for (int i=0;i<monitorsList.size();i++){
				if ((monitorsList.get(i).getTTL() == 1) &&
					(monitorsList.get(i).getRTT() == 1) &&
					monitorsList.get(i).getDESTINATION().equals("1")){
					monitorsList.get(i).setSuccess(1);
				}
			}
		}
		
		return listOfNotifyChanges;
	}
	
	public static ArrayList<Integer> TTLPerMonitor(Monitor desireMonitor, ArrayList<ArrayList<Monitor>> timeSteps){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for ( ArrayList<Monitor> monitorsPerTimeStep : timeSteps){
			boolean matched = false;
			for( Monitor monitor :  monitorsPerTimeStep){
			 if ( monitor.getHOST().equals(desireMonitor.getHOST()) ){
				 arr.add(monitor.getTTL());
				 matched = true;
			 }
			}
			if (matched == false){
				 arr.add(0);
			}
		}
				
		return arr;
	}
	
	public static ArrayList<Double> RTTPerMonitor(Monitor desireMonitor, ArrayList<ArrayList<Monitor>> timeSteps){
		ArrayList<Double> arr = new ArrayList<Double>();
		for ( ArrayList<Monitor> monitorsPerTimeStep : timeSteps){
			boolean matched = false;
			for( Monitor monitor : monitorsPerTimeStep){
			 if ( monitor.getHOST().equals(desireMonitor.getHOST()) ){
				 arr.add(monitor.getRTT());
				 matched = true;
			 }
			}
			if (matched == false){
				 arr.add(0.0);
			}
		}
				
		return arr;
	}
	
	public static ArrayList<String> DESTPerMonitor(Monitor desireMonitor, ArrayList<ArrayList<Monitor>> timeSteps){
		ArrayList<String> arr = new ArrayList<String>();
		for ( ArrayList<Monitor> monitorsPerTimeStep : timeSteps){
			boolean matched = false;
			for( Monitor monitor : monitorsPerTimeStep){
			 if ( monitor.getHOST().equals(desireMonitor.getHOST()) ){
				 arr.add(monitor.getDESTINATION());
				 matched = true;
			 }
			}
			if (matched == false){
				 arr.add("");
			}
		}
				
		return arr;
	}
	
	public static ArrayList<LinkedHashMap<Integer,Monitor>> timeStepForHashMap(ArrayList<ArrayList<Monitor>> timeSteps){
		ArrayList<LinkedHashMap<Integer,Monitor>> timeStepHashMap = new ArrayList<LinkedHashMap<Integer,Monitor>>();
		LinkedHashMap<Integer,Monitor> hashMapOfOneTimeStep = new LinkedHashMap<Integer,Monitor>();
		int counter = 0;
		for (ArrayList<Monitor> monitorsPerTimeStep : timeSteps){
			timeStepHashMap.add(new LinkedHashMap<Integer,Monitor>(hashMapOfOneTimeStep));
			for (Monitor monitor: monitorsPerTimeStep){
				hashMapOfOneTimeStep.put(counter,monitor);
			}
			counter ++;
		}
	return timeStepHashMap;
	}
	
	public static ArrayList<LinkedHashMap<Integer,Monitor>> notifyChanges(ArrayList<LinkedHashMap<Integer,Monitor>> finalTimeStep,double factor){
		LinkedHashMap<Integer, Monitor> monitorTableSdM = new LinkedHashMap<Integer, Monitor>();
		
		ArrayList<LinkedHashMap<Integer,Monitor>> slidingArray = new ArrayList<LinkedHashMap<Integer,Monitor>>();
		
		ArrayList<LinkedHashMap<Integer,Monitor>> listOfNotifyChanges = new ArrayList<LinkedHashMap<Integer,Monitor>>();
		LinkedHashMap<Integer, Monitor> notifyChanges = new LinkedHashMap<Integer, Monitor>();
		LinkedHashMap<Integer,Monitor> slidingHashMap = new LinkedHashMap<Integer,Monitor>();
		LinkedHashMap<Integer,Monitor> probeHashMap = new LinkedHashMap<Integer,Monitor>();
		ArrayList<LinkedHashMap<Integer,Monitor>> slidingArrayOfHashMapFiveTimeSteps = new ArrayList<LinkedHashMap<Integer,Monitor>>();
		ArrayList<ArrayList<Monitor>> slidingArrayOfArrayFiveTimeSteps = new ArrayList<ArrayList<Monitor>>();
		ArrayList<ArrayList<Monitor>> slidingArrayOfArrayFiveTimeStepsTranpose = new ArrayList<ArrayList<Monitor>>();
		ArrayList<LinkedHashMap<Integer,Monitor>> slidingArrayOfHashMapMonitors = new ArrayList<LinkedHashMap<Integer,Monitor>>();
		LinkedHashMap<Integer,Monitor> lastTimeStep = new LinkedHashMap<Integer,Monitor>();
		
		
		double deltaTTL=0.0;
		double deltaRTT=0.0;
		int i=0;
		int nbOfElements = 3; //number of elements in the sliding array, can be adjustable
		int timeStep = 0;
		int nbOfMonitors = 1;
		int keyOfHM = 1;
		
		//===================================//
		for (int c=1; c<(finalTimeStep.get(0).size()+1);c++){
			for (LinkedHashMap<Integer,Monitor> finalTimeStepScan : finalTimeStep){
				probeHashMap.put(nbOfMonitors, finalTimeStepScan.get(c));
				nbOfMonitors++;
			}
			nbOfMonitors=1;
			while (i>nbOfElements-1){
				//getting the lastest timestep value
				lastTimeStep = slidingArrayOfHashMapFiveTimeSteps.get(nbOfElements-1);
				
				
				slidingArrayOfArrayFiveTimeSteps = timeStepsFinal(slidingArrayOfHashMapFiveTimeSteps);
				
				slidingArrayOfArrayFiveTimeStepsTranpose = transposeTimeSteps(slidingArrayOfArrayFiveTimeSteps);
				
				slidingArrayOfHashMapMonitors = finalTimeStep(slidingArrayOfArrayFiveTimeStepsTranpose);
				double test2 = slidingArrayOfHashMapMonitors.get(1).get(1).getRTT();
				slidingHashMap= ttlRttDstPerMonitorsQueues(slidingArrayOfHashMapMonitors);
				double test = slidingHashMap.get(1).getTTL();
				
				for (int l=1;l<slidingHashMap.size();l++){
					double test = slidingHashMap.get(l).getTTL();
					deltaTTL = slidingHashMap.get(l).getTTL()-(double)probeHashMap.get(l).getTTL();
					deltaRTT = slidingHashMap.get(l).getRTT()-probeHashMap.get(l).getRTT();
					if (probeHashMap.get(l).getDESTINATION().equals(lastTimeStep.get(l).getHOST())){
						if (slidingHashMap.get(l).getSdRTT()*factor < Math.abs(deltaRTT)){
							if (slidingHashMap.get(l).getSdTTL()*factor < Math.abs(deltaTTL)){
								
								notifyChanges.put(l, new Monitor(null,1,1.0,Integer.toString(0),timeStep));
							}
							else {
								notifyChanges.put(l, new Monitor(null,0,1.0,Integer.toString(0),timeStep));
							}
						}
						else {
							if (slidingHashMap.get(l).getSdTTL()*factor < Math.abs(deltaTTL)){
								notifyChanges.put(l, new Monitor(null,1,0.0,Integer.toString(0),timeStep));
							}
							else {
								notifyChanges.put(l, new Monitor(null,0,0.0,Integer.toString(0),timeStep));
							}
						}
					}
					else {
						if (slidingHashMap.get(l).getSdRTT()*factor < Math.abs(deltaRTT)){
							if (slidingHashMap.get(l).getSdTTL()*factor < Math.abs(deltaTTL)){
								notifyChanges.put(l, new Monitor(null,1,1.0,Integer.toString(1),timeStep));
							}
							else {
								notifyChanges.put(l, new Monitor(null,0,1.0,Integer.toString(1),timeStep));
							}
						}
						else {
							if (slidingHashMap.get(l).getSdTTL()*factor < Math.abs(deltaTTL)){
								notifyChanges.put(l, new Monitor(null,1,0.0,Integer.toString(1),timeStep));
							}
							else {
								notifyChanges.put(l, new Monitor(null,0,0.0,Integer.toString(1),timeStep));
							}
						}
					}
					}
				lastTimeStep.clear();
				slidingArrayOfHashMapFiveTimeSteps.remove(0);
				listOfNotifyChanges.add(new LinkedHashMap<Integer, Monitor> (notifyChanges));
			}
			i++;
			slidingArrayOfHashMapFiveTimeSteps.add(new LinkedHashMap<Integer,Monitor>(probeHashMap));
			
			probeHashMap.clear();
			lastTimeStep.clear();
			slidingArrayOfArrayFiveTimeSteps.clear();
			slidingArrayOfArrayFiveTimeStepsTranpose.clear();
			slidingArrayOfHashMapMonitors.clear();
			notifyChanges.clear();
			slidingHashMap.clear();
		}
		
		return listOfNotifyChanges;
	}
}

