package fr.univlille1.akka.step1.wordmonstercounter.actors;

import java.util.ArrayList;
import java.util.List;

public interface Mapper {
	public void setReducers(List<Reducer> reducers);
	
	//String -> Message to a reducer
	public void partition(String word); //Choose where we should send the word
	public boolean processLine(String line, int number); //partition each line and sen deach word to the correc reducer
}
