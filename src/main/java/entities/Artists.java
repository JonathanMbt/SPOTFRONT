package entities;

import java.io.Serializable;
import java.util.Set;


public class Artists implements Serializable
{

	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String label;
	private String description;
	
	Set<Musics> music;
	
	public Artists() {}
	
	public Artists(String name, String label, String description)
	{
		this.name = name;
		this.label = label;
		this.description = description;
	}
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getLabel() 
	{
		return label;
	}

	public void setLabel(String label) 
	{
		this.label = label;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public Set<Musics> getMusic() 
	{
		return music;
	}

	public void setMusic(Set<Musics> music) 
	{
		this.music = music;
	}


}
