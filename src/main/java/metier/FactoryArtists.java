package metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import entities.Artists;

public class FactoryArtists 
{
	private static final String CHAMP_NAME = "name";
	private static final String CHAMP_LABEL = "label";
	private static final String CHAMP_DESCRIPTION = "description";
	
	private Map<String, String> errors = new HashMap<String, String>();
	public boolean success = false;
	
	
	public Artists constructArtist(HttpServletRequest request)
	{
		String name = request.getParameter(CHAMP_NAME);
		String label = request.getParameter(CHAMP_LABEL);
		String description = request.getParameter(CHAMP_DESCRIPTION);
		Artists a = new Artists(name, label, description);
		
		try 
		{
			name = java.net.URLEncoder.encode(name, "UTF-8");
			Artists artist = HttpWrapper.getOneInstance(Artists.class, "http://localhost/SPOTAPI/spotapi/artists/findByExactName/" + name);	
			if(artist == null)
			{
				success = true;
				HttpWrapper.postOneInstance(Artists.class,"http://localhost/SPOTAPI/spotapi/artists/" , a);
			}else 
			{
				errors.put(CHAMP_NAME, "Artiste déjà existant.");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return a;
		
	}
	
	public Map<String, String> getErreurs(){
		return errors;
	}

}
