/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Doan Phuc
 */
public class NotificationFrame extends Dialog {
    private NowPlayingPanel playing;
    int i=4;
    int x = Toolkit.getDefaultToolkit().getScreenSize().width;
    int y = Toolkit.getDefaultToolkit().getScreenSize().height - 124;
    Timer timer = null;
    TimerTask task;
    public NotificationFrame(JFrame frame,boolean bool,NowPlayingPanel playing)
    {
        super(frame,bool);
        this.playing = playing;
        initComponent();
        playing.setIcon(Image, playing.arrayList.get(playing.pos).getImage());
        Title.setText(playing.arrayList.get(playing.pos).getName());
        Album.setText(playing.arrayList.get(playing.pos).getAlbum());
        Author.setText(playing.arrayList.get(playing.pos).getAuthor());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent we) {
                WindowOpenedEvent();
            }
            
});
    }
    private void initComponent()
    {
        setAlwaysOnTop(true);
        setUndecorated(true);
        setLayout(null);
        setFocusCycleRoot(false);
        setFocusableWindowState(false);
        setFocusable(false);
        setSize(300, 124);
        JPanel panel =  new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 300, 124);
        panel.setBackground(Color.BLACK);
        add(panel);
        Image.setBounds(4, 12, 100, 100);
        Title.setBounds(108,12,188,50);
        Album.setBounds(108, 62, 188, 25);
        Author.setBounds(108,87, 188, 25);
        panel.add(Image);
        panel.add(Title);
        panel.add(Album);
        panel.add(Author);
        Title.setFont(new javax.swing.plaf.FontUIResource("Dialog", 1, 20));
        Title.setForeground(new Color(0, 102, 255));
        Author.setForeground(Color.white);
        Album.setForeground(Color.WHITE);
    }
    private void WindowOpenedEvent()
    {
        task = new TimerTask() {
            @Override
            public void run() {
                if(i == 300)
                {
                    timer.cancel();
                    task = new TimerTask() {
                        @Override
                        public void run() {
                            i-= 4;
                            setLocation(x-i, y);
                            if(i==0)
                            {
                                dispose();
                                timer = null;
                            }
                        }
                    };
                    timer = new Timer();
                    timer.schedule(task, 2500, 7);
                }
                else
                {
                    i+=4;
                    setLocation(x - i, y);
                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 7);
    }
    private JLabel Image = new JLabel();
    private JLabel Title = new JLabel();
    private JLabel Album = new JLabel();
    private JLabel Author = new JLabel();
}
