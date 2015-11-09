import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Parser {
	public static ArrayList<Monitor> parse(String path) throws Exception {
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

}
