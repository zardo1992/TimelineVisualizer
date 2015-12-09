package zardoni.matteo.AppAndroid.Altro;

import java.util.LinkedHashMap;
import java.util.Map;
import zardoni.matteo.AppAndroid.RappresentazioneTimeline.Fatto;

public class FattiLivelliColori 
{
	private Map<Fatto,Integer> fattiLivelli = new LinkedHashMap<Fatto, Integer>();
	private Map<Fatto,Integer> fattiColori = new LinkedHashMap<Fatto, Integer>();
	
	public FattiLivelliColori(Map<Fatto,Integer> livelli, Map<Fatto,Integer> colori)
	{
		fattiLivelli = livelli;
		fattiColori = colori;
	}
	
	public Map<Fatto,Integer> getFattiColori()
	{
		return fattiColori;
	}
	
	public Map<Fatto,Integer> getFattiLivelli()
	{
		return fattiLivelli;
	}
	
}
