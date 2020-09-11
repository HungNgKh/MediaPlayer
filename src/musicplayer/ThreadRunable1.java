/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Doan Phuc
 */
public class ThreadRunable1 implements Runnable{
    File file[];
    int i1,i2;
    NowPlayingPanel nowplayingPanel;
    ArrayList listPanel ;
    ArrayList listSong ;
    public ThreadRunable1(File file[],int i1, int i2, NowPlayingPanel nowplayingPanel,ArrayList listPanel ,ArrayList listSong )
    {
        this.file = file;
        this.i1 = i1;
        this.i2 = i2;
        this.nowplayingPanel = nowplayingPanel;
        this.listPanel = listPanel;
        this.listSong = listSong;
    }
    @Override
    public void run() {
        SongObject song ;
        SongPanel panel;
        for(int i=i1;i<=i2;i++)
        {
            if(file[i].isFile())
            {
                String temp = file[i].getName();
                int lastIndex = temp.lastIndexOf(".");
                if(lastIndex !=-1&&temp.substring(lastIndex+1).toLowerCase().compareTo("mp3")==0)
                {
                    try {
                        song = new SongObject(file[i].getPath());
                        listSong.add(song);
                        panel = new SongPanel(listSong, 0, nowplayingPanel);
                        listPanel.add(panel);
                    } catch (Exception ex) {}
                }
            }
        }
    }
}
