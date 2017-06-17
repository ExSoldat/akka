package fr.univlille1.akka.step1.wordmonstercounter.model;

import akka.actor.ActorRef;

public class ActorMessage extends Message {
	private ActorRef actorRef;
	
	public ActorMessage(){}
	
	public ActorMessage(ActorRef a) {
		super(Type.ADD_ACTOR);
		this.actorRef = a;
	}

	public ActorRef getActorRef() {
		return actorRef;
	}

	public void setActorRef(ActorRef actorRef) {
		this.actorRef = actorRef;
	}
}
