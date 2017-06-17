package fr.univlille1.akka.step1.wordmonstercounter.actors.impl;

import java.util.HashMap;
import java.util.Map;

import fr.univlille1.akka.step1.wordmonstercounter.actors.Master;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Reducer;
import fr.univlille1.akka.step1.wordmonstercounter.utils.Logger;

public class ReducerImpl implements Reducer {
	
	Logger l = new Logger("REDUCER");
	Master master;
	Map<String, Integer> counts = new HashMap<String, Integer>();

	@Override
	public void setMaster(Master m) {
		this.master = m;
	}

	@Override
	public void addWord(String word) {
		if(counts.containsKey(word))
			counts.put(word, counts.get(word) +1);
		else
			counts.put(word, 1);
	}

	@Override
	public String renderHashMap() {
		String result = "";
		for(Map.Entry<String, Integer> entry : counts.entrySet()) {
			result += (entry.getKey() + " : " + entry.getValue() + " time(s)"  + "\n");
		}
		return result;
	}

}
