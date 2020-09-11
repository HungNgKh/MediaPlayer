package AudioPlayer;


import javax.swing.ImageIcon;

import GraphicComponent.ButtonsPanel;
import GraphicComponent.playerUI;
import musicplayer.SongObject;

/*
 * Author : Nguyen Khanh Hung
 * Description :
 * - This implement all method of audio player in PLAYING status
 */
public class PlayingState implements AudioPlayerState {

	@Override
	public void load(AudioPlayer player,SongObject datasource) {
		player.release();
		player.load(datasource);
		player.play();
		
	}
	
	@Override
	public void play(AudioPlayer player) {
		PlayerThread thread = player.getPlayThread();
		thread.getkilled();
		SongObject song = thread.getSourcedata();
		int frame = (player.getUI()
						.getSeekbarpanel()
							.getSeekbar().getValue()
					*
					song.getLengthinframes())
					/1000;
		thread.createthread(song, frame);
		
		player.setStatus(AudioPlayer.PAUSE);
	}

	@Override
	public void stop(AudioPlayer player) {
		PlayerThread thread = player.getPlayThread();
		thread.getkilled();
		thread.createthread(thread.getSourcedata(), 0);
		player.setStatus(AudioPlayer.STANDBY);
	}
	
	@Override
	public void seek(AudioPlayer player,int pos) {
		PlayerThread thread = player.getPlayThread();
		thread.getkilled();
		thread.createthread(thread.getSourcedata(), pos);	
		player.getPlayThread().start();
		player.setStatus(AudioPlayer.PLAYING);
	}
	
	@Override
	public void updateUI(playerUI UI) {
		UI.getSeekbarpanel()
			.getSeekbar()
				.start();
		ButtonsPanel butpanel = UI.getButtonspanel();
		butpanel
			.getPlayButton()
				.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Image/pause.png")));
		butpanel
			.getStopButton().setEnabled(true);
	}

}
