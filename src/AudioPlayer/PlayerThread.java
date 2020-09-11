package AudioPlayer;


import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import musicplayer.SongObject;

/*
 * Author : Nguyen Khanh Hung
 * Description : 
 * - This is the thread that play the song
 */
public class PlayerThread implements Runnable { 
	private int start_frame;
	private AdvancedPlayer player;
	private Thread playthread;
	private SongObject sourcedata;

	public PlayerThread()
	{
		super();
		start_frame = 0;
		player = null;
		playthread = null;
		sourcedata = null;
	}
	
	public void createthread(SongObject stream,int start_frame) {	
		this.start_frame = start_frame;
		sourcedata = stream;
		try {
			if(stream != null)
			{
				FileInputStream file = new FileInputStream(stream.getPath());
				player = new AdvancedPlayer(file, FactoryRegistry.systemRegistry().createAudioDevice());
			}
			else
			{
				player = null;
			}
		} catch (Exception ex) {
			Logger.getLogger(this.getClass().toString()).log(Level.SEVERE, ex.toString(),ex);
			JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		playthread = new Thread(this);
	}
	
	@Override
	public void run()
	{
		try
		{
			player.play(this.start_frame,Integer.MAX_VALUE);
			this.player.close();
			this.player = null;
		}
		catch(JavaLayerException ex)
		{
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.toString(),ex);
		}

	}
	
	@SuppressWarnings("deprecation")
	public void start()
	{
		if(!playthread.isAlive())
		{		
			this.playthread.start();
		}
		else
			this.playthread.resume();
	}
	
	@SuppressWarnings("deprecation")
	public void stop()
	{
		playthread.suspend();
	}
	
	public AdvancedPlayer getPlayer()
	{
		return this.player;
	}
	
	public SongObject getSourcedata() {
		return sourcedata;
	}
	
	public boolean is_living()
	{
		return (this.player != null);
	}
	
	@SuppressWarnings("deprecation")
	public void getkilled()
	{
		if(this.player != null)
		{
			player.close();
			playthread.stop();
			player = null;
		}
		playthread = null;
	}
}
