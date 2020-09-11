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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicSliderUI;

import AudioPlayer.AudioPlayer;
import musicplayer.NowPlayingPanel;

/*
 * Author : Nguyen Khanh Hung
 * Description :
 * - This panel consists of slider to adjust volume and a button to switch mute
 */
public class VolumePanel extends JPanel implements PlayerUISetup {
	
	private JSlider VolumeSlider;
	private JButton VolumeButton;
	
	public JSlider getVolumeSlider() {
		return VolumeSlider;
	}

	public JButton getVolumeButton() {
		return VolumeButton;
	}

	public VolumePanel() {
		super();
		setupUI();
	}

	@Override
	public void setupUI() {
                this.setOpaque(false);
		this.setLayout(null);
		this.setSize(50, 125);
		this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 153, 102), new Color(0, 153, 153), new Color(0, 153, 102), new Color(0, 153, 153)));
		
		VolumeSlider = new JSlider(JSlider.VERTICAL, 0, 100, 50);
                VolumeSlider.setOpaque(false);
		VolumeSlider.setBounds(10, 5, 30, 70);
		VolumeSlider.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(VolumeSlider);
		
		VolumeButton = new JButton();
		VolumeButton.setBounds(10, 85, 30, 30);
		VolumeButton.setIcon((new ImageIcon(this.getClass().getClassLoader().getResource("Image/mute-off.png"))));
		//setIcon(VolumeButton, "Image/repeat0.png");
		this.add(VolumeButton);
	}

	@Override
	public void setupEvent(AudioPlayer player,NowPlayingPanel playingPanel) {
		VolumeSlider.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				JSlider sourceSlider=(JSlider)arg0.getSource();
			    BasicSliderUI ui = (BasicSliderUI)sourceSlider.getUI();
			    int value = ui.valueForYPosition( arg0.getY() );
			    VolumeSlider.setValue(value);
			    player.setVolume((float)VolumeSlider.getValue()/100);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
		});
		
		VolumeSlider.addMouseMotionListener(new MouseMotionListener() {		
			@Override
			public void mouseMoved(MouseEvent e) {}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				JSlider sourceSlider=(JSlider)e.getSource();
			    BasicSliderUI ui = (BasicSliderUI)sourceSlider.getUI();
			    int value = ui.valueForYPosition( e.getY() );
			    VolumeSlider.setValue(value);
			    player.setVolume((float)VolumeSlider.getValue()/100);
			}
		});
		
		VolumeSlider.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
			    player.setVolume((float)VolumeSlider.getValue()/100);
			}
		});
		
		VolumeButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				player.toggle_mute();
				if(player.isMute() == true)
				{
					VolumeButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Image/mute-on.png")));
				}
				else
				{
					VolumeButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Image/mute-off.png")));
				}
			}
		});
	}

}
