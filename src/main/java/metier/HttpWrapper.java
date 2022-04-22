package metier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import org.codehaus.jackson.map.ObjectMapper;


public class HttpWrapper 
{
	public static <T> T getOneInstance(Class<T> classname, String stringUrl) throws Exception
	{
		T object = null;
		
		try 
		{
			URL url = new URL(stringUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200 && conn.getResponseCode() != 204) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader((conn.getInputStream())));
			
			String output;
			while ((output = br.readLine()) != null) 
			{
				ObjectMapper mapper = new ObjectMapper();
				object = mapper.readValue(output, classname); 
			}
			
		}catch (MalformedURLException e) 
		{
		       e.printStackTrace();
	    }
		catch (IOException e)
		{ 
			e.printStackTrace();
		}
		
		return object;
	}
	
	public static <T> T[] getMultipleInstances(Class<T[]> classname, String stringUrl) throws Exception
	{
		T[] results = null;
		
		try 
		{
			URL url = new URL(stringUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200 && conn.getResponseCode() != 204) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader((conn.getInputStream())));
			
			String output;
			while ((output = br.readLine()) != null) 
			{
				ObjectMapper mapper = new ObjectMapper();
				results = mapper.readValue(output, classname); 
			}
			
		}catch (MalformedURLException e) 
		{
		       e.printStackTrace();
	    }
		catch (IOException e)
		{ 
			e.printStackTrace();
		}
		
		return results;
	}
	
	public static <T, F> T postOneInstance(Class<T> classname, String stringUrl, F inputObject) throws Exception
	{
		T object = null;
		
		try 
		{
			URL url = new URL(stringUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8"); //request format type
			conn.setRequestProperty("Accept", "application/json"); //response format type
			conn.setDoOutput(true);
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonInput = mapper.writeValueAsString(inputObject);
			
			try(OutputStream os = conn.getOutputStream()) 
			{ 
				byte[] input = jsonInput.getBytes("utf-8");
			    os.write(input, 0, input.length);
			}
			
			if (conn.getResponseCode() != 200 && conn.getResponseCode() != 204) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader((conn.getInputStream())));
			
			String output;
			while ((output = br.readLine()) != null) 
			{
				object = mapper.readValue(output, classname); 
			}
			
		}catch (MalformedURLException e) 
		{
		       e.printStackTrace();
	    }
		catch (IOException e)
		{ 
			e.printStackTrace();
		}
		
		return object;
	}
	
	public static <T, F> T deleteOneInstance(Class<T> classname, String stringUrl) throws Exception
	{
		T object = null;
		
		try 
		{
			URL url = new URL(stringUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200 && conn.getResponseCode() != 204) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader((conn.getInputStream())));
			
			String output;
			while ((output = br.readLine()) != null) 
			{
				ObjectMapper mapper = new ObjectMapper();
				object = mapper.readValue(output, classname); 
			}
			
		}catch (MalformedURLException e) 
		{
		       e.printStackTrace();
	    }
		catch (IOException e)
		{ 
			e.printStackTrace();
		}
		
		return object;
	}
}
