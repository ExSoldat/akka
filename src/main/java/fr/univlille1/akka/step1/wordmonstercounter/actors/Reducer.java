package fr.univlille1.akka.step1.wordmonstercounter.actors;

public interface Reducer {
	
	public void setMaster(Master m);
	public void addWord(String word);
	public String renderHashMap();

}