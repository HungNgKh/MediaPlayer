/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 *
 * @author Doan Phuc
 */
public class SongObject {
    private final String fileName;
    private String Title;
    private long duration;
    private String author;
    private String album;
    private int time;
    private BufferedImage imageAlbum;
    
    public SongObject(String fileName) throws Exception
    {
        this.fileName = fileName;
        loadInfo();
    }
    private void loadInfo() throws Exception
    {
        File file = new File(fileName);
        Map p = ((TAudioFileFormat)AudioSystem.getAudioFileFormat(file)).properties();
        Title = (String) p.get("title");
        if(Title == null)
            Title = file.getName();
        author = (String) p.get("author");
        if(author == null)
            author = "unknown";
        album = (String) p.get("album");
        if(album == null)
            album = "unknown";
        loadImage(fileName);
        time = (int) p.get("mp3.length.frames");
        duration = (long) p.get("duration");
    }
    private void loadImage(String file)
    {
        try {
            Mp3File song = new Mp3File(file);
            if(song.hasId3v2Tag())
            {
                ID3v2 id3v2tag = song.getId3v2Tag();
                byte[] imageData = id3v2tag.getAlbumImage();
                imageAlbum = ImageIO.read(new ByteArrayInputStream(imageData));
            }
        } catch (Exception ex) {
            try {
                imageAlbum = ImageIO.read(this.getClass().getClassLoader().getResource("Image/icon1.png"));
            } catch (IOException ex1) {
                Logger.getLogger(SongObject.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } 
    }
    
    public String getPath()
    {
        return fileName;
    }
    public String getName()
    {
        return Title;
    }
    public String getAuthor()
    {
        return author;
    }
    public String getAlbum()
    {
        return album;
    }
    public BufferedImage getImage()
    {
        return imageAlbum;
    }
    public int getLengthinframes()
    {
        return time;
    }
    public long getDuration()
    {
        return duration;
    }
}
