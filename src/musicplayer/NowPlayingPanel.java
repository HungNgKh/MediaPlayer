/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import AudioPlayer.AudioPlayer;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author Doan Phuc
 */
public class NowPlayingPanel extends JPanel implements MouseListener{
    private final int x,y,width,height;
    public ArrayList<SongObject> arrayList;
    public int pos;
    public AudioPlayer player;
    private MusicPlayerGUI gui;
    public NowPlayingPanel(int x,int y,int width, int height,AudioPlayer player,MusicPlayerGUI gui)
    {
        this.gui = gui;
        this.player = player;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        initComponent();
        list.addMouseListener(this);
    }
    private void initComponent()
    {
        this.setLayout(null);
        setOpaque(false);
        setBounds(x, y, width, height);
        setBorder(SongPanel.line1);
        /////////////
        add(ListPane);ListPane.setBounds(0, this.getHeight()*3/10, this.getWidth(), this.getHeight()*7/10);
        ListPane.setViewportView(list);
        ListPane.setOpaque(false);
        ((JScrollBar) ListPane.getHorizontalScrollBar()).setOpaque(false);
        ((JScrollBar) ListPane.getVerticalScrollBar()).setOpaque(false);
        ListPane.getViewport().setOpaque(false);
        list.setOpaque(false);
        ((DefaultListCellRenderer) list.getCellRenderer()).setOpaque(false);
        /////////////
        add(infoMp3);
        infoMp3.setBounds(0, 0, this.getWidth(), this.getHeight()*3/10);
        infoMp3.setBorder(SongPanel.line1);
        infoMp3.setLayout(null);
        infoMp3.setOpaque(false);
        //////////////
        infoMp3.add(image);
        image.setBounds(4, 18, 75, 75);
        setIcon(image, "Image/icon1.png");
        infoMp3.add(but2);infoMp3.add(but3);infoMp3.add(but4);
        but2.setBounds(79,18,185,35);but3.setBounds(79, 53, 185, 20);but4.setBounds(79, 73, 185, 20);
        but2.setFont(new javax.swing.plaf.FontUIResource("Dialog", 1, 25));
//        but2.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
//        but3.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
//        but4.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        but2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but2.setForeground(new Color(0,70,70));
        but3.setForeground(new Color(0,70,70));
        but4.setForeground(new Color(0,70,70));
    }
    @Override
    public void mouseClicked(MouseEvent me) {
        Object ob = me.getSource();
        if(ob == list && me.getClickCount()==2)
        {
            pos = list.getSelectedIndex();
            playSong();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
    
    public void setIcon(JLabel label ,String filename){
        try {
            BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResource(filename));
            int x =label.getSize().width;
            int y =label.getSize().height;
            int ix =image.getWidth();
            int iy =image.getHeight();
            int dx=0;
            int dy=0;
            if(x /y > ix /iy){
                dy=y;
                dx=dy*ix /iy;
            }else{
                dx=x;
                dy=dx*iy/ix;
            }
            ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException ex) {
            Logger.getLogger(SongPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setIcon(JLabel label, BufferedImage image)
    {
        int x =label.getSize().width;
        int y =label.getSize().height;
        int ix =image.getWidth();
        int iy =image.getHeight();
        int dx=0;
        int dy=0;
        if(x /y > ix /iy){
            dy=y;
            dx=dy*ix /iy;
        }else{
            dx=x;
            dy=dx*iy/ix;
        }
        ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
        label.setIcon(icon);
    }
    public void playSong()
    {
        but2.setText(arrayList.get(pos).getName());
        but3.setText(arrayList.get(pos).getAlbum());
        but4.setText(arrayList.get(pos).getAuthor());
        setIcon(image, arrayList.get(pos).getImage());
        player.setStatus(AudioPlayer.PLAYING);
        player.load(arrayList.get(pos));
        new NotificationFrame(gui,true,this).setVisible(true);
    }
    
    
    public JList list = new JList();
    private JPanel infoMp3 = new JPanel();
    public JLabel image = new JLabel();
    public JLabel but2= new JLabel();
    public JLabel but3= new JLabel();
    public JLabel but4= new JLabel();
    private JScrollPane ListPane = new JScrollPane();
}
