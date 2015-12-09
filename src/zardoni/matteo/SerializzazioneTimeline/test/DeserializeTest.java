package zardoni.matteo.SerializzazioneTimeline.test;
/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import zardoni.matteo.SerializzazioneTimeline.lightTimelines.PlainTimeLine;
import zardoni.matteo.SerializzazioneTimeline.lightTimelines.TimeLine;
import zardoni.matteo.SerializzazioneTimeline.timedValue.TimedValueImpl;

public class DeserializeTest 
{

	public static void main(String[] args) 
	{
		
		String filename = "Timeline1.txt";
		PlainTimeLine newTimeLine = deserializeJson(filename, PlainTimeLine.class);
		System.out.println(newTimeLine.getAllTimeds());
		
		System.out.println(newTimeLine.getName());
	}
	
	@SuppressWarnings("rawtypes")
	public static <E> E deserializeJson(String filename, Class<E> class1)
	{
		try 
		{
			GsonBuilder gBuilder = new GsonBuilder();
			
			RuntimeTypeAdapterFactory timelineTypeAdapter = RuntimeTypeAdapterFactory.of(TimeLine.class, "timeline").registerSubtype(PlainTimeLine.class, "plainTimeline");
			gBuilder.registerTypeAdapterFactory(timelineTypeAdapter);
			RuntimeTypeAdapterFactory managerAdapterFactory = RuntimeTypeAdapterFactory
														.of(zardoni.matteo.SerializzazioneTimeline.lightTimelines.TimedValueManager.class, "timedValueManger")
														.registerSubtype(zardoni.matteo.SerializzazioneTimeline.lightTimelines.PlainManager.class, "plainManager")
														.registerSubtype(zardoni.matteo.SerializzazioneTimeline.lightTimelines.TreeMapBasedManager.class, "treeMapBasedManager");
			gBuilder.registerTypeAdapterFactory(managerAdapterFactory);
						
			gBuilder.registerTypeAdapter(TimedValueImpl.class, new TimedValueSerializer());
			
			Gson gson = gBuilder.create();
		
			return leggiInAndroid(gson,filename,class1);
		//	return gson.fromJson((String) Files.readAllLines(Paths.get(filename),Charset.forName("ASCII")).toArray()[0], class1);
		} 
		catch (JsonSyntaxException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static <E> E leggiInAndroid(Gson g, String filename, Class<E> class1) throws IOException
	{
		List<String> json;
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		json = new ArrayList<String>();
		for (;;) 
		{
		  String line = reader.readLine();
		  if (line == null)
		          break;
		   json.add(line);
		}
		reader.close();
		return g.fromJson((String)json.toArray()[0], class1);
	}
	
}*/
