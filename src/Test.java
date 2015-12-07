import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;


public class Test {

	public static void debugTimeSteps(ArrayList<ArrayList<Monitor>> timeSteps){
	int timeslot = 1;
	for (ArrayList<Monitor> monitorsForOneTimeStep : timeSteps){
		System.out.println("-----------------------------------");
		System.out.println("-------TIME SLOT :" + timeslot );
		timeslot++;
		System.out.println(monitorsForOneTimeStep.get(1).getHOST());
		for(Monitor monitor : monitorsForOneTimeStep){
			//System.out.println(monitor.getTimeStep());		
			System.out.println(monitor.getHOST());
			//System.out.println(monitor.getRTT());
		}
	}
}

	public static void debugMonitorSet(ArrayList<Monitor> listMonitors){
	for (Monitor monitor1 : listMonitors){	
		System.out.println(monitor1.getHOST());
		//System.out.println(monitor1.getRTT());
		//System.out.println(monitor1.getTTL());
		System.out.println(monitor1.getTimeStep());
	}
}

	public static void debugnotifyChanges(ArrayList<HashMap<Integer,Monitor>> notifyChanges){
		int timeslot = 0;
		for (HashMap<Integer,Monitor> monitorsForOneTimeStep : notifyChanges){
			System.out.println("-----------------------------------");
			System.out.println("TIME SLOT :" + timeslot );
			timeslot++;
			System.out.println(monitorsForOneTimeStep.size());
			//test one monitor

			//test all monitors
			for (Entry<Integer, Monitor> entry : monitorsForOneTimeStep.entrySet()){
				System.out.println("Key " + entry.getKey() + " " + "Value " + " " + entry.getValue().getHOST()+ " " + entry.getValue().getTTL() + " " + entry.getValue().getRTT() + " " + entry.getValue().getDESTINATION());
			}
		}
	}
	public static void debugstandardMonitorList(HashMap<String,Monitor> standardMonitorList){
		Iterator iterator = standardMonitorList.keySet().iterator();
		while (iterator.hasNext()){
			String key = (String) iterator.next();
			Monitor value = standardMonitorList.get(key);
			System.out.println(key + " " + 
								value.getHOST()+ " " + 
								value.getTTL()+ " " + 
								value.getRTT()+ " " + 
								value.getDESTINATION()+ " " + 
								value.getTimeStep());
		}
	}
	

	public static void debugarrayToSlide(ArrayList<LinkedHashMap<Integer,Monitor>> arrayToSlide){
		int timeslot = 0;
		for (LinkedHashMap<Integer,Monitor> monitorsForOneTimeStep : arrayToSlide){
			System.out.println("-----------------------------------");
			System.out.println("TIME SLOT :" + timeslot );
			timeslot++;
			System.out.println(monitorsForOneTimeStep.size());
			//test one monitor

			//test all monitors
			for (Entry<Integer, Monitor> entry : monitorsForOneTimeStep.entrySet()){
				System.out.println("Key " + entry.getKey() + " " + "Value " + " " + entry.getValue().getHOST()+ " " + entry.getValue().getTTL() + " " + entry.getValue().getRTT() + " " + entry.getValue().getDESTINATION());
			}
		}
	}
}
