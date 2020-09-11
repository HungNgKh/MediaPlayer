package GraphicComponent;


import javax.swing.JPanel;

import AudioPlayer.AudioPlayer;
import musicplayer.NowPlayingPanel;

/*
 * Author : Nguyen Khanh Hung
 * Description :
 * - This UI class show up all about audio player interface to user
 * - This consist of many necessary component ( button , seek bar,...)
 */
public class playerUI extends JPanel {
	private static final int MIN_WIDTH = 450;
	private static final int MIN_HEIGHT = 170;
	
	private SeekbarPanel seekbarpanel;		// the panel that contains the seek bar
	private ButtonsPanel buttonspanel;		// the panel that contains all button (play,next,prev,...)		
	private VolumePanel volumepanel;		// the panell that conatains the volume slider n mute switch
	public NowPlayingPanel playingPanel;
	
	
	
	public SeekbarPanel getSeekbarpanel() {
		return seekbarpanel; 
	}

	public ButtonsPanel getButtonspanel() {
		return buttonspanel;
	}

	public VolumePanel getVolumepanel() {
		return volumepanel;
	}

	public playerUI(int x,int y,int width,int height)
	{
		super();
		this.setLayout(null);
		if(width < MIN_WIDTH) width = MIN_WIDTH;
		if(height < MIN_HEIGHT) height = MIN_HEIGHT;
		setBounds(x, y, width, height);
		initComponent();
                setOpaque(false);
	}

	private void initComponent()
	{
		seekbarpanel = new SeekbarPanel(this);
		this.add(seekbarpanel);
		
		buttonspanel = new ButtonsPanel();
		buttonspanel.setBounds((this.getWidth()-buttonspanel.getWidth())/2, 
								30, 
								buttonspanel.getWidth(), 
								buttonspanel.getHeight());
		this.add(buttonspanel);
		
		volumepanel = new VolumePanel();
		volumepanel.setBounds(buttonspanel.getX() + buttonspanel.getWidth() + 5,
								30,
								volumepanel.getWidth(),
								volumepanel.getHeight());
		this.add(volumepanel);
	}
	
	public void addLogic(AudioPlayer player,NowPlayingPanel playingpanel)
	{
		seekbarpanel.setupEvent(player,playingpanel);
		buttonspanel.setupEvent(player,playingpanel);
		volumepanel.setupEvent(player,playingpanel);
		player.setUI(this);
	}
}
