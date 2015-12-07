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
<<<<<<< HEAD
=======
	public static LinkedHashMap<Integer, Monitor> ttlRttDstPerMonitorsQueues(ArrayList<LinkedHashMap<Integer,Monitor>> queues){
																   
		// ArrayList<ArrayList<ArrayList<String>>> Monitors =  new ArrayList<ArrayList<ArrayList<String>>>();
		LinkedHashMap<Integer, Monitor> listMonitors = new LinkedHashMap<Integer, Monitor>();
		int cntMonitor = 0;
		for (LinkedHashMap<Integer,Monitor> monitor : queues){
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
>>>>>>> refs/remotes/origin/3-calculateMean-SD-ForTimePeriod(slidingArray)
	
	 
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
	
	//transpose-new function. Input is timeStepHashMapFill
	public static ArrayList<LinkedHashMap<Integer,Monitor>> transposeTimeStepsNew (ArrayList<LinkedHashMap<Integer,Monitor>> timeStepHashMapFill){
			ArrayList<LinkedHashMap<Integer,Monitor>> transposeTimeStepsNew = new ArrayList<LinkedHashMap<Integer,Monitor>>();
			LinkedHashMap<Integer,Monitor> hashMapOfTimeStepsForOneMonitor = new LinkedHashMap<Integer,Monitor>();
			for (int j=0;j<timeStepHashMapFill.size();j++){							//TS
				for (int i=0; i<timeStepHashMapFill.get(j).size();i++){				//MONITORS
					if (j==0){
						hashMapOfTimeStepsForOneMonitor.put(j,new Monitor(timeStepHashMapFill.get(j).get(i).getHOST(),
																			timeStepHashMapFill.get(j).get(i).getTTL(),
																			timeStepHashMapFill.get(j).get(i).getRTT(),
																			timeStepHashMapFill.get(j).get(i).getDESTINATION(),
																			timeStepHashMapFill.get(j).get(i).getTimeStep()));
						transposeTimeStepsNew.add(i,new LinkedHashMap<Integer,Monitor>(hashMapOfTimeStepsForOneMonitor));
						
					}
					else {
						hashMapOfTimeStepsForOneMonitor = transposeTimeStepsNew.get(i);
						hashMapOfTimeStepsForOneMonitor.put(j,new Monitor(timeStepHashMapFill.get(j).get(i).getHOST(),
																			timeStepHashMapFill.get(j).get(i).getTTL(),
																			timeStepHashMapFill.get(j).get(i).getRTT(),
																			timeStepHashMapFill.get(j).get(i).getDESTINATION(),
																			timeStepHashMapFill.get(j).get(i).getTimeStep()));

						transposeTimeStepsNew.set(i,hashMapOfTimeStepsForOneMonitor);

					}

				}

			}
			return transposeTimeStepsNew;
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
	
	public static ArrayList<HashMap<Integer,Monitor>> notifyChanges(ArrayList<LinkedHashMap<Integer,Monitor>> finalTimeStep,double factor){
		LinkedHashMap<Integer, Monitor> monitorTableSdM = new LinkedHashMap<Integer, Monitor>();
		
		ArrayList<LinkedHashMap<Integer,Monitor>> slidingArray = new ArrayList<LinkedHashMap<Integer,Monitor>>();
		
		ArrayList<HashMap<Integer,Monitor>> listOfNotifyChanges = new ArrayList<HashMap<Integer,Monitor>>();
		HashMap<Integer, Monitor> notifyChanges = new HashMap<Integer, Monitor>();
		LinkedHashMap<Integer,Monitor> slidingHashMap = new LinkedHashMap<Integer,Monitor>();
		
		
		LinkedHashMap<Integer,Monitor> probeHashMap = new LinkedHashMap<Integer,Monitor>();
		ArrayList<LinkedHashMap<Integer,Monitor>> slidingArrayOfHashMapFiveTimeSteps = new ArrayList<LinkedHashMap<Integer,Monitor>>();
		ArrayList<LinkedHashMap<Integer,Monitor>> slidingArrayOfHashMapMonitors = new ArrayList<LinkedHashMap<Integer,Monitor>>();
		LinkedHashMap<Integer,Monitor> lastTimeStep = new LinkedHashMap<Integer,Monitor>();
		
		
		double deltaTTL=0.0;
		double deltaRTT=0.0;
		int i=0;
		int nbOfElements = 3; //number of elements in the sliding array, can be adjustable
		int timeStep = 0;
		int nbOfMonitors = 0;
		int cnt=0;
		//===================================//
		for (int c=0; c<(finalTimeStep.get(0).size());c++){								//Scan thru 95 timesteps
			for (LinkedHashMap<Integer,Monitor> finalTimeStepScan : finalTimeStep){		//Scan thru each monitor in the monitor ArrayList
				probeHashMap.put(nbOfMonitors, finalTimeStepScan.get(c));
				nbOfMonitors++;
			}
			nbOfMonitors=0;
			i++;
			if ((i>nbOfElements) && (i<95)){
				slidingArrayOfHashMapMonitors = transposeTimeStepsNew(slidingArrayOfHashMapFiveTimeSteps);
				slidingHashMap= ttlRttDstPerMonitorsQueues(slidingArrayOfHashMapMonitors);
				for (int l=0;l<(slidingHashMap.size());l++){
				//	System.out.println(slidingHashMap.get(l).getSdRTT() + "SFLDSFS");
				//	System.out.println(slidingHashMap.get(l).getSdTTL() + "lfskdkk");
					deltaRTT = probeHashMap.get(l).getRTT() - slidingHashMap.get(l).getMeanRTT();
					deltaTTL = probeHashMap.get(l).getTTL() - slidingHashMap.get(l).getMeanTTL();
					if (probeHashMap.get(l).getDESTINATION().equals(slidingArrayOfHashMapMonitors.get(l).get(nbOfElements-1).getDESTINATION())){
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
				}		//end of FOR
				slidingArrayOfHashMapFiveTimeSteps.remove(0);
				listOfNotifyChanges.add(new LinkedHashMap<Integer, Monitor>(notifyChanges));
			}			//end of IF
			slidingArrayOfHashMapFiveTimeSteps.add(new LinkedHashMap<Integer,Monitor>(probeHashMap));
			probeHashMap.clear();
		}				//end of first FOR
		return listOfNotifyChanges;
	}
	


}

