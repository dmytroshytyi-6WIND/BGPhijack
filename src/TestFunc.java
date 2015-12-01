import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;


public class TestFunc {

	//ArrayList<HashMap<String,Monitor>>
	
	@Test
	public void testTtlRttDstPerMonitorsQueues() {
	/*
		Map<String, Monitor> monitor1 = ImmutableMap.of(
				"key1", new Monitor (15,300), 
				"key2", new Monitor (12,2), 
				"key3", new Monitor (1,3), 
				"key4", new Monitor (6,4), 
				"key5", new Monitor (5,5));//, "key6", new Monitor (3,5), "key7", new Monitor (7,6), "key8", new Monitor (4,4));
		Map<String, Monitor> monitor2Timesteps = ImmutableMap.of(
				"key1", new Monitor (15,300), 
				"key2", new Monitor (12,2), 
				"key3", new Monitor (1,3), 
				"key4", new Monitor (6,4), 
				"key5", new Monitor (5,5));//, "key6", new Monitor (3,5), "key7", new Monitor (7,6), "key8", new Monitor (4,4));
		*/
		
		LinkedHashMap monitor1Timesteps = new LinkedHashMap();
		monitor1Timesteps.put(1, new Monitor (15,300));
		monitor1Timesteps.put(2, new Monitor (12,2));
		monitor1Timesteps.put(3, new Monitor (1,3));
		monitor1Timesteps.put(4, new Monitor (6,4));
		monitor1Timesteps.put(5, new Monitor (5,5));
		
		LinkedHashMap monitor2Timesteps = new LinkedHashMap();
		monitor1Timesteps.put(1, new Monitor (15,300));
		monitor1Timesteps.put(2, new Monitor (12,2));
		monitor1Timesteps.put(3, new Monitor (1,3));
		monitor1Timesteps.put(4, new Monitor (6,4));
		monitor1Timesteps.put(5, new Monitor (5,5));
		
		/*	
		HashMap<String,Monitor> monitor1Timesteps = new HashMap<String,Monitor> (Arrays.asList(
		//ArrayList<Monitor> monitor1Timesteps = new ArrayList<Monitor> (Arrays.asList(
				new Monitor(15,300), 
				new Monitor (12,2),
				new Monitor (1,3),
				new Monitor (6,4),
				new Monitor (5,5),
				new Monitor (3,5),
				new Monitor (7,6),
				new Monitor (4,4)
				));
		
		ArrayList<Monitor> monitor2Timesteps = new ArrayList<Monitor> (Arrays.asList(
				new Monitor(1,1.0), 
				new Monitor (2,2.0),
				new Monitor (3,3.0),
				new Monitor (4,4.0),
				new Monitor (5,5.0),
				new Monitor (6,6.0),
				new Monitor (7,7.0),
				new Monitor (4,4.0)
				));
		
		ArrayList<Monitor> monitor3Timesteps = new ArrayList<Monitor> (Arrays.asList(
				new Monitor(1,1), 
				new Monitor (2,2),
				new Monitor (3,3),
				new Monitor (4,4),
				new Monitor (5,5),
				new Monitor (6,6),
				new Monitor (7,7),
				new Monitor (8,8)
				));
		*/
		
<<<<<<< HEAD
		ArrayList<Map<String, Monitor>> queues = new ArrayList<Map<String, Monitor>>(Arrays.asList(
				monitor1Timesteps,
				monitor2Timesteps//,
				//monitor3Timesteps));
				));
		//Map<Integer, Monitor> table = DataSet.ttlRttDstPerMonitorsQueues(queues);
		//System.out.println(table.get(0).meanTTL);
		//assertEquals(table.get(0).meanTTL,7.8,0.0);
		//assertEquals(table.get(0).meanRTT,62.8,0.0);
=======
		ArrayList<LinkedHashMap<Integer, Monitor>> queues = 
				new ArrayList<LinkedHashMap<Integer, Monitor>>();
		queues.add(monitor1Timesteps);
		queues.add(monitor1Timesteps);
		
		Map<Integer, Monitor> table = DataSet.ttlRttDstPerMonitorsQueues(queues);
		//System.out.println(table.get(0).meanTTL);
		assertEquals(table.get(0).meanTTL,7.8,0.0);
		assertEquals(table.get(0).meanRTT,62.8,0.0);
>>>>>>> refs/remotes/origin/3-calculateMean-SD-ForTimePeriod(slidingArray)
		
		//assertEquals(table.get(1).meanTTL,4.0,0.0);
		//assertEquals(table.get(1).meanRTT,4.0,0.0);
		
		//assertEquals(table.get(2).meanTTL,4.5,0.0);
		//assertEquals(table.get(2).meanRTT,4.5,0.0);
		
	}

}
