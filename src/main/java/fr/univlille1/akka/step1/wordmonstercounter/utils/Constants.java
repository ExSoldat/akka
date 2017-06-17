package fr.univlille1.akka.step1.wordmonstercounter.utils;

import java.net.URLEncoder;

public class Constants {

	public static final String STEP1_SYSTEM = "wordcounter";
	public static final String STEP2_SYSTEM_MAPPERS = "wordcountermappers";
	public static final String STEP2_SYSTEM_MASTER_REDUCERS = "wordcountermaster";
	public static final String STEP2_SYSTEM_MAPPERS_CONFIG = "mappers";
	public static final String STEP2_SYSTEM_MASTER_REDUCERS_CONFIG = "masterreducers";
	public static final String STEP2_MAPPERS_ROOT = "akka.tcp://" + STEP2_SYSTEM_MAPPERS + "@localhost:2552";
	
	public static int MAPPERS_NUMBER = 3;
	public static int REDUCERS_NUMBER = 2;
	
	
	public static String MAPPER1_ADDRESS = STEP2_MAPPERS_ROOT + "/user/mapper1";
	public static String MAPPER2_ADDRESS = STEP2_MAPPERS_ROOT + "/user/mapper2";
	public static String MAPPER3_ADDRESS = STEP2_MAPPERS_ROOT + "/user/mapper3";
	
	public static final String[] MAPPER_ADDRESSES = {MAPPER1_ADDRESS, MAPPER2_ADDRESS, MAPPER3_ADDRESS};

}
