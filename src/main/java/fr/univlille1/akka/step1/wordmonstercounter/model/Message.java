package fr.univlille1.akka.step1.wordmonstercounter.model;

public abstract class Message {	
	private Type type;
	
	public static enum Type {
		ADD_ACTOR,
		SUCCESS,
		FAILURE
	}
	
	public Message() {}
	
	public Message(Type t) {
		this.type = t;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
