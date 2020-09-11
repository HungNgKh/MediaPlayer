package GraphicComponent;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import AudioPlayer.AudioPlayer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import musicplayer.NowPlayingPanel;

public class test extends JFrame {

	private JPanel contentPane;
	private playerUI ui;
	private AudioPlayer player = new AudioPlayer();
	private NowPlayingPanel playingPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws JavaLayerException 
	 * @throws FileNotFoundException 
	 */
	public test() throws FileNotFoundException, JavaLayerException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		ui = new playerUI(100,100, 600, 160);
		ui.addLogic(player,playingPanel);
		contentPane.add(ui);
		File myDocuments = FileSystemView.getFileSystemView().getDefaultDirectory();
		File file = new File(myDocuments, "My Music/Charlie Brown.mp3");
		 AdvancedPlayer player1= new AdvancedPlayer(new FileInputStream(file));
		//File file = new File(System.getProperty("user.home") + "/Music/Viva La Vida.mp3");
	      Thread t = new Thread()
	    		  {
	    	  			public void run()
	    	  			{
	    	  				try
	    	  				{
	    	  					
	    	  					player1.play(0,5000);
	    	  					
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
	}

}
