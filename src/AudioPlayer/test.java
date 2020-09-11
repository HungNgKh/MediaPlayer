package AudioPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Clock;
import java.util.Map;
import java.util.ResourceBundle.Control;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line.Info;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.tritonus.share.sampled.TAudioFormat;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import javax.sound.sampled.Port;
import javax.sound.sampled.SourceDataLine;

import com.sun.media.jfxmedia.MediaPlayer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.AudioDeviceBase;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class test {
	  private int frames;
	  public static FloatControl set_volume()  //from 0.0 to 1.0
	  {        
	      Info source = Port.Info.SPEAKER;
	      //       source = Port.Info.LINE_OUT;
	      //        source = Port.Info.HEADPHONE;

	          if (AudioSystem.isLineSupported(source)) 
	          {
	              try 
	              {
	                  Port outline = (Port) AudioSystem.getLine(source);
	                  outline.open();                
	                  FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);                
	                 // System.out.println("       volume: " + volumeControl.getValue() );
	                 // volumeControl.setValue(value);
	                 System.out.println("   new volume: " + volumeControl.getValue() );
	                
	                //  System.out.println("newest volume: " + volumeControl.getValue() );
	                   return volumeControl;
	              } 
	              catch (LineUnavailableException ex) 
	              {
	                  System.err.println("source not supported");
	                  ex.printStackTrace();
	              }            
	          }      
	          return null;
	      }  	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			test tst = new test();
			tst.frames = 0;
		//	File file = new File(System.getProperty("user.home")+"/Documents/Coldplay - Birds.mp3");
			File myDocuments = FileSystemView.getFileSystemView().getDefaultDirectory();
			File file = new File(myDocuments, "My Music/Coldplay - A Hopeful Transmission.mp3");
			//File file = new File(System.getProperty("user.home") + "/Music/Viva La Vida.mp3");
			// AudioInputStream in= AudioSystem.getAudioInputStream(file);
			 AdvancedPlayer player= new AdvancedPlayer(new FileInputStream(file));
			 //Player pl = new Player(in);
			   player.setPlayBackListener(new PlaybackListener() {
				   @Override
                   public void playbackFinished(PlaybackEvent arg0) {
                       arg0.setSource(player);
                       tst.frames = arg0.getFrame();
                       super.playbackFinished(arg0);
                   }   
               
               @Override
               public void playbackStarted(PlaybackEvent arg0) {
                   arg0.setSource(player);
                   arg0.setFrame(tst.frames);
                   super.playbackStarted(arg0);
               }
			});
		      Thread t = new Thread()
		    		  {
		    	  			public void run()
		    	  			{
		    	  				try
		    	  				{
		    	  					player.play(0,1000);
		    	  					
		    	  					//long start = System.currentTimeMillis();
		    	  							    	  					//long end = System.currentTimeMillis();
		    	  					//System.out.println((end - start));
		    	  				}catch(Exception ex)
		    	  				{
		    	  					ex.printStackTrace();
		    	  				}		    	  				
		    	  			}
		    		  };
		   
		   t.start();
		  FloatControl f = set_volume();
		   int sc = 0;
		   float vol =1;
		/*  
		   while(t.isAlive())
		   {
			   Thread.sleep(1000);
			  f.setValue(vol);
			  vol -=0.1f;
		   }
		  // */
		  // player.stop();
		   
		   System.out.println(sc);
		   Map p = ((TAudioFileFormat)AudioSystem.getAudioFileFormat(file)).properties();
		   System.out.println(p.get("mp3.length.frames"));
		   System.out.println(p.get("mp3.framerate.fps"));
		   System.out.println(p.get("mp3.length.bytes"));
		   System.out.println(p.get("mp3.framesize.bytes"));
		   System.out.println(p.get("mp3.header.pos"));
		   System.out.println((long)p.get("duration"));
		   Map p2 = ((TAudioFormat)(AudioSystem.getAudioFileFormat(file).getFormat())).properties();
		   System.out.println(p2.get("bitrate"));
		   // System.out.println(p.get("mp3.id3tag.v2"));
		  // InputStreamReader rd = new InputStreamReader((InputStream)p.get("mp3.id3tag.v2"));
		   
			// t.start();
		    
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
