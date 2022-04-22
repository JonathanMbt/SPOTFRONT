package metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import entities.Musics;

public class FactoryMusics 
{
	private static final String CHAMP_TITLE = "titre";
	private static final String CHAMP_GENRE = "genre";
	private static final String CHAMP_ARTIST = "artist";
	
	private Map<String, String> errors = new HashMap<String, String>();
	public boolean success = false;
	
	
	public Musics constructMusics(HttpServletRequest request)
	{
		String title = request.getParameter(CHAMP_TITLE);
		String genre = request.getParameter(CHAMP_GENRE);
		String artistName = request.getParameter(CHAMP_ARTIST);
		Musics m = new Musics(title, genre, null);
		
		try 
		{
			artistName = java.net.URLEncoder.encode(artistName, "UTF-8");
			success = true;
			HttpWrapper.postOneInstance(Musics.class,"http://localhost/SPOTAPI/spotapi/musics/" + artistName , m);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return m;
		
	}
	
	public Map<String, String> getErreurs(){
		return errors;
	}
}
