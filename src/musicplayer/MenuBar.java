/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Doan Phuc
 */
public class MenuBar extends JMenuBar implements ActionListener{
    private NowPlayingPanel playingPanel;
    private MusicPlayerGUI gui;
    MenuItem hide_showFrame = new MenuItem("Show Frame");
    MenuItem hide_exit = new MenuItem("Exit");
    Image Imagen = new ImageIcon(this.getClass().getClassLoader().getResource("Image/iconApp.png")).getImage();
    java.awt.PopupMenu popup = new java.awt.PopupMenu();
    TrayIcon trayIcon = new TrayIcon(Imagen,"Music Player",popup);
    SystemTray systemtray;
    int len = 8;
    static String background[] = {"pic1.png","pic2.png","pic3.png","pic4.png","pic5.png","pic6.png","pic7.png","pic8.png"};
    public MenuBar(MusicPlayerGUI gui,NowPlayingPanel playingPanel)
    {
        this.gui = gui;
        this.playingPanel = playingPanel;
        initComponent();
        trayIcon.setImageAutoSize(true);
        menuitem_exit.addActionListener(this);
        menuitem_open.addActionListener(this);
        menuitem_openURL.addActionListener(this);
        menuitem_hide.addActionListener(this);
        hide_exit.addActionListener(this);
        hide_showFrame.addActionListener(this);
        hide_showFrame.addActionListener(this);
        hide_exit.addActionListener(this);
        item_about.addActionListener(this);
        for(int i =0;i<len;i++)
            itemBackGround[i].addActionListener(this);
    }
    private void initComponent()
    {
        add(menuFile);
        add(menuView);
        add(menuHelp);
        popup.add(hide_showFrame);
        popup.add(hide_exit);
        menuFile.add(menuitem_open);
        menuFile.add(menuitem_openURL);
        menuFile.add(menuitem_exit);
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuView.add(menuitem_hide);
        menuView.add(menuBackGround);
        menuView.setMnemonic(KeyEvent.VK_V);
        menuBackGround.setMnemonic(KeyEvent.VK_B);
        menuitem_openURL.setMnemonic(KeyEvent.VK_U);
        menuitem_hide.setMnemonic(KeyEvent.VK_H);
        menuitem_open.setMnemonic(KeyEvent.VK_O);
        menuitem_exit.setMnemonic(KeyEvent.VK_X);
        menuitem_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_MASK));
        menuitem_openURL.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,KeyEvent.CTRL_MASK));
        menuitem_hide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_MASK));
        itemBackGround = new JMenuItem[len];
        for(int i=0;i<len;i++)
        {
            itemBackGround[i] = new JMenuItem("BackGround "+(i+1));
            menuBackGround.add(itemBackGround[i]);
        }
        menuHelp.add(item_about);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object ob = ae.getSource();
        if(ob == menuitem_exit || ob == hide_exit)
            System.exit(0);
        if(ob == menuitem_open)
            openFile();
        if(ob == menuitem_openURL)
            openfileURL();
        if(ob == menuitem_hide)
            hideFrame();
        if(ob == hide_showFrame)
            showFrame();
        if(ob == item_about)
            new AboutFrame().setVisible(true);
        for(int i=0;i<len;i++)
            if(ob == itemBackGround[i])
                changeBackGround(i);
    }
    private void showFrame()
    {
        systemtray.remove(trayIcon);
        gui.setVisible(true);
        gui.setExtendedState(gui.NORMAL);
    }
    private void hideFrame()
    {
        try {
            if(SystemTray.isSupported()){
                systemtray = SystemTray.getSystemTray();
                systemtray.add(trayIcon);
                gui.dispose();
            }                    
        } catch (AWTException ex) {
            Logger.getLogger(MenuBar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void openFile()
    {
        File file[] = null;
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(true);
        SongObject song;
        DefaultListModel<String> listmodel = new DefaultListModel<>();
        int status = fileChooser.showOpenDialog(null);
        int lastIndex;
        String temp;
        if(status == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFiles();
            if(playingPanel.arrayList != null)
                playingPanel.arrayList.clear();
            else
                playingPanel.arrayList = new ArrayList<>();
            playingPanel.pos = 0;
            for(File file1 : file)
            {
                if(file1.isFile())
                {
                    temp = file1.getName();
                    lastIndex = temp.lastIndexOf(".");
                    if(lastIndex !=-1&&temp.substring(lastIndex+1).toLowerCase().compareTo("mp3")==0)
                        try {
                            song = new SongObject(file1.getPath());
                            playingPanel.arrayList.add(song);
                            listmodel.addElement(song.getName());
                            playingPanel.list.setModel(listmodel);
                        } catch (Exception ex) {}
                }
            }
            if(playingPanel.arrayList.size()>0)
                playingPanel.playSong();
            else
                JOptionPane.showMessageDialog(null, "Can't open file", "Warning !!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void openfileURL()
    {
        String temp;
        int lastIndex;
        File file;
        String s = JOptionPane.showInputDialog(null, "Open URL:", "Music Player Demo", JOptionPane.PLAIN_MESSAGE);
        if(s == null)
            s = "";
        file = new File(s);
        if(!file.exists())
            JOptionPane.showMessageDialog(null, "File not exists", "NOTIFICATION !!", JOptionPane.ERROR_MESSAGE);
        else
        {
            DefaultListModel<String> listmodel = new DefaultListModel<>();
            SongObject song;
            temp = file.getName();
            lastIndex = temp.lastIndexOf(".");
            if(lastIndex != -1 && temp.substring(lastIndex+1).compareTo("mp3")==0)
            {
                try{
                    song = new SongObject(file.getPath());
                    if(playingPanel.arrayList != null)
                        playingPanel.arrayList.clear();
                    else 
                        playingPanel.arrayList = new ArrayList<>();
                    playingPanel.arrayList.add(song);
                    playingPanel.pos =0;
                    listmodel.addElement(song.getName());
                    playingPanel.list.setModel(listmodel);
                    playingPanel.playSong();
                }catch(Exception e)
                {
                }
            }
        }
    }
    private void changeBackGround(int i)
    {
        File file = new File(FileMethods.locationFolder+"/MusicPlayer.dat");
        if(file.exists())
        {
            try {
                FileOutputStream out = new FileOutputStream(file);
                out.write(i);
                out.close();
            } catch (Exception ex) {
                Logger.getLogger(MenuBar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        playingPanel.setIcon(gui.backGround, "Image/"+background[i]);
    }
    
    
    
    private final JMenu menuFile = new JMenu("File");
    private final JMenu menuView = new JMenu("View");
    private final JMenu menuHelp = new JMenu("Help");
    private final JMenu menuBackGround = new JMenu("Set BackGround");
    private final JMenuItem menuitem_exit = new JMenuItem("Exit");
    private final JMenuItem menuitem_open = new JMenuItem("Open file");
    private final JMenuItem menuitem_openURL = new JMenuItem("Open URL");
    private final JMenuItem menuitem_hide = new JMenuItem("Hide");
    private final JMenuItem item_about = new JMenuItem("About Music Player");
    private JMenuItem[] itemBackGround;
    private JFileChooser fileChooser = new JFileChooser();
}
