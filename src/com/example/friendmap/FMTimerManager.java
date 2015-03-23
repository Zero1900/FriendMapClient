package com.example.friendmap;

import java.util.HashMap;
import java.util.Map;



import com.example.friendmap.utils.FMTimer;

public class FMTimerManager {
	public enum Tag {
		NetPositionGetAll
	};
	private Map<Tag, FMTimer> mapTimer;
	public FMTimerManager(){
		mapTimer=new HashMap<Tag, FMTimer>();
	}
	public void startTimer(Tag tag,FMTimer timer){
		if(mapTimer.containsKey(tag)){
			return;
		}
		mapTimer.put(tag, timer);
		timer.start();
	}
	public void stopTimer(Tag tag){
		FMTimer timer=mapTimer.get(tag);
		if(null!=timer){
			timer.stop();
			mapTimer.remove(tag);
		}
	}
}
