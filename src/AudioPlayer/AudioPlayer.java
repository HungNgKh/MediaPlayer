package AudioPlayer;

import GraphicComponent.playerUI;
import musicplayer.SongObject;

/*
 * Author : Nguyen Khanh Hung
 * Description :
 * - This class have all function of a audio player (play,adjust volume,...)
 */
public class AudioPlayer{
	public static final PlayingState PLAYING = new PlayingState();
	public static final PauseState PAUSE = new PauseState();
	public static final StandbyState STANDBY = new StandbyState();
	public static final IdleState IDLE = new IdleState();
	
	private int repeatmode; 					// 0 is off, 1 is repeat one, 2 is repeat all
	private VolumeController volumecontroller;	// the volume controller that can adjust volume, switch mute
	private AudioPlayerState status;			// the current status of player

	private final PlayerThread mainPlayingThread;	
	private playerUI UI;						

	public AudioPlayer() {
		repeatmode = 0;
		volumecontroller = new VolumeController(0.5f);
		mainPlayingThread = new PlayerThread();
		this.status = IDLE;
	}
	
	public AudioPlayerState getStatus() {
		return status;
	}
	
	public void load(SongObject loadsong)
	{
		this.status.load(this, loadsong);	
	}

	public void setStatus(AudioPlayerState status) {
		this.status = status;
		if(this.UI != null)
			status.updateUI(this.UI);
	}

	public void play()
	{
		this.status.play(this);
	}
	
	public void stop()
	{
		this.status.stop(this);
	}
	
	public void seek(int frame)
	{
		System.out.println("sasdasfasf");
		this.status.seek(this,frame);
	}
	
	public void toggle_mute()
	{
		volumecontroller.toggleMute();
	}
	
	public void setUI(playerUI UI)
	{
		this.UI = UI;
		this.setStatus(IDLE);
	}
	
	public playerUI getUI() {
		return UI;
	}
	
	public void setVolume(float value)  //from 0.0 to 1.0
	{        
		volumecontroller.setVolume(value);
	}
	
	public void toggle_Repeat()
	{
		this.repeatmode = (this.repeatmode + 1) %3;
	}
	
	public int getRepeatmode()
	{
		return this.repeatmode;
	}
	
	public boolean isMute()
	{
		return volumecontroller.isMute();
	}
	
	public PlayerThread getPlayThread()
	{
		return this.mainPlayingThread;
	}
	
	public SongObject loadedSong()
	{
		return mainPlayingThread.getSourcedata();
	}
	
	public void release()
	{
		this.mainPlayingThread.getkilled();
		this.mainPlayingThread.createthread(null, 0);
		this.setStatus(IDLE);
	}
	
}
