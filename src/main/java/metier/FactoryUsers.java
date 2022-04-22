package metier;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;

import entities.Users;


public class FactoryUsers 
{
	private static final String CHAMP_USERNAME = "username";
	private static final String CHAMP_PASSWORD = "password";
	private static final String CHAMP_MAIL = "email";
	
	private Map<String, String> errors = new HashMap<String, String>();
	public boolean success = false;
	
	
	public Users constructUser(HttpServletRequest request)
	{
		String username = request.getParameter(CHAMP_USERNAME);
		String password = request.getParameter(CHAMP_PASSWORD);
		String mail = request.getParameter(CHAMP_MAIL);
		Users newUser = new Users(username, mail, password);
		
		try 
		{
			Users u = HttpWrapper.getOneInstance(Users.class, "http://localhost/SPOTAPI/spotapi/users/findByName/" + username);	
			if(u == null)
			{
				success = true;
				HttpWrapper.postOneInstance(Users.class,"http://localhost/SPOTAPI/spotapi/users/" , newUser);
			}else 
			{
				errors.put(CHAMP_USERNAME, "Nom d'utilisateur indisponible.");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return newUser;
		
	}
	
	public Users loginUser(HttpServletRequest request)
	{
		String username = request.getParameter(CHAMP_USERNAME);
		String password = request.getParameter(CHAMP_PASSWORD);
		Users user = new Users(username, "", password);
		Users rs = null;
		
		try 
		{
			username = java.net.URLEncoder.encode(username, "UTF-8");
			Users u = HttpWrapper.getOneInstance(Users.class, "http://localhost/SPOTAPI/spotapi/users/findByName/" + username);	
			if(u != null)
			{
				if(user.getPassword().equals(u.getPassword()))
				{
					success = true;
					rs = u;
				}else 
				{
					errors.put(CHAMP_PASSWORD, "Mot de passe invalide");
				}
			}else 
			{
				errors.put(CHAMP_USERNAME, "Nom d'utilisateur invalide.");
			}
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return rs;
		
	}

	
	public Map<String, String> getErreurs(){
		return errors;
	}
}
