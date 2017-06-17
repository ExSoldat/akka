package fr.univlille1.akka.step1.wordmonstercounter.main2;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Mapper;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Master;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Reducer;
import fr.univlille1.akka.step1.wordmonstercounter.actors.impl.MapperImpl;
import fr.univlille1.akka.step1.wordmonstercounter.actors.impl.MasterImpl;
import fr.univlille1.akka.step1.wordmonstercounter.actors.impl.ReducerImpl;
import fr.univlille1.akka.step1.wordmonstercounter.utils.Constants;
import fr.univlille1.akka.step1.wordmonstercounter.utils.Logger;

public class MasterReducers {
	public static void main(String[] args) {
		Logger l = new Logger("MasterAndReducers");
		ActorSystem system = ActorSystem.create(Constants.STEP2_SYSTEM_MASTER_REDUCERS, ConfigFactory.load(Constants.STEP2_SYSTEM_MASTER_REDUCERS_CONFIG));
		
		Master master = TypedActor.get(system).typedActorOf(new TypedProps<MasterImpl>(Master.class, MasterImpl.class));
	    List<Reducer> reducers = new ArrayList<Reducer>();
	    
	    for(int i = 0; i<Constants.REDUCERS_NUMBER; i++) {
	    	Reducer r = TypedActor.get(system).typedActorOf(new TypedProps<ReducerImpl>(Reducer.class, ReducerImpl.class));
	    	reducers.add(r);
	    }
	    master.setReducers(reducers);
	    
	    for(int i = 0; i<Constants.MAPPERS_NUMBER; i++) {
	    	Mapper map = TypedActor.get(system).typedActorOf(new TypedProps<MapperImpl>(Mapper.class, MapperImpl.class), URLEncoder.encode(Constants.MAPPER_ADDRESSES[i]));
	    	map.setReducers(reducers);
	    	master.addMapper(map);
	    }   
	    l.i("Starting to process file...");
	    master.processFile("zola-assommoir.txt");
	    l.i(master.renderResult());
	}
}
