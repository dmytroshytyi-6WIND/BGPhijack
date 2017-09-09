//authors: Dmytro Shytyi
//email: dmytro@shytyi.net
//website: http://shytyi.net
//website: http://dmytro.shytyi.net
//license: BSD
//Please feel free to use and modify this, but keep the above information. Thanks!

import java.util.ArrayList;


public class Modify {

	public static ArrayList<String> arrDoubleToString(ArrayList<Double> List){ 
	 ArrayList<String> arr = new ArrayList<String>();
	 for (Double d : List) {
		 arr.add(d.toString());
	 }
	return arr;
	}

	public static ArrayList<String> arrIntToString(ArrayList<Integer> List){ 
	 ArrayList<String> arr = new ArrayList<String>();
	 for (int i : List){
		 arr.add(Integer.toString(i));
	 }
	return arr;
	}

	public static ArrayList<String> deleteZerosFromArray(ArrayList<String> arr){
		ArrayList<String> tmpArr = new ArrayList<String>();
		for (String elem : arr){
			if (	   !elem.equals("0")
					&& !elem.equals("0.0")
				){
				tmpArr.add(elem);
			}
		}
		return tmpArr;
	}

}
