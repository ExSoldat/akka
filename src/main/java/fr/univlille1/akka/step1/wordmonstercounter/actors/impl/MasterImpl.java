package fr.univlille1.akka.step1.wordmonstercounter.actors.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.univlille1.akka.step1.wordmonstercounter.actors.Mapper;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Master;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Reducer;
import fr.univlille1.akka.step1.wordmonstercounter.utils.Logger;

public class MasterImpl implements Master {
	
	Logger l = new Logger("MASTER");

	private ArrayList<Mapper> mappers = new ArrayList<Mapper>();
	private List<Reducer> reducers;
	
	@Override
	public boolean addMapper(Mapper m) {
		System.out.println("adding mapper");
		return this.mappers.add(m);
	}
	
	@Override
	public void setReducers(List<Reducer> r) {
		this.reducers = r;
	}

	@Override
	public void processFile(String f) {
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			l.e("Unable to find the file");
			e.printStackTrace();
			return;
		}
		
		int i = 0; //index
		String s;
		
		try {
			while((s=br.readLine()) != null) {
				
				mappers.get(i).processLine(s, i);
				i++;
				if(i == mappers.size())
					i = 0;
			}
			l.i("DONE ! \\o/");
		} catch (IOException e) {
			l.e("An IO exception occured");
			e.printStackTrace();
			return;
		}
		
	}

	@Override
	public String renderResult() {
		l.i("rendering...");
		String result = "";
		for (Reducer reducer : reducers) {
			result += reducer.renderHashMap() + "\n";
		}
		return result;
	}

}
