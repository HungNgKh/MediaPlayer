package GraphicComponent;

import javax.swing.JSlider;
import javax.swing.Timer;

/*
 *	Author: Nguyen Khanh Hung
 *  Description: 
 *  - This slider contains a Timer that cause itself automatic movement
 *  - The slider will increase its value each time actionEvent of Timer was fired
 *  - ActionEvent of Timer is fired frequently depend on the delay time set on it 
 */

public class AutoMoveSlider extends JSlider {
	Timer increase_value;
	
	public AutoMoveSlider(int ori,int max)
	{
		super(ori, 0, max, 0);
		increase_value = new Timer(0, null);
		increase_value.stop();
	}
	
	public void setDelayTime(int delay)
	{
		increase_value.setDelay(delay);
	}
	
	public int getDelayTime()
	{
		return increase_value.getDelay();
	}
	
	public void start()
	{
		if(!increase_value.isRunning())
			increase_value.start();
	}
	
	public void stop()
	{
		if(increase_value.isRunning())
			increase_value.stop();
	}
}
