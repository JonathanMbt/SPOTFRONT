package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Playlists implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String description;
	
	
	Set<Musics> musics = new HashSet<Musics>();
	
	
	public Playlists(){}
	
	public Playlists(String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public Set<Musics> getMusics() 
	{
		return musics;
	}

	public void setMusics(Set<Musics> musics) 
	{
		this.musics = musics;
	}
	
	public void addMusics(List<Musics> musics)
	{
		if(this.musics == null)
		{
			this.musics = new HashSet<Musics>();	
		}
		
		this.musics.addAll(musics);
	}
	
	public void removeMusic(int musicId)
	{
		for(Musics m : this.musics)
		{
			if(m.getId() == musicId)
			{
				this.musics.remove(m);
			}
		}
	}
	
	
}
