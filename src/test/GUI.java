package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javafx.scene.layout.Border;
import javafx.scene.shape.Box;

public class GUI extends JFrame{
	
	public GUI()
	{
		initCompone();
		but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int list_size = new Integer(list.size());
				Object ob = new Object(text1.getText(),text2.getText());
				list.add(ob);
				System.out.println(list.size()+"\n"+ob.s1+ob.s2);
				panel.setPreferredSize(new Dimension(250, list_size*50));
				panel.add(ob);
				ob.setBounds(0, list_size*50, ob.getWidth(), ob.getHeight());;
				//panel.add(javax.swing.Box.createVerticalBox());
				pane.updateUI();
			}
		});
	}
	private void initCompone()
	{
		//GroupLayout groupL = new GroupLayout(panel);
		pane.setBounds(150,10,300,500);
		add(pane); 
		pane.setViewportView(panel);
		panel.setPreferredSize(new Dimension(0, 0));
		panel.setLayout(null);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,600);
		add(text1); text1.setBounds(0,0,150,30);
		add(text2); text2.setBounds(0,30,150,30);
		add(but); but.setBounds(0,60,50,30);	
	}
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setVisible(true);
	}
	private JTextField text1 = new JTextField();
	private JTextField text2 = new JTextField();
	private JButton but = new JButton("ENTER");
	JScrollPane pane = new JScrollPane();
	JPanel panel = new JPanel();
	ArrayList<Object> list = new ArrayList<Object>();
}
