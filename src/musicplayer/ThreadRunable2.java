package musicplayer;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Doan Phuc
 */
public class ThreadRunable2 implements Runnable{
    ArrayList<SongPanel> listPanel;
    int i1,i2;
    public ThreadRunable2(ArrayList<SongPanel> listPanel,int i1,int i2)
    {
        this.i1 = i1;
        this.i2 = i2;
        this.listPanel = listPanel;
    }
    @Override
    public void run() {
        for(int i =i1;i<=i2;i++)
        {
            listPanel.get(i).pos = i;
            listPanel.get(i).loadInfo();
        }
    }
    
}
