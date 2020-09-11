/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 *
 * @author Doan Phuc
 */
public class SongPanel extends JPanel implements MouseListener,ActionListener{
    private String Title;
    private String author;
    private String album;
    public int pos;
    public final ArrayList<SongObject> arrayList;
    private NowPlayingPanel playingPanel;
    private int state=0;
    private JMenuItem play_item = new JMenuItem("Play");
    public JMenuItem remove_item = new JMenuItem("Remove from List");
    public JPopupMenu menu_listSong = new JPopupMenu();
    static SoftBevelBorder line1 =  new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 153, 102), new Color(0, 153, 153), new Color(0, 153, 102), new Color(0, 153, 153));
    SoftBevelBorder line2 = new SoftBevelBorder(1, Color.blue, Color.blue, Color.blue, Color.blue);
    public SongPanel(ArrayList arrayList,int pos,NowPlayingPanel playingPanel)
    {
//        this.pos = pos;
        this.playingPanel = playingPanel;
        this.arrayList = arrayList;
        initComponent();
//        loadInfo();
        setToolTipText(Title);
        addMouseListener(this);    
        menu_listSong.add(play_item);
        menu_listSong.add(remove_item);
        play_item.addActionListener(this);
    }
    public void loadInfo()
    {
        Title = arrayList.get(pos).getName();
        author = arrayList.get(pos).getAuthor();
        album = arrayList.get(pos).getAlbum();
        l_title.setText (" Title    :   "+Title);
        l_artist.setText(" Author :   "+author);
        l_album.setText (" Album  :   "+album);
        playingPanel.setIcon(image, arrayList.get(pos).getImage());
        Long a = arrayList.get(pos).getDuration()/1000000/60;
        Long b = arrayList.get(pos).getDuration()/1000000%60;
        l_time.setText  (" Time    :   "+ a+" : "+b);
    }
    private void initComponent()
    {
        Color color = new Color(0,50,50);
        setOpaque(false);
        setLayout(null);
        setSize(473, 90);
        setBorder(line1);
        add(image);image.setBounds(2, 2, 88 , 86);
        add(l_title);l_title.setBounds(90, 0, 300, 50);
        l_title.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        l_title.setFont(new javax.swing.plaf.FontUIResource("Dialog", 1, 16));
        l_title.setForeground(color);
        l_album.setForeground(color);
        l_time.setForeground(color);
        l_artist.setForeground(color);
        add(l_artist);l_artist.setBounds(90, 60, 300, 15);
        add(l_album);l_album.setBounds(90, 75, 300, 15);
        add(l_time);l_time.setBounds(90,45 , 300, 15);
    }
    private final JLabel image = new JLabel();
    private final JLabel l_title = new JLabel();
    private final JLabel l_artist = new JLabel();
    private final JLabel l_album = new JLabel();
    private final JLabel l_time = new JLabel();
    
    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mousePressed(MouseEvent me) {
        setBorder(null);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(state == 1 )
        {
            setBorder(line2);
            if(SwingUtilities.isRightMouseButton(me))
            {
                Component b = (Component) me.getSource();
                Point p = me.getLocationOnScreen();
                menu_listSong.show(b, p.x, p.y);
                menu_listSong.setLocation(p.x,p.y);
            }
            else
            {
                setNowPlayingPanel();
            }
        }
        else
            setBorder(line1);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        state = 1;
        setBorder(line2);
    }

    @Override
    public void mouseExited(MouseEvent me) {
        state = 0;
        setBorder(line1);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object ob = ae.getSource();
        if(ob == play_item)
        {
            setNowPlayingPanel();
        }
    }
    public String removeElementAt(int i)
    {
        String s = "";
        arrayList.remove(i);
        for(SongObject song: arrayList)
            s += song.getPath()+"\n";
        return s;
    }
    private void setNowPlayingPanel()
    {
        DefaultListModel<String> listmodel = new DefaultListModel<>();
        playingPanel.list.removeAll();
        for(int i = 0;i< arrayList.size();i++)
            listmodel.addElement(arrayList.get(i).getName());
        playingPanel.list.setModel(listmodel);
        playingPanel.pos = pos;
        playingPanel.arrayList = arrayList;
        playingPanel.playSong();
    }
}
