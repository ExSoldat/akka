package fr.univlille1.akka.step1.wordmonstercounter.actors.impl;

import java.util.ArrayList;
import java.util.List;

import fr.univlille1.akka.step1.wordmonstercounter.actors.Mapper;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Reducer;
import fr.univlille1.akka.step1.wordmonstercounter.utils.Logger;

public class MapperImpl implements Mapper {
	Logger l = new Logger("MAPPER");
	List<Reducer> reducers;
	
	@Override
	public void setReducers(List<Reducer> r) {
		this.reducers = r;
	}
	
	
	@Override
	public void partition(String word) {
		//Each reducer got a range of characters into which the words it counts could be
		//mot.hashcode % 2 = index du reducer dans la liste des reducers
		reducers.get(Math.abs(word.hashCode()%reducers.size())).addWord(word);
	}

	@Override
	public boolean processLine(String line, int number) {
		String[] delimitedLine = line.split(" ");
		for (String word : delimitedLine) {
			//Removing special characters :
			String cleanedWord = word.replaceAll("[^A-Za-z0-9\\s]", "");
			partition(cleanedWord);
		}
		if(number%2 ==0)
			l.i("Line processed ! \\o\\");
		else
			l.i("Line processed ! /o/");
		return true;
	}

}
