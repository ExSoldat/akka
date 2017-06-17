package fr.univlille1.akka.step1.wordmonstercounter.actors;

import java.util.List;

public interface Master {
	public boolean addMapper(Mapper m);
	public void setReducers(List<Reducer> r);
	public void processFile(String f);
	public String renderResult();
}
