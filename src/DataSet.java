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
	public static Map<Integer, Monitor> ttlRttDstPerMonitorsQueues(ArrayList<Map<Integer,Monitor>> queues){
																   
		// ArrayList<ArrayList<ArrayList<String>>> Monitors =  new ArrayList<ArrayList<ArrayList<String>>>();
		Map<Integer, Monitor> listMonitors = new HashMap<Integer, Monitor>();
		int cntMonitor = 0;
		for (Map<Integer,Monitor> monitor : queues){
			double sumTTL = 0;
			double sumRTT = 0.0;
			int cntElemInArr = 0;
			ArrayList<Integer> arrTTL = new ArrayList<Integer>();
			ArrayList<Double> arrRTT = new ArrayList<Double>();
			
			for ( Entry<Integer, Monitor> entry : monitor.entrySet()){
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
	
	 
//	public static ArrayList<HashMap<Integer,Monitor>> destCompareTTLRTT (ArrayList<HashMap<Integer,Monitor>> listOfNotifyChanges){
	public static int[] destCompareTTLRTT (ArrayList<HashMap<Integer,Monitor>> listOfNotifyChanges){
		int[] cntArr = new int[1];
		int numSuccess = 0;
		int numFalse = 0;
		
		for (HashMap<Integer,Monitor> monitor:listOfNotifyChanges){
			for (int i=0;i<monitor.size();i++){
				if ((monitor.get(i).getTTL() == 1) &&
					(monitor.get(i).getRTT() == 1) &&
					 monitor.get(i).getDESTINATION().equals("1")){
					//monitor.get(i).setSuccess(1);
					 numSuccess++;
				}else{
					if(((	monitor.get(i).getTTL() == 1) &&
						(	monitor.get(i).getRTT() == 1) &&
							monitor.get(i).getDESTINATION().equals("0")) ||
						((	monitor.get(i).getTTL() == 0) &&
						(	monitor.get(i).getRTT() == 0) &&
						 	monitor.get(i).getDESTINATION().equals("1"))	
						){
							numFalse++;
						}
					
				}
			}
		}
		cntArr[0] = numFalse;
		cntArr[1] = numSuccess;
		return cntArr;
//		return listOfNotifyChanges;
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
	
	
	public static ArrayList<LinkedHashMap<Integer,Monitor>> timeStepForHashMap(ArrayList<ArrayList<Monitor>> TimeSteps){
		ArrayList<LinkedHashMap<Integer,Monitor>> timeStepHashMap = new ArrayList<LinkedHashMap<Integer,Monitor>>();
		LinkedHashMap<Integer,Monitor> hashMapOfOneTimeStep = new LinkedHashMap<Integer,Monitor>();
		
		int key = 0;
		for (ArrayList<Monitor> monitorTimeSteps : TimeSteps){
			for (Monitor monitor: monitorTimeSteps){
				key++;
				hashMapOfOneTimeStep.put(key,monitor);
			}
			timeStepHashMap.add(new LinkedHashMap<Integer,Monitor>(hashMapOfOneTimeStep));
			hashMapOfOneTimeStep.clear();
			key=0;
		}
	return timeStepHashMap;
	}
		
	public static ArrayList<LinkedHashMap<Integer,Monitor>> timeStepHashMapFill(ArrayList<LinkedHashMap<Integer,Monitor>> timeStepForHashMap,
															LinkedHashMap<String,Monitor> standardMonitorList){
		ArrayList<LinkedHashMap<Integer,Monitor>> timeStepHashMapFill = new ArrayList<LinkedHashMap<Integer,Monitor>>();
		LinkedHashMap<Integer,Monitor> HashMapFill = new LinkedHashMap<Integer,Monitor>();
		boolean match=false;
		int keyInt=0;
//edit timestep form 1 to 0		
		int timeStep =0;
		int keyChange=0;
		for (LinkedHashMap<Integer,Monitor> timeStepForHashMapScan : timeStepForHashMap){
			for (String keyString : standardMonitorList.keySet()){
				for (int key : timeStepForHashMapScan.keySet()){
					if (timeStepForHashMapScan.get(key).getHOST().equals(keyString)){
						match = true;
						keyChange = key;
						HashMapFill.put(keyInt,timeStepForHashMapScan.get(keyChange));
					}
				}	

					if (match!=true){
						HashMapFill.put(keyInt,new Monitor(keyString,0,0.0,"NA",timeStep));
					}
				match=false;	
//					}
				keyInt++;
			}
			
			timeStepHashMapFill.add(new LinkedHashMap<Integer,Monitor>(HashMapFill));
			HashMapFill.clear();
			timeStep++;
			keyInt=0;
		}
		
		return timeStepHashMapFill;
	}
	
	public static ArrayList<ArrayList<Monitor>> timeStepsFinal (ArrayList<LinkedHashMap<Integer,Monitor>> timeStepForHashMap){
		ArrayList<ArrayList<Monitor>> timeStepsFinal = new ArrayList<ArrayList<Monitor>>();
		ArrayList<Monitor> monitor = new ArrayList<Monitor>();
		for (int a=0; a<timeStepForHashMap.size();a++){
			for (int key:timeStepForHashMap.get(a).keySet()){
				monitor.add(timeStepForHashMap.get(a).get(key));
			}
			timeStepsFinal.add(new ArrayList<Monitor>(monitor));
			monitor.clear();
		}
	return timeStepsFinal;
	}
	

	//transpose
	public static ArrayList<ArrayList<Monitor>> transposeTimeSteps (ArrayList<ArrayList<Monitor>> timeSteps){

		ArrayList<ArrayList<Monitor>> newTimeSteps = new ArrayList<ArrayList<Monitor>>();
//		int currentTimeStep=1;
		int rowCount=timeSteps.size();
		int colCount=0;
		
		for (int i=0;i<rowCount;i++){
			ArrayList<Monitor> row = timeSteps.get(i);
			int rowSize = row.size();
			if (rowSize > colCount){
				colCount = rowSize;
			}
		}
		for (int r=0;r<rowCount;r++){
			ArrayList<Monitor> innerIn = timeSteps.get(r);
			for (int c=0; c<colCount;c++){
				ArrayList<Monitor> matrixOutRow = new ArrayList<Monitor>();
				if (r!=0){
					try{
						matrixOutRow = newTimeSteps.get(c);
					}
					catch
						(java.lang.IndexOutOfBoundsException e){
//	                    System.out.println("Transposition error!\n"
//	                            + "could not get matrixOut at index "
//	                             + c + " - out of bounds" +e);
                    matrixOutRow.add(new Monitor("host",0,0.0,"NA",0));
					}
				}
				try{
					matrixOutRow.add(innerIn.get(c));
				}
				catch (java.lang.IndexOutOfBoundsException e){
	                matrixOutRow.add(new Monitor("host",0,0.0,"NA",0));
				}
				try{
					newTimeSteps.set(c,matrixOutRow);
				}
				catch (java.lang.IndexOutOfBoundsException e){
	                newTimeSteps.add(matrixOutRow);
	            }
				
			}
		}

		return newTimeSteps;
	}
	
	public static ArrayList<LinkedHashMap<Integer,Monitor>> finalTimeStep(ArrayList<ArrayList<Monitor>> transposeTimeSteps){
		ArrayList<LinkedHashMap<Integer,Monitor>> finalTimeStep = new ArrayList<LinkedHashMap<Integer,Monitor>>();
		LinkedHashMap<Integer,Monitor> finalTimeStepScan = new LinkedHashMap<Integer,Monitor>();
		for (ArrayList<Monitor> transposeTimeStepsScan : transposeTimeSteps){
			for (Monitor tranposeTimeStepsScanScan : transposeTimeStepsScan){
				finalTimeStepScan.put(tranposeTimeStepsScanScan.getTimeStep(), tranposeTimeStepsScanScan);
			}
		finalTimeStep.add(new LinkedHashMap<Integer,Monitor>(finalTimeStepScan));
		finalTimeStepScan.clear();
		}
		return finalTimeStep;
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

