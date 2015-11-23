import java.util.ArrayList;


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

	
}
