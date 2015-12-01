import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.*;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

public class MainProgram {
	
	public static void test(ArrayList<ArrayList<Monitor>> timeSteps){
		System.out.println("-------------------------------------------");
	//	System.out.println(timeSteps.get(0).get(0).getHOST());
		 ArrayList<Integer>  ttlList= DataSet.TTLPerMonitor(timeSteps.get(0).get(0),timeSteps);
		//for (int myTTL : ttlList){	
	//		System.out.println(myTTL);
			//System.out.println(monitor1.getRTT());
			//System.out.println(monitor1.getTTL());
			//System.out.println(monitor1.getTimeStep());
		//}
		
		 ArrayList<Double>  rttList= DataSet.RTTPerMonitor(timeSteps.get(0).get(0),timeSteps);
		 for (double myRTT : rttList){
		//		System.out.println(myRTT);
		 }	
		 ArrayList<String> desList = DataSet.DESTPerMonitor(timeSteps.get(0).get(1),timeSteps);	 
		 for (String myDEST : desList){
		//		System.out.println(myDEST);
		 }
		 ArrayList<Integer> notifyDest = Calc.notifyRouteChange(desList);
		 for (int nDest : notifyDest){
			//	System.out.println(nDest);	 
		 }
	}
	
	public static void main(String[] arg) throws Exception{
		ArrayList<Monitor> listMonitor = Parser.parse();
		LinkedHashMap<String,Monitor> lisOftMonitors = Parser.standardMonitorList();
//		Test.debugstandardMonitorList(lisOftMonitors);
		ArrayList<Monitor> meanValues =  DataSet.makeMeanValuesOfMesurement(listMonitor);
		ArrayList<ArrayList<Monitor>> timeSteps =  DataSet.timeStepsFor(meanValues);
		ArrayList<LinkedHashMap<Integer,Monitor>> timeStepsHashMap = DataSet.timeStepForHashMap(timeSteps);
//		Test.debugarrayToSlide(timeStepsHashMap);
		ArrayList<LinkedHashMap<Integer,Monitor>> timeStepHashMapFill = DataSet.timeStepHashMapFill(timeStepsHashMap,lisOftMonitors);
//		Test.debugarrayToSlide(timeStepHashMapFill);
		ArrayList<ArrayList<Monitor>> timeStepsFinal = DataSet.timeStepsFinal(timeStepHashMapFill);
//		Test.debugTimeSteps(timeStepsFinal);
		ArrayList<ArrayList<Monitor>> timeStepsNew = DataSet.transposeTimeSteps(timeStepsFinal);
//		ArrayList<ArrayList<Monitor>> timeStepsNew = DataSet.transposeTimeSteps(timeSteps);
//		Test.debugTimeSteps(timeStepsNew);
		ArrayList<LinkedHashMap<Integer,Monitor>> finalTimeStep = DataSet.finalTimeStep(timeStepsNew);
//		Test.debugarrayToSlide(finalTimeStep);
		ArrayList<LinkedHashMap<Integer,Monitor>> notifyChanges = DataSet.notifyChanges(finalTimeStep, 0.5);
//		Test.debugarrayToSlide(notifyChanges);
	}

}


//check of branches
//test branches2
//and one more test