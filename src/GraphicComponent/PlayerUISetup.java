package GraphicComponent;


import AudioPlayer.AudioPlayer;
import musicplayer.NowPlayingPanel;

/*
 * Author : Nguyen Khanh Hung
 * Description :
 * - This interface include 2 abtract method
 * - setupUI() will organize the layout of UI components
 * - setupEvent(...) will add the logic behavior to UI component
 */
public interface PlayerUISetup{
	public void setupUI();
	public void setupEvent(AudioPlayer player,NowPlayingPanel playingpanel);
}
