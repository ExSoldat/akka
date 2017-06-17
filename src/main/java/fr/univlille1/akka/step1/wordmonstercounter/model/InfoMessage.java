package fr.univlille1.akka.step1.wordmonstercounter.model;

public class InfoMessage extends Message {

	private String content;
	public InfoMessage(Type t, String c) {
		super(t);
		this.content = c;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
