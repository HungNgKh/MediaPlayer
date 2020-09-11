package GraphicComponent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.event.ChangeEvent;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class app extends JFrame {

	private JPanel contentPane;
	private Timer increaseValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app frame = new app();
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
	public app() {
		setBounds(new Rectangle(50, 50, 600, 400));
		JSlider jSlider1 = new JSlider();
		jSlider1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				
			}
		});
		jSlider1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JSlider sourceSlider=(JSlider)e.getSource();
			    BasicSliderUI ui = (BasicSliderUI)sourceSlider.getUI();
			    int value = ui.valueForXPosition( e.getX() );
			    jSlider1.setValue(value);
			}
		});
		jSlider1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JSlider sourceSlider=(JSlider)e.getSource();
			    BasicSliderUI ui = (BasicSliderUI)sourceSlider.getUI();
			    int value = ui.valueForXPosition( e.getX() );
			    jSlider1.setValue(value);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				JSlider sourceSlider=(JSlider)e.getSource();
			    BasicSliderUI ui = (BasicSliderUI)sourceSlider.getUI();
			    int value = ui.valueForXPosition( e.getX() );
			    jSlider1.setValue(value);
			}
		});
	    jSlider1.setValue(0);
	    jSlider1.setMaximum(100);
	    jSlider1.setBounds(0, 50, 300, 50);
	    increaseValue = new Timer(10, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
			
		                jSlider1.setValue(jSlider1.getValue() + 1);	                
		               // increaseValue.setDelay(increaseValue.getDelay());
		        
				
			}
		});
	    getContentPane().setLayout(null);
	    getContentPane().add(jSlider1);
	    JButton moveSlider = new JButton("Start");
	    moveSlider.setBounds(63, 147, 191, 122);
	    moveSlider.addActionListener((new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent event) {
	            increaseValue.start();
	        }
	    }));
	    getContentPane().add(moveSlider);
	}
}
