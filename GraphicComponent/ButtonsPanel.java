package GraphicComponent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import AudioPlayer.AudioPlayer;
import musicplayer.NowPlayingPanel;

/*
 * Author : Nguyen Khanh Hung
 * Description :
 * - This panel contains all button to controll player
 * - Include: play/pause , stop , next , previous , last, first , repeat mode
 */
public class ButtonsPanel extends JPanel implements PlayerUISetup {

	private JButton PlayButton;
	private JButton NextButton;
	private JButton PrevButton;
	private JButton LastButton;
	private JButton FirstButton;
	private JButton StopButton;
	private JButton RepeatButton;
	
	public JButton getRepeatButton() {
		return RepeatButton;
	}

	public JButton getPlayButton() {
		return PlayButton;
	}

	public JButton getNextButton() {
		return NextButton;
	}

	public JButton getPrevButton() {
		return PrevButton;
	}

	public JButton getLastButton() {
		return LastButton;
	}

	public JButton getFirstButton() {
		return FirstButton;
	}

	public JButton getStopButton() {
		return StopButton;
	}

	public ButtonsPanel() {
		super();
		setupUI();
	}
	
	@Override
	public void setupUI() {
		this.setLayout(null);
		this.setSize(340,125);
		this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 153, 102), new Color(0, 153, 153), new Color(0, 153, 102), new Color(0, 153, 153)));
		
		PlayButton = new JButton();
		PlayButton.setBounds(140, 10, 60, 60);
		PlayButton.setIcon((new ImageIcon(this.getClass().getClassLoader().getResource("Image/play.png"))));
		this.add(PlayButton);
		
		NextButton = new JButton();
		NextButton.setBounds(205, 20, 60, 40);
		NextButton.setIcon((new ImageIcon(this.getClass().getClassLoader().getResource("Image/next.png"))));
		this.add(NextButton);
		
		PrevButton = new JButton();
		PrevButton.setBounds(75 , 20, 60, 40);
		PrevButton.setIcon((new ImageIcon(this.getClass().getClassLoader().getResource("Image/prev.png"))));
		this.add(PrevButton);
		
		LastButton = new JButton();
		LastButton.setBounds(270, 20, 60, 40);
		LastButton.setIcon((new ImageIcon(this.getClass().getClassLoader().getResource("Image/last.png"))));
		this.add(LastButton);
		
		FirstButton = new JButton();
		FirstButton.setBounds(10, 20, 60, 40);
		FirstButton.setIcon((new ImageIcon(this.getClass().getClassLoader().getResource("Image/first.png"))));
		this.add(FirstButton);
		
		StopButton = new JButton();
		StopButton.setBounds(140, 75, 60, 40);
		StopButton.setIcon((new ImageIcon(this.getClass().getClassLoader().getResource("Image/stop.png"))));
		this.add(StopButton);
		
		RepeatButton = new JButton();
		RepeatButton.setBounds(300, 85, 30, 30);
		RepeatButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Image/repeat0.png")));
		RepeatButton.setToolTipText("Repeat Off");
		this.add(RepeatButton);
	}

	
	@Override
	public void setupEvent(AudioPlayer player,NowPlayingPanel playingpanel) {
		PlayButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.play();			
			}
		});
		
		StopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.stop();			
			}
		});
		
		NextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playingpanel.pos = (playingpanel.pos + 1) % playingpanel.arrayList.size();
	//			player.load(playingpanel.arrayList.get(playingpanel.pos));
                                playingpanel.playSong();
			}
		});
		
		PrevButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playingpanel.pos = playingpanel.pos+playingpanel.arrayList.size()-1;
				playingpanel.pos = playingpanel.pos % playingpanel.arrayList.size();
                                playingpanel.playSong();
//				player.load(playingpanel.arrayList.get(playingpanel.pos));
			}
		});
		
		FirstButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playingpanel.pos = 0;
                                playingpanel.playSong();
//				player.load(playingpanel.arrayList.get(playingpanel.pos));
			}
		});
		
		LastButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playingpanel.pos = playingpanel.arrayList.size()-1;
                                playingpanel.playSong();
//				player.load(playingpanel.arrayList.get(playingpanel.pos));
			}
		});
		
		RepeatButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.toggle_Repeat();
				int mode = player.getRepeatmode();
				if(mode == 0)
				{
					RepeatButton.setToolTipText("Repeat Off");
					RepeatButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Image/repeat0.png")));
				}					
				else if(mode == 1)
				{
					RepeatButton.setToolTipText("Repeat One");
					RepeatButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Image/repeat1.png")));
				}		
				else if(mode == 2)
				{
					RepeatButton.setToolTipText("Repeat All");
					RepeatButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Image/repeat2.png")));
				}
			}
		});
	}

}
