package fr.univlille1.akka.step1.wordmonstercounter.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Master;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Reducer;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Mapper;
import fr.univlille1.akka.step1.wordmonstercounter.actors.impl.MapperImpl;
import fr.univlille1.akka.step1.wordmonstercounter.actors.impl.MasterImpl;
import fr.univlille1.akka.step1.wordmonstercounter.actors.impl.ReducerImpl;
import fr.univlille1.akka.step1.wordmonstercounter.utils.Logger;

public class Main {
	//Master : distribue les lignes aux mappers
	public static int NUMBER_MAPPERS = 3;
	public static int NUMBER_REDUCERS = 2;
	
	//public List<Mapper> mappers = new ArrayList<Mapper>();
	
	public static void main(String[] args) {
		Logger l = new Logger("Main");
		ActorSystem system = ActorSystem.create("WordMonsterCounter");
	    Master master = TypedActor.get(system).typedActorOf(new TypedProps<MasterImpl>(Master.class, MasterImpl.class));
	    List<Reducer> reducers = new ArrayList<Reducer>();
	    
	    for(int i = 0; i<NUMBER_REDUCERS; i++) {
	    	Reducer r = TypedActor.get(system).typedActorOf(new TypedProps<ReducerImpl>(Reducer.class, ReducerImpl.class));
	    	reducers.add(r);
	    }
	    master.setReducers(reducers);
	    
	    for(int i = 0; i<NUMBER_MAPPERS; i++) {
	    	Mapper map = TypedActor.get(system).typedActorOf(new TypedProps<MapperImpl>(Mapper.class, MapperImpl.class));
	    	map.setReducers(reducers);
	    	master.addMapper(map);
	    }   
	    l.i("Starting to process file...");
	    master.processFile("zola-assommoir.txt");
	    l.i(master.renderResult());
	    
	}
}
