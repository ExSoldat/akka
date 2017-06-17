package fr.univlille1.akka.step1.wordmonstercounter.main2;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;
import akka.actor.Address;
import akka.actor.AddressFromURIString;
import akka.actor.Deploy;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import akka.remote.RemoteScope;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Mapper;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Master;
import fr.univlille1.akka.step1.wordmonstercounter.actors.Reducer;
import fr.univlille1.akka.step1.wordmonstercounter.actors.impl.MapperImpl;
import fr.univlille1.akka.step1.wordmonstercounter.actors.impl.MasterImpl;
import fr.univlille1.akka.step1.wordmonstercounter.actors.impl.ReducerImpl;
import fr.univlille1.akka.step1.wordmonstercounter.utils.Constants;
import fr.univlille1.akka.step1.wordmonstercounter.utils.Logger;

public class Mappers {

	public static void main(String[] args) {
		Logger l = new Logger("Mappers");
		ActorSystem system = ActorSystem.create(Constants.STEP2_SYSTEM_MAPPERS, ConfigFactory.load(Constants.STEP2_SYSTEM_MAPPERS_CONFIG));
		Address address = AddressFromURIString.parse(Constants.STEP2_MAPPERS_ROOT);
	    
	    for(int i = 0; i<Constants.MAPPERS_NUMBER; i++) {
	    	Mapper map = TypedActor.get(system).typedActorOf(new TypedProps<MapperImpl>(Mapper.class, MapperImpl.class).withDeploy(
	    			new Deploy(new RemoteScope(address))), URLEncoder.encode(Constants.MAPPER_ADDRESSES[i]));
	    }   
	}

}
