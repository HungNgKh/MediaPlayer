/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Doan Phuc
 */
public class FileMethods {
    static final public String locationFolder = System.getProperty("user.home")+"/Documents/MediaPlayer";
    static final public String locationMyMusic = System.getProperty("user.home")+"/Music";
    public void createFile(String s) 
    {
        File file = new File(locationFolder);
        if(!file.exists())
            file.mkdir();
        file = new File(locationFolder+"/"+s+".list");
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Create File ERROR");
            }
        }
        else
            System.out.println("FILE EXISTS");
    }
    public void renameFile(String name,String rename)
    {
        File file = new File(locationFolder+"/"+name+".list");
        File newFile = new File(locationFolder+"/"+rename+".list");
        if(file.exists())
            file.renameTo(newFile);
        else
            System.out.println("rename ERROR");
    }
    public void deleteFile(String s)
    {
        s = locationFolder+"/"+s+".list";
        File file = new File(s);
        if(file.exists())
            file.delete();
        else
            System.out.println("delete ERROR");
    }
    public void writeToFile(String filename,String content) throws Exception
    {
        File file = new File(locationFolder+"/"+filename+".list");
        if(file.exists())
        {
            FileInputStream in = new FileInputStream(file);
            byte buf[] = new byte[(int)file.length()];
            in.read(buf);
            in.close();
            String s = new String(buf,"UTF-8");
            s = s+ content + "\n";
            ////////////////////////////////////////////
            FileOutputStream out = new FileOutputStream(file);
            byte data[] = s.getBytes();
            out.write(data);
            out.close();
        }
        else
            System.out.println("Write file ERROR");
    }
    
    public ArrayList readFromFile(String filename,NowPlayingPanel panel) throws FileNotFoundException, IOException
    {
        int count = 0;
        ArrayList arrayList = new ArrayList();
        ArrayList<SongObject> listSong = new ArrayList();
        SongPanel song;
        SongObject songObj;
        File file = new File(locationFolder+"/"+filename+".list");
        if(file.exists())
        {
            FileInputStream in = new FileInputStream(file);
            byte buf[] = new byte[(int)file.length()];
            in.read(buf);
            in.close();
            String s = new String(buf,"UTF-8");
            if(s.compareTo("")==0) return arrayList;
            String[] list = s.split("\n");
            list = checkFile(list);
            updateList(filename, list);
            File[] file1 = new File[list.length];
            for(int i = 0 ;i< list.length;i++)
                file1[i] = new File(list[i]);
            readFile(arrayList, panel, file1);
            if(count > 0)
            {
                s = "";
                for (SongObject Song : listSong) {
                    s += Song.getPath()+"\n";
                }
                confirmremoveLink(filename,s);
            }
        }
        else
            System.out.println("can't read");
        return arrayList;
    }
    public void removeLink(String listName, String content) throws Exception
    {
        File file = new File(locationFolder+"/"+listName+".list");
        if(file.exists())
        {
            FileOutputStream out = new FileOutputStream(file);
            byte data[] = content.getBytes();
            out.write(data);
            out.close();
        }
        else
            System.out.println("can't remove");
    }
    private void confirmremoveLink(String fileName,String s)
    {
        int i = JOptionPane.showConfirmDialog(null, "Several files can not open!\n"
                + "Do you want remove those files ?", "Warning !!", JOptionPane.WARNING_MESSAGE);
        if(i == JOptionPane.YES_OPTION)
            try {
                removeLink(fileName, s);
        } catch (Exception ex) {
            Logger.getLogger(FileMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void updateList(String fileName, String[] list)
    {
        String content="";
        File file = new File(locationFolder+"/"+fileName+".list");
        for(int i=0; i<list.length;i++)
            content += list[i]+"\n";
        if(file.exists())
        {
            try {
                FileOutputStream out= new FileOutputStream(file);
                byte data[] = content.getBytes();
                out.write(data);
                out.close();
            } catch (Exception ex) {
                Logger.getLogger(FileMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private String[] checkFile(String[] s)
    {
        String[] list = null;
        ArrayList<String> array = new ArrayList();
        File file;
        for(int i=0; i<s.length;i++)
        {
            file = new File(s[i]);
            if(file.exists())
                array.add(s[i]);
        }
        list = new String[array.size()];
        for(int i=0;i<array.size();i++)
            list[i] = array.get(i);
        return list;
    }
    public ArrayList getMyMusicFile(NowPlayingPanel nowplayingPanel)
    {
        File file = new File(locationMyMusic);
        File[] listFile = null;
        ArrayList<SongPanel> listPanel = new ArrayList();
        if(file.exists())
        {
            listFile = file.listFiles();
            readFile(listPanel,nowplayingPanel,listFile);
        }
        return listPanel;
    }
    public int loadCurrentBackGround()
    {
        File file = new File(locationFolder+"/MusicPlayer.dat");
        if(!file.exists())
            try {
                file.createNewFile();
                FileOutputStream out = new FileOutputStream(file);
                out.write(0);
                out.close();
                return 0;
            } catch (Exception ex) {
                System.out.println("Can't create file");
            }
        else
        {
            try {
                FileInputStream in = new FileInputStream(file);
                int cur = in.read();
                in.close();
                return cur;
            } catch (Exception ex) {
                Logger.getLogger(FileMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    private void readFile(ArrayList<SongPanel> listPanel,NowPlayingPanel nowplayingPanel,File[] listFile)
    {
        int sum = 10;
        long time1 = System.currentTimeMillis();
        ArrayList listSong = new ArrayList();
        SongObject song ;
        SongPanel panel;
        int leng = listFile.length;
        if(listFile.length >= sum )
        {
            int count = leng/sum;
            ThreadRunable1 t[] = new ThreadRunable1[sum];
            Thread x[] = new Thread[sum];
            for(int i=0;i<sum-1;i++)
            {
                t[i] = new ThreadRunable1(listFile, i*count, (i+1)*count-1, nowplayingPanel, listPanel, listSong);
                x[i] = new Thread(t[i]);
            }
            t[sum-1] = new ThreadRunable1(listFile, (sum-1)*count, leng-1, nowplayingPanel, listPanel, listSong);
            x[sum-1] = new Thread(t[sum-1]);
            for(int i=0;i<sum;i++)
                x[i].start();
            for(int i=0;i<sum;i++)
                try {
                    x[i].join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(FileMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else
        {
            ThreadRunable1 t = new ThreadRunable1(listFile, 0, leng-1, nowplayingPanel, listPanel, listSong);
            t.run();
        }
        if(listPanel.size() >= sum )
        {
            int count = listPanel.size()/sum;
            ThreadRunable2 t[] = new ThreadRunable2[sum];
            Thread x[] = new Thread[sum];
            for(int i=0;i<sum-1;i++)
            {
                t[i] = new ThreadRunable2(listPanel, i*count, (i+1)*count-1);
                x[i] = new Thread(t[i]);
            }
            t[sum-1] = new ThreadRunable2(listPanel, (sum-1)*count, listPanel.size()-1);
            x[sum-1] = new Thread(t[sum-1]);
            for(int i=0;i<sum;i++)
                x[i].start();
            for(int i=0;i<sum;i++)
                try {
                    x[i].join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(FileMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else
        {
            ThreadRunable2 t = new ThreadRunable2(listPanel, 0, listPanel.size()-1);
            t.run();
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1);
//        System.out.println(listPanel.size());
    }
}