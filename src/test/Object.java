package test;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Object extends JPanel {
	String s1,s2;
	public JLabel label1,label2;
	public Object(String s1,String s2)
	{
		this.s1 = s1;
		this.s2 = s2;
		initCompone();
	}
	private void initCompone()
	{
		setLayout(null);
		label1 = new JLabel();
		label2 = new JLabel();
		//setAlignmentY(TOP_ALIGNMENT);
		setSize(250,50);
		setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
		label1.setText(s1);label2.setText(s2);
		add(label1); label1.setBounds(50,0,200,25);
		add(label2); label1.setBounds(50,25,200,25);
	}
}
