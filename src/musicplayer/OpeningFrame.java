/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Doan Phuc
 */
public class OpeningFrame extends JFrame{
    public OpeningFrame()
    {
        initComponent();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent we) {
                WindowStart();
            }
        });
    }
    private void initComponent()
    {
        setUndecorated(true);
        setResizable(false);
        setSize(600,400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("Image/iconApp.png")).getImage());
        JPanel panel = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("Image/pic2.png"));
            public void paintComponent(Graphics g)
            {
                Dimension d = getSize();
                g.drawImage(icon.getImage(),0,0,d.width,d.height,null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };      
        add(panel);
    }
    private void WindowStart()
    {
        try {
            Thread.sleep(1500);
            for(int i=100;i>=0;i--)
            {
                setOpacity((float)i/100);
                Thread.sleep(20);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(OpeningFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        new MusicPlayerGUI().setVisible(true);
        dispose();
    }
    public static void main(String[] args) {
        new OpeningFrame().setVisible(true);
    }
}
