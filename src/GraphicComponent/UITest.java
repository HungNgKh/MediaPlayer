package GraphicComponent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class UITest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UITest frame = new UITest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UITest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(45, 80, 395, 161);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 395, 155);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 30, 340, 125);
		panel_4.add(panel_1);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 153, 102), new Color(0, 153, 153), new Color(0, 153, 102), new Color(0, 153, 153)));
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBackground(new Color(51, 153, 102));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(140, 10, 60, 60);
		panel_1.add(btnNewButton);
		
		JButton button = new JButton("New button");
		button.setBounds(75, 20, 60, 40);
		panel_1.add(button);
		
		JButton button_3 = new JButton("New button");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_3.setBounds(10, 20, 60, 40);
		panel_1.add(button_3);
		
		JButton button_2 = new JButton("New button");
		button_2.setBounds(270, 20, 60, 40);
		panel_1.add(button_2);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(205, 20, 60, 40);
		panel_1.add(button_1);
		
		JButton button_6 = new JButton("New button");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_6.setBounds(140, 75, 60, 40);
		panel_1.add(button_6);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(300, 85, 30, 30);
		panel_1.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 51, 102), new Color(0, 51, 153), new Color(0, 51, 102), new Color(0, 51, 153)));
		panel.setBounds(0, 0, 340, 25);
		panel_4.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JSlider slider = new JSlider();
		panel.add(slider);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(345, 30, 50, 125);
		panel_4.add(panel_3);
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 153, 102), new Color(0, 153, 153), new Color(0, 153, 102), new Color(0, 153, 153)));
		panel_3.setLayout(null);
		
		JSlider slider_2 = new JSlider();
		slider_2.setBounds(10, 5, 30, 75);
		panel_3.add(slider_2);
		slider_2.setOrientation(SwingConstants.VERTICAL);
		
		JButton button_5 = new JButton("New button");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_5.setBounds(10, 85, 30, 30);
		panel_3.add(button_5);
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 153, 102), new Color(51, 153, 153)));
			}
			
			
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setBorder(null);
			}
		});
	}
}
