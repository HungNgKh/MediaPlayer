/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import AudioPlayer.AudioPlayer;
import GraphicComponent.playerUI;
import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 *
 * @author Doan Phuc
 */
public class MusicPlayerGUI extends JFrame{
    final int defaultWidth = 1000;
    final int defaultHeight = 625;
    final static public String locationFolder = System.getProperty("user.home")+"/Documents/MediaPlayer";
    /**
     * @param args the command line arguments
     */
    public MusicPlayerGUI(){
        initComponent(defaultHeight,defaultWidth);
    }
    private void initComponent(int defaultHeight, int defaultWidth)
    {
        setSize(defaultWidth, defaultHeight);
        setLocationRelativeTo(null);
        d = getSize();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Music Player demo");
        setResizable(false);
        setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("Image/iconApp.png")).getImage());
        setLayout(null);
        /////////////////////////////////////////////////
        playerui = new playerUI(0, 410, (int) d.getWidth(), 170);
        add(playerui);
        //////////////////////////////////////////////////
        playingPanel = new NowPlayingPanel(720, 40, 270, 371,player,this);
        add(playingPanel);
        playerui.playingPanel = playingPanel;
        playerui.addLogic(player, playingPanel);
        /////////////////////////////////////////////////
        menuBar = new MenuBar(this,playingPanel);
        this.setJMenuBar(menuBar);
        ///////////////////////////////////////////////////
        pane2 = new javax.swing.JScrollPane();
        panel1.setLayout(null);
        add(listName);
        listName.setBounds((int)d.getWidth()/25*6,0,(int)d.getWidth()/25*12,(int)d.getHeight()/15);
        listName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listName.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        listName.setBorder(SongPanel.line1);
        listName.setFont(new javax.swing.plaf.FontUIResource("Dialog", 1, 20));
        listName.setForeground(new java.awt.Color(0, 0, 102));
        tree = new Library_treeNode(panel1,pane2,listName,playingPanel);
        loadData();
        //////////////////////////////////////////////////
        pane1 = new javax.swing.JScrollPane();
        pane1.setViewportView(tree.getTree());
        pane1.setOpaque(false);
        pane1.getViewport().setOpaque(false);
        ((JScrollBar) pane1.getHorizontalScrollBar()).setOpaque(false);
        ((JScrollBar) pane1.getVerticalScrollBar()).setOpaque(false);
        add(pane1);
        pane1.setBounds(0, 40, 240, 371);
        ////////////////////////////////////////////
        pane2.setViewportView(panel1);
        ((JScrollBar) pane2.getHorizontalScrollBar()).setOpaque(false);
        ((JScrollBar) pane2.getVerticalScrollBar()).setOpaque(false);
        pane2.getViewport().setOpaque(false);
        pane2.setOpaque(false);
        panel1.setOpaque(false);
        panel1.setPreferredSize(new Dimension(0, 0));
        add(pane2);
        pane2.setBounds((int)d.getWidth()/25*6,40, (int)d.getWidth()/25*12, 371);
        add(backGround);
        backGround.setBounds(0, 0, d.width, d.height);
        playingPanel.setIcon(backGround, "Image/"+MenuBar.background[tree.file_method.loadCurrentBackGround()]);
    }
    private void loadData()
    {
        File file = new File(locationFolder);
        String[] childFile;
        if(!file.exists())
            file.mkdir();
        childFile = file.list();
        for(int i = 0 ; i< childFile.length ; i++)
        {
            String temp = childFile[i];
            int lastIndex = temp.lastIndexOf(".");
            if(lastIndex != -1&&temp.substring(lastIndex+1).toLowerCase().compareTo("list")==0)
                tree.addToPlaylistNode(temp.substring(0, lastIndex));
        }
        tree.expandAllNodes();
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MusicPlayerGUI().setVisible(true);
            }
        });
    }
    private Dimension d;
    public javax.swing.JScrollPane pane1,pane2;
    public Library_treeNode tree;
    public JPanel panel1 = new JPanel();
    public JLabel listName = new JLabel();
    public playerUI playerui;
    public NowPlayingPanel playingPanel;
    public AudioPlayer player = new AudioPlayer();
    private MenuBar menuBar;
    public JLabel backGround = new JLabel();
}
