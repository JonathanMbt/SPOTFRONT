package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Musics;
import entities.Playlists;
import metier.HttpWrapper;

/**
 * Servlet implementation class Playlist
 */
@WebServlet("/session/playlist")
public class Playlist extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Playlist() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher requestDispatcher;
		int id = Integer.parseInt(request.getParameter("p"));
		
		List<Musics> lma = new ArrayList<Musics>();
		
		try 
		{
			
			Playlists[] lp = HttpWrapper.getMultipleInstances(Playlists[].class, "http://localhost/SPOTAPI/spotapi/playlists/all");
			Playlists p = HttpWrapper.getOneInstance(Playlists.class,  "http://localhost/SPOTAPI/spotapi/playlists/findById/"+id);
			Musics[] lm = HttpWrapper.getMultipleInstances(Musics[].class, "http://localhost/SPOTAPI/spotapi/playlists/getMusics/"+id);
			Musics[] lm2 = HttpWrapper.getMultipleInstances(Musics[].class, "http://localhost/SPOTAPI/spotapi/musics/all");
			
			
			for(Musics m2 : lm2)
			{
				boolean flag = false;
				for(Musics m : lm)
				{
					if(m.getId() == m2.getId())
					{
						flag = true;
					}
				}
				if(flag == false)
				{
					lma.add(m2);
				}
			}
			
			request.setAttribute("playlists", lp);
			request.setAttribute("playlist", p);
			request.setAttribute("musics", lm);
			request.setAttribute("musicsAvailable", lma);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/playlist.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int musicId = Integer.parseInt(request.getParameter("music"));
		int playlistId = Integer.parseInt(request.getParameter("p"));
		
		if(request.getParameter("updatePlaylist") != null) 
		{
			try 
			{
				Musics m = HttpWrapper.getOneInstance(Musics.class, "http://localhost/SPOTAPI/spotapi/musics/findById/"+musicId);
				List<Musics> lm = new ArrayList<Musics>();
				lm.add(m);
				
				Playlists playlist = HttpWrapper.getOneInstance(Playlists.class,  "http://localhost/SPOTAPI/spotapi/playlists/findById/"+playlistId);
				playlist.addMusics(lm);
				HttpWrapper.postOneInstance(Playlists.class, "http://localhost/SPOTAPI/spotapi/playlists/update", playlist);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}else if(request.getParameter("deletePlaylist") != null)
		{
			try 
			{
				HttpWrapper.deleteOneInstance(boolean.class, "http://localhost/SPOTAPI/spotapi/playlists/delete/" + playlistId);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}else
		{
			try 
			{
				Playlists playlist = HttpWrapper.getOneInstance(Playlists.class,  "http://localhost/SPOTAPI/spotapi/playlists/findById/"+playlistId);
				playlist.removeMusic(musicId);
				HttpWrapper.postOneInstance(Playlists.class, "http://localhost/SPOTAPI/spotapi/playlists/update", playlist);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("./playlist?p="+playlistId);
	}

}
