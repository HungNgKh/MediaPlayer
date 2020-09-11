package AudioPlayer;


import javax.swing.ImageIcon;

import GraphicComponent.AutoMoveSlider;
import GraphicComponent.ButtonsPanel;
import GraphicComponent.playerUI;
import musicplayer.SongObject;

/*
 * Author : Nguyen Khanh Hung
 * Description :
 * - This implement all method of audio player in IDLE status
 */
public class IdleState implements AudioPlayerState {

	@Override
	public void load(AudioPlayer player,SongObject datasource) {
		player.getPlayThread().createthread(datasource, 0);
		player.getUI()
				.getSeekbarpanel()
					.getSeekbar().setDelayTime(Math.toIntExact(datasource.getDuration())/1000000);
		player.setStatus(AudioPlayer.STANDBY);
	}

	@Override
	public void play(AudioPlayer player) {
		player.setStatus(AudioPlayer.IDLE);

	}

	@Override
	public void stop(AudioPlayer player) {
		player.setStatus(AudioPlayer.IDLE);

	}

	@Override
	public void seek(AudioPlayer player, int pos) {
		player.setStatus(AudioPlayer.IDLE);
	}

	@Override
	public void updateUI(playerUI UI) {
		ButtonsPanel buttonspanel = UI.getButtonspanel();
		buttonspanel.getPlayButton().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Image/play.png")));
		buttonspanel.getPlayButton().setEnabled(false);
		buttonspanel.getStopButton().setEnabled(false);
		buttonspanel.getNextButton().setEnabled(false);
		buttonspanel.getPrevButton().setEnabled(false);
		buttonspanel.getFirstButton().setEnabled(false);
		buttonspanel.getLastButton().setEnabled(false);
		AutoMoveSlider slider = UI.getSeekbarpanel().getSeekbar();
		slider.setValue(0);
		slider.stop();
	}

}
