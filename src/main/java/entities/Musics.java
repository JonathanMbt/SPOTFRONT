package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Musics implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private String genre;
	
	private Artists artist;
	

	Set<Playlists> playlist;
	
	Set<Users> users = new HashSet<Users>();
	
	public Musics() {}
	
	public Musics(String title, String genre, Artists artist)
	{
		this.title = title;
		this.genre = genre;
		this.artist = artist;
	}
	
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getGenre() 
	{
		return genre;
	}

	public void setGenre(String genre) 
	{
		this.genre = genre;
	}

	public Artists getArtist() 
	{
		return artist;
	}

	public void setArtist(Artists artist) 
	{
		this.artist = artist;
	}

	public Set<Playlists> getPlaylist() 
	{
		return playlist;
	}

	public void setPlaylist(Set<Playlists> playlist) 
	{
		this.playlist = playlist;
	}
	
	public Set<Users> getUsersLikes()
	{
		return users;
	}
	
	public boolean isLikedBy(String username)
	{
		boolean result = false;
		for(Users u : this.users)
		{
			if(u.getUsername().equals(username))
			{
				result = true;
			}
		}
		return result;
	}
	
	public void setUsersLikes(Set<Users> user)
	{
		this.users=user;
	}
	
	public void addUsersLikes(List<Users> users)
	{
		if(this.users == null)
		{
			this.users = new HashSet<Users>();	
		}
		
		this.users.addAll(users);
	}
	
	public void removeUserLike(String username)
	{
		for(Users u : this.users)
		{
			if(u.getUsername().equals(username))
			{
				this.users.remove(u);
			}
		}
	}
	
	
}
