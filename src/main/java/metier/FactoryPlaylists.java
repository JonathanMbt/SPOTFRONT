package metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import entities.Playlists;

public class FactoryPlaylists 
{
	private static final String CHAMP_NAME = "name";
	private static final String CHAMP_DESCRIPTION = "description";
	
	private Map<String, String> errors = new HashMap<String, String>();
	public boolean success = false;
	
	
	public Playlists constructPlaylist(HttpServletRequest request)
	{
		String name = request.getParameter(CHAMP_NAME);
		String description = request.getParameter(CHAMP_DESCRIPTION);
		Playlists p = new Playlists(name, description);
		
		try 
		{
			success = true;
			HttpWrapper.postOneInstance(Playlists.class,"http://localhost/SPOTAPI/spotapi/playlists/" , p);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return p;
		
	}
	
	public Map<String, String> getErreurs(){
		return errors;
	}
}
