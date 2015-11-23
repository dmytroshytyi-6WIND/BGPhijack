import java.util.ArrayList;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;


public class Calc {

	public static double getStandartDeviation(ArrayList<String> arr){
	//public static double applyStandartDeviation(ArrayList<?> arr){
		
		double standardDeviation = 0;
		double[] SimpleArray = new double[arr.size()]; 
		int cnt = 0;
		for (String elem:arr){
			 SimpleArray[cnt] = Double.parseDouble(elem);
			 cnt++;
		}
		//double standardDeviation = 0;
		//double[] SimpleArray = ArrayUtils.toPrimitive(arr.toArray(new Double[arr.size()]));
	
		StandardDeviation sd = new StandardDeviation();
	    standardDeviation = sd.evaluate(SimpleArray);
	    return standardDeviation;
	}

	public static ArrayList<Integer> notifyRouteChange(ArrayList<String> destList){
			ArrayList<Integer> routeChange = new ArrayList<Integer>();
			
			int notifyChange = 0;
			routeChange.add(notifyChange);
			for (int i=0; i < (destList.size() - 1);i++){
				if (!destList.get(i).equals(destList.get(i+1)))
				{if (!(destList.get(i+1).equals(""))){
					if (!(destList.get(i).equals(""))){
					notifyChange = 1;
					routeChange.add(notifyChange);
					notifyChange = 0;
				}
				}
				}
				else routeChange.add(notifyChange);
			}
		return routeChange;
	}

}
