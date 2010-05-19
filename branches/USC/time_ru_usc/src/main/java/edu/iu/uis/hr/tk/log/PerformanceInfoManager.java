package edu.iu.uis.hr.tk.log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class PerformanceInfoManager {
	public static final int NUM_MAX_TIME_ENTRIES = 100;
	
	private static List<PerformanceInfo> performanceList = new ArrayList<PerformanceInfo>(NUM_MAX_TIME_ENTRIES);
	static{
		for(int i = 0;i<NUM_MAX_TIME_ENTRIES;i++){
			performanceList.add(new PerformanceInfo(0,new Date(System.currentTimeMillis()),"BlankEntry", "BlankEntry"));
		}
	}
	public static List<PerformanceInfo> getPerformanceList() {
		return performanceList;
	}

	public static void setPerformanceList(List<PerformanceInfo> performanceList) {
		PerformanceInfoManager.performanceList = performanceList;
	}
	
	public static void addToPerformanceList(PerformanceInfo inf){
		sortPerformanceList();
		if(performanceList.get(NUM_MAX_TIME_ENTRIES - 1).getTimeTaken() < inf.getTimeTaken()){
			performanceList.remove(NUM_MAX_TIME_ENTRIES - 1);
			performanceList.add(inf);
		}
		sortPerformanceList();
	}
	
	public static void sortPerformanceList(){
		Collections.sort(performanceList, new PerformanceComparator());
	}

	static class PerformanceComparator implements Comparator<PerformanceInfo>{
		public int compare(PerformanceInfo o1, PerformanceInfo o2) {
			if(o1.getTimeTaken() < o2.getTimeTaken()){
				return 1;
			}
			else if(o1.getTimeTaken() > o2.getTimeTaken()){
				return -1;
			}
			else{
				return 0;
			}
		}
		
	}
	
}
