package zardoni.matteo.AppAndroid.ListaScrollableTimeline;

/* Questa  classe rappresenta la riga della ListView
 * I campi corrispondono alla sottoriga:
 * titolo e descrizione.
 */
public class TwoStringFields
{
	private String title; //nome della timeline
	private String description; //eventuale breve descrizione/dettaglio
	
	public TwoStringFields(String title, String description)
	{
		this.title = title;
		this.description = description;
	}

	public String getTitle() 
	{
		return title;
	}

	public String getDescription() 
	{
		return description;
	}

}
