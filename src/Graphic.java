import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Graphic {
	
	
	// 1st parametr: lisMonitorNoRepeat consists of motinors with TTL and RTT. 
	// 2nd paramert: TimeSteps could be timeinterval
	//the first file needs conent of all monitors
	public static void createFrames(ArrayList<ArrayList<Monitor>> timeSteps){
		ArrayList<JFrame> Frames = new ArrayList<JFrame>();
		ArrayList<Monitor> listMonitorsForTimeStep0 = timeSteps.get(0);
		
		for (Monitor monitor : listMonitorsForTimeStep0){
			JFrame frame = new JFrame(monitor.getHOST());
			Frames.add(frame);
		}	
		
	    int i=0;
		for (JFrame frame: Frames){
			String host = listMonitorsForTimeStep0.get(i).getHOST();
			frame.getContentPane().add(new ChartPanel(chartFunc(host,timeSteps)));
			frame.pack();
			frame.setVisible(true);
			i++;
		}	
	}
	
	public static JFreeChart chartFunc(String monitor,ArrayList<ArrayList<Monitor>> timeSteps){	
		JFreeChart chart = ChartFactory.createScatterPlot(
				monitor, // chart title
	            "TIME", // x axis label
	            "TTL|RTT|LOCATION", // y axis label
	            createDataset(monitor,timeSteps), // data  ***-----PROBLEM------***
	            PlotOrientation.VERTICAL,
	            true, // include legend
	            true, // tooltips
	            false // urls
	            );
		
		//StandardXYItemRenderer renderer = new StandardXYItemRenderer();   
		//renderer.setSeriesPaint(0, Color.blue);
		//renderer.setSeriesPaint(1, Color.blue);
		
		//XYPlot plot = (XYPlot) chart.getPlot(); 
		//renderer = plot.getRenderer();
		//renderer.setSeriesShape(0, cross);
		
		//plot.setDataset(0,createDataset(monitor, monitors,timeSteps));
		//plot.setRenderer(0, renderer);
			
		   
		return chart;
	}

		public static XYDataset createDataset(String plotHost,ArrayList<ArrayList<Monitor>> timeSteps) {
			
		    XYSeriesCollection result = new XYSeriesCollection();
		    XYSeries ttl = new XYSeries("TTL");
		    XYSeries rtt = new XYSeries("RTT"); 
		    
		    int timeStep = 1;
		    boolean foundMonitor;
		    for (ArrayList<Monitor> listMonitors :timeSteps){
		    	foundMonitor = false;
		    	for (Monitor monitor:listMonitors){
		    		if (monitor.getHOST().equals(plotHost)){
		    			ttl.add(timeStep,monitor.getTTL());
		    			rtt.add(timeStep,monitor.getRTT());
		    			foundMonitor = true;
		    			timeStep++;
		    		}
		    	}
		    	if(foundMonitor==false)
		    		timeStep++;
		    }
		    
		    result.addSeries(ttl);
		    result.addSeries(rtt);
		    
		    return result;
		}
}
