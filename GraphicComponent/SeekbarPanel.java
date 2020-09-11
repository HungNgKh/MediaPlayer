package GraphicComponent;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicSliderUI;

import AudioPlayer.AudioPlayer;
import musicplayer.NowPlayingPanel;
import musicplayer.SongObject;

/*
 * Author : Nguyen Khanh Hung
 * Description :
 * - This panel contains the Seekbar 
 */
public class SeekbarPanel extends JPanel implements PlayerUISetup {
	
	private AutoMoveSlider Seekbar;

	// Constructor that organize all UI cpmponent
	public SeekbarPanel(playerUI parent) {
		super();
		this.setBounds(0, 0, parent.getWidth(), 25);
		setupUI();
	}

	public AutoMoveSlider getSeekbar() {
		return Seekbar;
	}
	
	@Override
	public void setupUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 153, 102), new Color(51, 153, 153)));
		Seekbar = new AutoMoveSlider(JSlider.HORIZONTAL, 1000);
		Seekbar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(Seekbar);		
	}

	@Override
	public void setupEvent(AudioPlayer player,NowPlayingPanel playingpanel) {
		Seekbar.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int frame;
				SongObject loadedsong = player.loadedSong();
				if(loadedsong != null)
				{
					frame = (	Seekbar.getValue() * loadedsong.getLengthinframes())/1000;
					player.seek(frame);
				}
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				Seekbar.stop();
				JSlider sourceSlider=(JSlider)arg0.getSource();
			    BasicSliderUI ui = (BasicSliderUI)sourceSlider.getUI();
			    int value = ui.valueForXPosition( arg0.getX() );
			    Seekbar.setValue(value);			
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				((JPanel)Seekbar.getParent()).setBorder(null);			
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				((JPanel)Seekbar.getParent()).setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 51, 102), new Color(0, 51, 153), new Color(0, 51, 102), new Color(0, 51, 153)));
			}
		});
		
		Seekbar.addMouseMotionListener(new MouseMotionListener() {		
			@Override
			public void mouseMoved(MouseEvent e) {}		
			@Override
			public void mouseDragged(MouseEvent e) {
				JSlider sourceSlider=(JSlider)e.getSource();
			    BasicSliderUI ui = (BasicSliderUI)sourceSlider.getUI();
			    int value = ui.valueForXPosition( e.getX() );
			    Seekbar.setValue(value);
			}
		});
		
		Seekbar.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent arg0) {}		
			@Override
			public void keyReleased(KeyEvent arg0) {}			
			@Override
			public void keyPressed(KeyEvent arg0) {		
				int frame;
				SongObject loadedsong = player.loadedSong();
				if(loadedsong != null)
				{
					frame = (	Seekbar.getValue() * loadedsong.getLengthinframes())/1000;
					player.seek(frame);
				}
			}
		});
		
		Seekbar.increase_value.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Seekbar.setValue(Seekbar.getValue() + 1);
				if(!player.getPlayThread().is_living())
				{
					switch(player.getRepeatmode())
					{
						case 0: if(playingpanel.pos == playingpanel.arrayList.size() - 1)
									player.stop();
								else{
									playingpanel.pos++;
									playingpanel.playSong();
								}
								break;
						case 1: playingpanel.playSong();
								break;
						case 2:	playingpanel.pos = (playingpanel.pos + 1) % playingpanel.arrayList.size();
								playingpanel.playSong();
								break;				
					}
					//playingpanel.pos = (playingpanel.pos + 1) % playingpanel.arrayList.size();
					//playingpanel.playSong();
				}
			}
		});	
	}

}
