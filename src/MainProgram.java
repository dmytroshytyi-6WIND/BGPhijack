import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

		ArrayList<Monitor> listMonitor = Parser.parse();											//	1.Parsing monitors from dataset
		LinkedHashMap<String,Monitor> lisOftMonitors = Parser.standardMonitorList();				//	2.Parsing monitors from flapping

	//	Test.debugstandardMonitorList(lisOftMonitors);												//	3.Test 2

		
		ArrayList<Monitor> meanValues =  DataSet.makeMeanValuesOfMesurement(listMonitor);			//	4.Making mean values 
		ArrayList<ArrayList<Monitor>> timeSteps =  DataSet.timeStepsFor(meanValues);				//	5.Dividing into timesteps
		ArrayList<LinkedHashMap<Integer,Monitor>> timeStepsHashMap = DataSet.timeStepForHashMap(timeSteps);			//	6.Converting from AL<AL> to AL<HM>
	//	Test.debugarrayToSlide(timeStepsHashMap);																	//	7.Test 6

		ArrayList<LinkedHashMap<Integer,Monitor>> timeStepHashMapFill = DataSet.timeStepHashMapFill(timeStepsHashMap,lisOftMonitors);	//	8.Filling Dataset
	//	Test.debugarrayToSlide(timeStepHashMapFill);																					//	9.Test 8
	//	System.out.println(timeStepHashMapFill.get(0).get(0).getHOST()+" "+timeStepHashMapFill.get(0).get(0).getTimeStep());
	//	System.out.println(timeStepHashMapFill.get(0).get(1).getHOST()+" "+timeStepHashMapFill.get(0).get(1).getTimeStep());
	//	System.out.println(timeStepHashMapFill.get(0).get(21).getHOST()+" "+timeStepHashMapFill.get(0).get(21).getTimeStep());
		
		ArrayList<LinkedHashMap<Integer,Monitor>> finalTimeStep = DataSet.transposeTimeStepsNew(timeStepHashMapFill);	//10.Tranposing AL<HM> to AL<HM>
	//	Test.debugarrayToSlide(finalTimeStep);																			//11.Test 10
	//	System.out.println(finalTimeStep.get(0));
	//	ArrayList<HashMap<Integer,Monitor>> notifyChanges = DataSet.notifyChanges(finalTimeStep, 1);
	//	Test.debugnotifyChanges(notifyChanges);
		ArrayList<int[]> arrayListOfInteger = new ArrayList<int[]>();
		for (int i=0; i<100;i++){
			System.out.println(i);
			ArrayList<HashMap<Integer,Monitor>> notifyChanges = DataSet.notifyChanges(finalTimeStep, i/100);			//12.Notify changes
			arrayListOfInteger.add(DataSet.destCompareTTLRTT(notifyChanges));
//			Test.debugnotifyChanges(notifyChanges);																		//13.Test 12
		}
		
		Graphic.createFrame(arrayListOfInteger);
		
	}

}


//check of branches
//test branches2
//and one more test