package AudioPlayer;


import javax.swing.ImageIcon;

import GraphicComponent.AutoMoveSlider;
import GraphicComponent.ButtonsPanel;
import GraphicComponent.playerUI;
import musicplayer.SongObject;

/*
 * Author : Nguyen Khanh Hung
 * Description :
 * - This implement all method of audio player in STANDBY status
 */
public class StandbyState implements AudioPlayerState {

	@Override
	public void load(AudioPlayer player,SongObject datasource) {
		player.release();
		player.load(datasource);
	}
	
	@Override
	public void play(AudioPlayer player) {
		player.getPlayThread().start();
		player.setStatus(AudioPlayer.PLAYING);
	}

	@Override
	public void stop(AudioPlayer player) {
		player.setStatus(AudioPlayer.STANDBY);
	}
	
	@Override
	public void seek(AudioPlayer player,int pos) {
		PlayerThread thread = player.getPlayThread();
		thread.createthread(thread.getSourcedata(), pos);	
	}

	@Override
	public void updateUI(playerUI UI) {
		AutoMoveSlider slider = UI.getSeekbarpanel().getSeekbar();
		slider.stop();
		slider.setValue(0);
		
		ButtonsPanel panel = UI.getButtonspanel();
		panel.getPlayButton().setEnabled(true);
		panel.getPlayButton()
				.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Image/play.png")));
		panel.getStopButton()
				.setEnabled(false);
		panel.getNextButton().setEnabled(true);
		panel.getPrevButton().setEnabled(true);
		panel.getFirstButton().setEnabled(true);
		panel.getLastButton().setEnabled(true);
	}
}
