/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Doan Phuc
 */
public class AboutFrame extends JFrame {
    public AboutFrame()
    {
        initComponent();
    }
    private void initComponent()
    {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(420, 350);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setTitle("About Music Plyer");
        setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("Image/iconApp.png")).getImage());
        backGround = new JPanel(){
            ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("Image/danterock.png"));
            public void paintComponent(Graphics g)
            {
                Dimension d = getSize();
                g.drawImage(icon.getImage(),0,0,d.width,d.height,null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        add(backGround);
        backGround.setLayout(null);
        /////////////////////////////
        la0.setBounds(0, 30, 420, 30);
        la1.setBounds(0, 80, 420, 30);
        la2.setBounds(0, 110, 420, 30);
        la3.setBounds(0, 140, 420, 30);
        la4.setBounds(0, 170, 420, 30);
        la5.setBounds(0, 200, 420, 30);
        la6.setBounds(0, 230, 420, 30);
        ///////////////////////////////
        la0.setForeground(Color.white);
        la1.setForeground(Color.white);
        la2.setForeground(Color.white);
        la3.setForeground(Color.white);
        la4.setForeground(Color.white);
        la5.setForeground(Color.white);
        la6.setForeground(Color.white);
        //////////////////////////////
        la0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        la1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        la2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        la3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        la4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        la5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        la6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        la0.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        la1.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        la2.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        la3.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        la4.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        la5.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        la6.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        //////////////////////////////
        la0.setFont(new Font("Tahoma", 1, 26));
        la1.setFont(new Font("Tahoma", 1, 14));
        la2.setFont(new Font("Tahoma", 1, 14));
        la3.setFont(new Font("Tahoma", 1, 14));
        la4.setFont(new Font("Tahoma", 1, 14));
        la5.setFont(new Font("Tahoma", 1, 11));
        la6.setFont(new Font("Tahoma", 1, 11));
        //////////////////////////////
        backGround.add(la0);
        backGround.add(la1);
        backGround.add(la2);
        backGround.add(la3);
        backGround.add(la4);
        backGround.add(la5);
        backGround.add(la6);
    }
    private JPanel backGround ;
    private JLabel la0 = new JLabel("Music Player");
    private JLabel la1 = new JLabel("Author : 2016 Phúc Hưng group");
    private JLabel la2 = new JLabel("Version : 1.0");
    private JLabel la3 = new JLabel("Platform : Java");
    private JLabel la4 = new JLabel("Facebook :");
    private JLabel la5 = new JLabel("https://www.facebook.com/dungnhi9x");
    private JLabel la6 = new JLabel("https://www.facebook.com/profile.php?id=100004094562628&fref=ts");
}
