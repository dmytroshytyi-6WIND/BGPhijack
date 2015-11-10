import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

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
		
		ArrayList<Monitor> listMonitor = Parser.parse("Z:/Eclipse/INF_570/LabPolyGroupFlapping/BGPHijack/");
		Test.debugMonitorSet(listMonitor);
		ArrayList<Monitor> listMonitorMeanValues = DataSet.makeMeanValuesOfMesurement(listMonitor);
		Test.debugMonitorSet(listMonitorMeanValues);
		ArrayList<ArrayList<Monitor>> timeSteps = DataSet.timeStepsFor(listMonitorMeanValues);		
		//debugTimeSteps(timeSteps);
		
		
		
		Graphic.createFrames(timeSteps);
		//test(timeSteps);
	
		ArrayList<Double>  rttList= DataSet.RTTPerMonitor(timeSteps.get(0).get(0),timeSteps);
		ArrayList<Integer>  ttlList= DataSet.TTLPerMonitor(timeSteps.get(0).get(0),timeSteps);

		ArrayList <String> withoutZerosRTT = Modify.deleteZerosFromArray(Modify.arrDoubleToString(rttList));
		ArrayList <String> withoutZerosTTL = Modify.deleteZerosFromArray(Modify.arrIntToString(ttlList));
/*		
		getStandartDeviation(deleteZerosFromArray(arrIntToString(TTLPerMonitor(timeSteps.get(0).get(0),timeSteps))));
		
		//print
		 for (String tmp : withoutZerosRTT){
				System.out.println(tmp);	 
		 }		 
		 System.out.println("----------------------------------------------");
		 System.out.println(getStandartDeviation(withoutZerosRTT));
		 System.out.println("----------------------------------------------");
		 
		
		 for (String tmp : withoutZerosTTL){
				System.out.println(tmp);	 
		 }		
		 System.out.println("----------------------------------------------");
		 System.out.println(getStandartDeviation(withoutZerosTTL));
*/
	}

}


