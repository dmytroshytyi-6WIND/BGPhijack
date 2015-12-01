import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;


public class Parser {
	public static ArrayList<Monitor> parse() throws Exception {
<<<<<<< HEAD
		String path ="Z:\\Eclipse\\INF_570\\LabPolyGroupFlapping\\BGPHijack";
=======
		String path = "D:\\HUYMX\\M1\\Peer to Peer Networking\\LabPolyGroupFlapping\\New folder";
>>>>>>> origin/dev
		ArrayList<Monitor> ListMonitors = new ArrayList<Monitor>();
		File folder = new File(path);
		int timeStep=0;
		File[] files = folder.listFiles();
		Arrays.sort(files);
		for (File file : files){
			timeStep++;
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);	
		String lines = null;			
			while ((lines = bufferedReader.readLine()) != null) {
				String line = lines.toString();
				String[] words = line.split(" ", 0);
				if (words.length == 4){
				ListMonitors.add(new Monitor(words[0],Integer.parseInt(words[1]),Double.parseDouble(words[2]),words[3],timeStep));
				}
			}
			
		fileReader.close();
		
		bufferedReader.close();
		}
		return ListMonitors;
	}
	public static LinkedHashMap<String,Monitor> standardMonitorList() throws IOException{
		String path = "D:\\HUYMX\\M1\\Peer to Peer Networking\\LabPolyGroupFlapping\\resultsFlapping";
		LinkedHashMap<String,Monitor> standardMonitorList = new LinkedHashMap<String,Monitor>();
		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);	
		String lines = null;			
		while ((lines = bufferedReader.readLine()) != null) {
			String line = lines.toString();
			String[] words = line.split("\t", 0);
			if (words.length == 4){
				standardMonitorList.put(words[0], new Monitor(words[0],0,0.0,"NA",0));
			}
		}
		fileReader.close();
		bufferedReader.close();
		return standardMonitorList;
	}
}
