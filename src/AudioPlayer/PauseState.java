package AudioPlayer;


import javax.swing.ImageIcon;

import GraphicComponent.playerUI;
import musicplayer.SongObject;

/*
 * 
 */
public class PauseState implements AudioPlayerState {

	@Override
	public void load(AudioPlayer player,SongObject datasource) {
		player.release();
		player.load(datasource);
	}
	
	@Override
	public void play(AudioPlayer player) {
		PlayerThread thread = player.getPlayThread();
		thread.start();
		player.setStatus(AudioPlayer.PLAYING);
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
	}
	
	@Override
	public void updateUI(playerUI UI) {
		UI.getButtonspanel()
			.getPlayButton()
				.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Image/play.png")));	
		UI.getSeekbarpanel()
			.getSeekbar()
				.stop();
	}
}
