package AudioPlayer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;
import javax.swing.JOptionPane;
import javax.sound.sampled.Line.Info;

/*
 * Author : Nguyen Khanh Hung
 * Description :
 * - This is a part of audio player that adjust volume and toggle mute on/off
 */
public class VolumeController {
	private boolean mute;
	private float volume;
	private FloatControl controller;
	
	public VolumeController(float vol) {
		mute = false;
		Info source = Port.Info.SPEAKER;
		if (AudioSystem.isLineSupported(source)) 
	    {
			try
			{
				Port outline = (Port) AudioSystem.getLine(source);
				outline.open();                
				controller = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
			} 
			catch (LineUnavailableException ex) 
			{
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.toString(), ex);
				JOptionPane.showMessageDialog(null, ex.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
				
			} 
	    }
		volume = vol;
		controller.setValue(volume);
	}
	
	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
		if(mute == false)
		{
			controller.setValue(volume);
		}
	}
	
	public boolean isMute() {
		return mute;
	}

	public void toggleMute() {
		this.mute ^= true;
		if(this.mute == false)
		{
			controller.setValue(volume);
		}
		else
		{
			controller.setValue(0.0f);
		}
	}

}
