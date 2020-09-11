package AudioPlayer;


import GraphicComponent.playerUI;
import musicplayer.SongObject;

// This is interface of player status that handle behavior for each status
public interface AudioPlayerState {
	public void load(AudioPlayer player,SongObject datasource);
	public void play(AudioPlayer player);
	public void stop(AudioPlayer player);
	public void seek(AudioPlayer player,int pos);
	public void updateUI(playerUI UI);
}
